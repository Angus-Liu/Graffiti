package main

import (
	"fmt"
	"os"
)

type data struct {
	os.File
}

func main() {
	d := data{
		os.File{},
	}

	fmt.Printf("%#v\n", d)
}
