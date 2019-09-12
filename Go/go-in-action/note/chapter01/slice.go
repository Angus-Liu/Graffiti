package main

import "fmt"

func main() {
	//x := make([]int, 0, 5)
	//for i := 0; i < 8; i++ {
	//	x = append(x, i)
	//}
	//fmt.Print(x)

	m := make(map[string]int)
	m["a"] = 1
	x, ok := m["b"]
	fmt.Print(x, ok)

	delete(m, "a")
}
