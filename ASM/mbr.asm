; 硬盘主引导扇区代码

; 声明常数（用户程序起始逻辑扇区号），常数的声明不会占用汇编地址
app_lba_start equ 100

SECTION mbr align=16 vstart=0x7c00
  ; 设置堆栈段
  mov ax,0
  mov ss,ax
  mov sp,ax ; 0x00:0x00

  ; 根据用户程序的物理地址计算逻辑段地址
  mov ax,[cs:phy_base]
  mov dx,[cs:phy_base+0x02]
  mov bx,16
  div bx
  ; 令 DS 和 ES 指向用户程序所在段
  mov ds,ax
  mov es,ax

  ; 读取程序的起始部分
  ; 设置程序在硬盘上的逻辑扇区号
  xor di,di
  mov si,app_lba_start
  xor bx,bx ; 加载到 DS:0000 处
  call read_hard_disk

  ; 根据读取的第一个扇区获取用户程序头部信息
  ; 根据用户程序长度计算一共有几个扇区
  mov ax,[0]
  mov dx,[2] ; 程序长度
  mov bx,512 ; 一个扇区 512 字节
  div bx
  cmp dx,0   ; 判断是否除尽
  jnz @1     ; 未除尽时剩下的字节仍然要占一个扇区
  dec ax     ; 已经读了一个扇区

@1:
  cmp ax,0
  jz direct

  ; 读取剩余的扇区
  push ds ; 以下会改变 DS
  mov cx,ax

@2:
  mov ax,ds
  add ax,0x20 ; 512=0x200
  mov ds,ax   ; DS 移到下一个以 512 字节为边界的段地址
  xor bx,bx   ; BX 置0；若不修改 DS，亦可将 BX+512
  inc si      ; 每次循环读取下一个扇区
  call read_hard_disk
  loop @2

  pop ds  ; 恢复数据段基址到用户程序头部段

  ; 计算入口点代码段基址
direct:
  mov ax,[0x06]
  mov dx,[0x08]
  call calc_segment_base
  mov [0x06],ax ; 将计算出的段地址回填，以备用户程序使用

  ; 根据段重定位表信息处理所有需要重定位的表项（同上）
  mov cx,[0x0a] ; 需要重定位的表项个数
  mov bx,0x0c   ; 重定位表首地址
realloc:
  mov ax,[bx]
  mov dx,[bx+0x02]
  call calc_segment_base
  mov [bx],ax   ; 回填段地址
  add bx,4
  loop realloc

  ; 转到用户程序
  jmp far [0x04]

; ---------------------------------
;      read_hard_disk start
; ---------------------------------
; 功能：从硬盘读取一个逻辑扇区
; 输入：DI:SI=起始逻辑扇区号
;       DS:BX=目标缓冲区地址
; ---------------------------------
read_hard_disk:
  push ax
  push bx
  push cx
  push dx

  ; 指定读取扇区数为 1
  mov dx,0x1f2
  mov al,1
  out dx,al

  ; LBA 地址 7~0
  inc dx  ; 端口 0x1f3
  mov ax,si
  out dx,al

  ; LBA 地址 15~8
  inc dx  ; 端口 0x1f4
  mov al,ah
  out dx,al

  ; LBA 地址 24~16
  inc dx  ; 端口 0x1f5
  mov ax,di
  out dx,al

  ; LBA28模式，主盘，LBA地址27~24
  inc dx  ; 端口 0x1f6
  mov al,0xe0
  or al,ah
  out dx,al

  ; 读命令
  inc dx  ; 端口 0x1f7 既是命令端口，又是状态端口
  mov al,0x20
  out dx,al

  ; 等待磁盘可读
.waits:
  in al,dx ; 从 0x1f7 获取磁盘状态信息
  and al,0x88
  cmp al,0x08
  jnz .waits
  ; 不忙，且硬盘已准备好数据传输时向下执行

  mov cx,256
  mov dx,0x1f0 ; 端口 0x1f0 为数据端口
.readw:
  in ax,dx
  mov [bx],ax
  add bx,2
  loop .readw

  pop dx
  pop cx
  pop bx
  pop ax
  ret
; ---------------------------------
;      read_hard_disk end
; ---------------------------------


; ---------------------------------
;      calc_segment_base start
; ---------------------------------
; 功能：根据物理地址（汇编地址 + phy_base）计算出段地址
; 输入：DX:AX=32位汇编地址（有效位数为 20）
; 返回：AX=16位段地址
; ---------------------------------
calc_segment_base:
  ; push bx
  push dx
  add ax,[cs:phy_base]
  adc dx,[cs:phy_base+0x02]
  ; 20位物理地址整体右移 4 位得到段地址
  shr ax,4
  ror dx,4      ; 也可以改为左移
  and dx,0xf000 ; 清掉不需要的位数
  or ax,dx
  ; 也可直接除 16，更简单
  ; mov bx,16
  ; div bx
  pop dx
  ; pop bx
  ret

; ---------------------------------
;      calc_segment_base end
; ---------------------------------

  ; 用户程序被加载到内存中的物理起始地址（20位）
  phy_base dd 0x10000

  times 510-($-$$) db 0
  dw 0xaa55
