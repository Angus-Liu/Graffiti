package main

import (
	"fmt"
	"reflect"
)

type user struct {
	name string `姓名`
	age  byte   `年龄`
}

func main() {
	u := user{"刘安光", 23}
	fmt.Println(u)
	v := reflect.ValueOf(u)
	fmt.Println(v)
	t := v.Type()
	fmt.Println(t)

	for i := 0; i < t.NumField(); i++ {
		fmt.Printf("%s: %v\n", t.Field(i).Tag, v.Field(i))
	}
}
