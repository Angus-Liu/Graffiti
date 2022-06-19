; 用户程序

SECTION header vstart=0
  program_length dd program_end    ; 程序总长度[0X00]

  ; 用户程序入口点
  code_entry dw start              ; 偏移地址[0x04]
             dd section.code.start ; 段地址[0x06]

  ; 段重定位表信息
  realloc_tbl_len dw (tbl_end - tbl_start) / 4  ; 表项个数[0x0a]
  tbl_start:
    code_segment  dd section.code.start  ;[0x0c]
    data_segment  dd section.data.start  ;[0x10]
    stack_segment dd section.stack.start ;[0x14]
  tbl_end:

SECTION code align=16 vstart=0
  start:

SECTION data align=16 vstart=0

SECTION stack align=16 vstart=0

SECTION trail align=16
  program_end:
