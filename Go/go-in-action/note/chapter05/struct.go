package main

import "fmt"

type attribution struct {
	owner int
	perm  int
}

type file struct {
	name string
	attr attribution
}

func testEmptyStruct(tmp struct{}) {

}

func main() {
	f := file{
		name: "Angus",
		attr: attribution{
			owner: 1,
			perm:  1,
		},
	}
	fmt.Println(f)

	p := &f
	p.name = "liu"
	fmt.Println(f)
}
