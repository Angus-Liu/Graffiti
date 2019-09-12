package main

import "fmt"

type flags byte

const (
	read flags = iota
	write
	exec
)

type (
	user struct {
		name string
		age  uint8
	}
	event func(string) bool
)

func main() {
	//fmt.Println(read)
	//fmt.Println(write)
	//fmt.Println(exec)

	//var u = user{"", 1}

	type data [2]int
	var d data = [2]int{1, 2}
	fmt.Println(d)

	a := make(chan int, 2)
	var b chan<- int = a
	b <- 1

	f := 1.0 << 3
	fmt.Println(f)

	var m = 3
	var n int = 1.0 << m
	fmt.Println(n)
}
