; 计算 GDT 所在的逻辑段地址
mov ax, [cs:gdt_base + 0x7c00]
mov dx, [cs:gdt_base + 0x7c00 + 0x02]
mov bx, 16
div bx
mov ds, ax   ; 段地址
mov bx, dx   ; 偏移地址

; 创建 #0 描述符，空描述符
mov dword [bx + 0x00], 0x00
mov dword [bx + 0x04], 0x00

; 创建 #1 描述符，保护模式下的代码段描述符
mov dword [bx + 0x08], 0x7c0001ff
mov dword [bx + 0x0c], 0x00409800

; 创建 #2 描述符，保护模式下的数据段描述符（文本模式下的显示缓冲区）
mov dword [bx + 0x10], 0x8000ffff
mov dword [bx + 0x14], 0x0040920b

; 初始化描述符表寄存器 GDTR
mov word [cs:gdt_size + 0x7c00], 23  ; 描述符表的界限
lgdt [cs:gdt_size + 0x7c00]

; 开启第 21 条地址线 A20
in al, 0x92
or al, 0000_0010B
out 0x92, al    ; 打开 A20

; 关中断，保护模式下中断机制还未建立，原有的中断向量表不再适用
cli

; 设置 PE 位，开启保护模式
mov eax, cr0
or eax, 1
mov cr0, eax

; 进入保护模式
; 0000-0000-0000-1000 #1 代码段选择子
jmp dword 0x0008:flush

[bits 32]

flush:
    ; 0000-0000-0001-0000 #2 数据段选择子
    mov cx, 0x0010
    mov ds, cx

    ; 以下在屏幕上显示"Protect mode OK." 
    mov byte [0x00],'P'  
    mov byte [0x02],'r'
    mov byte [0x04],'o'
    mov byte [0x06],'t'
    mov byte [0x08],'e'
    mov byte [0x0a],'c'
    mov byte [0x0c],'t'
    mov byte [0x0e],' '
    mov byte [0x10],'m'
    mov byte [0x12],'o'
    mov byte [0x14],'d'
    mov byte [0x16],'e'
    mov byte [0x18],' '
    mov byte [0x1a],'O'
    mov byte [0x1c],'K'    

    hlt

gdt_size dw 0
gdt_base dd 0x00007e00

times 510-($-$$) db 0
dw 0xaa55
