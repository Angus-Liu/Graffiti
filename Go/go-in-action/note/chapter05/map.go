package main

import "fmt"

func main() {
	m := make(map[string]int)
	m["a"] = 1
	m["b"] = 2
	fmt.Println(m)

	m2 := map[string]int{
		"a": 11,
		"b": 22,
	}
	fmt.Println(m2)

	type user struct {
		name string
		age  byte
	}

	u := user{"Angus", 23}

	m3 := map[int]*user{
		1: &u,
	}
	m3[1].age++
	fmt.Println(m3[1].age)
	fmt.Println(u)
}
