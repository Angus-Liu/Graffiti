package main

import "fmt"

func test3(s string, a ...int) {
	fmt.Printf("%T, %v\n", a, a)
}

func test4(a ...int) {
	for i := range a {
		a[i] += 100
	}
}

func main() {
	test3("abc", 1, 2, 3)

	a := []int{10, 20, 30}
	test4(a...)
	fmt.Println(a)
}
