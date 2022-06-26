     mov eax,cs
     mov ss,eax
     mov sp,0x7c00

     mov eax,[cs:pgdt+0x7c00+0x02]  ; GDT 32 位线性地址
     xor edx,edx
     mov ebx,16
     div ebx

     mov ds,eax    ; DS 指向 GDT 所在段 
     mov ebx,edx   ; 段内起始偏移地址

     ; 创建 0# 描述符，空描述符
     mov dword [ebx+0x00], 0x00000000
     mov dword [ebx+0x04], 0x00000000

     ; 创建1#描述符，这是一个数据段，对应0~4GB的线性地址空间
     ; 高32：0000-0000-1100-1111-1001-0010-0000-0000
     ; 低32：0000-0000-0000-0000-1111-1111-1111-1111
     mov dword [ebx+0x08],0x0000ffff    ; 基地址为0，段界限为0xfffff
     mov dword [ebx+0x0c],0x00cf9200    ; 粒度为4KB，存储器段描述符 

     ; 创建保护模式下初始代码段描述符
     mov dword [ebx+0x10],0x7c0001ff    ; 基地址为0x00007c00，512字节 
     mov dword [ebx+0x14],0x00409800    ; 粒度为1个字节，代码段描述符 

     ; 创建以上代码段的别名描述符
     mov dword [ebx+0x18],0x7c0001ff    ; 基地址为0x00007c00，512字节
     mov dword [ebx+0x1c],0x00409200    ; 粒度为1个字节，数据段描述符

     ; 创建栈段描述符
     mov dword [ebx+0x20],0x7c00fffe    ; 基地址为0x00007c00，段界限为0xffffE
     mov dword [ebx+0x24],0x00cf9600    ; 粒度为4KB，栈段描述符

     ; 初始化描述符表寄存器 GDTR
     mov word [cs:pgdt+0x7c00],39  ; 描述符表的界限
     lgdt [cs:pgdt+0x7c00]

     ; 打开 A20
     in al,0x92
     or al,0000_0010B
     out 0x92,al

     ; 禁止中断
     cli

     ; 设置 PE 位
     mov eax,cr0
     or eax,1
     mov cr0,eax

     ; 进入保护模式
     jmp dword 0x0010:flush  ; 16位的描述符选择子：32位偏移

     [bits 32]

flush:
     mov eax,0x0018 ; 11B号选择子（代码段的别名段，可写）
     mov ds,eax

     mov eax,0x0008 ; 01B号选择子（数据段）
     mov es,eax
     mov fs,eax
     mov gs,eax

     mov eax,0x0020 ; 100B号选择子（栈段）
     mov ss,eax
     xor esp,esp

     mov dword [es:0x0b8000],0x072e0750 ;字符'P'、'.'及其显示属性
     mov dword [es:0x0b8004],0x072e074d ;字符'M'、'.'及其显示属性
     mov dword [es:0x0b8008],0x07200720 ;两个空白字符及其显示属性
     mov dword [es:0x0b800c],0x076b076f ;字符'o'、'k'及其显示属性

     ; 开始冒泡排序
     mov ecx,pgdt-string-1  ; 遍历次数=串长度-1
lp_exter:
     push ecx
     xor bx,bx
lp_inter:
     mov ax,[string+bx]
     cmp ah,al
     jge lp_inter_next
     xchg al,ah
     mov [string+bx],ax
lp_inter_next:
     inc bx
     loop lp_inter
     pop ecx
     loop lp_exter

     ; 显示排序后的字符串
     mov ecx,pgdt-string
     xor ebx,ebx
show_next:
     mov ah,0x07
     mov al,[string+ebx]
     mov [es:0xb80a0+ebx*2],ax
     inc ebx
     loop show_next

     hlt

;-----------------------------------------------------------
string db 's0ke4or92xap3fv8giuzjcy5l1m7hd6bnqtw.'
;-----------------------------------------------------------
pgdt dw 0
     dd 0x00007e00  ; GDT 物理地址
;-----------------------------------------------------------
times 510-($-$$) db 0
                 dw 0xaa55
