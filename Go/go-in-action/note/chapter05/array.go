package main

import "fmt"

func main() {
	a := [...]int{1, 2}
	p := &a
	p[1] += 10
	fmt.Println(a)
}
