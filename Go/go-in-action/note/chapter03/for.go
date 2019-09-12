package main

import "fmt"

func main() {
	data := [3]int{10, 20, 30}
	for i, v := range data {
		fmt.Println(i, v)
	}
}
