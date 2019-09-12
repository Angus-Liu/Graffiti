package main

import (
	"fmt"
	"reflect"
)

func main() {
	const (
		B = 1 << (10 * iota)
		KB
		MB
		GB
	)
	println(B)
	println(KB)
	println(MB)
	println(GB)

	var d = 1.0
	fmt.Print(reflect.TypeOf(d))
}
