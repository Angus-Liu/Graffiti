package main

import (
	"./internal"
	"fmt"
	"unsafe"
)

func main() {
	d := internal.NewData()
	// 直接访问导出字段
	d.Y = 10
	// 利用指针访问私有字段
	p := (*struct{ x int })(unsafe.Pointer(d))
	p.x = 10
	fmt.Printf("%+v\n", *d)
}
