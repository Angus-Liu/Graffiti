package main

import (
	"fmt"
	"reflect"
	"unsafe"
)

func main() {
	s := "ab" + "cd"
	println(s == "abcd")
	println(s > "abc")
	println(&s)
	println(s)
	s1 := s[:3]
	fmt.Printf("%#v\n", (*reflect.StringHeader)(unsafe.Pointer(&s)))
	fmt.Printf("%#v\n", (*reflect.StringHeader)(unsafe.Pointer(&s1)))
}
