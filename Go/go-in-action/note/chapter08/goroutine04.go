package main

import "runtime"

func main() {
	n := runtime.NumCPU()
	println(n)
}
