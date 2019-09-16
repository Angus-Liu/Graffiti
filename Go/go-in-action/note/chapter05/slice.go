package main

import "fmt"

func main() {
	var a1 = [...]int{1, 2, 3}
	var s1 = a1[:]
	println(&a1)
	println(&s1)
	s1[0] += 10
	fmt.Println(a1)
}
