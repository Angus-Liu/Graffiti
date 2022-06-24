; 用户程序

SECTION header vstart=0
  program_length dd program_end    ; 程序总长度[0X00]

  ; 用户程序入口点
  code_entry dw start              ; 偏移地址[0x04]
             dd section.code.start ; 段地址[0x06]

  ; 段重定位表信息
  realloc_tbl_len dw (tbl_end - tbl_start) / 4  ; 表项个数[0x0a]
  tbl_start:                             ;[0x0c] 表项首地址
    code_segment  dd section.code.start  ;[0x0c]
    data_segment  dd section.data.start  ;[0x10]
    stack_segment dd section.stack.start ;[0x14]
  tbl_end:

SECTION code align=16 vstart=0
start:
  mov ax,[data_segment]
  mov ds,ax
  mov si,string

  mov ax,0xb800
  mov es,ax
  mov di,0

  mov cx,(string_end-string)
show:
  mov al,[si]
  mov ah,0x41
  mov [es:di],ax
  inc si
  add di,2
  loop show

  jmp $

SECTION data align=16 vstart=0
  string db 'Hello World'
  string_end:

SECTION stack align=16 vstart=0

SECTION trail align=16
  program_end:
