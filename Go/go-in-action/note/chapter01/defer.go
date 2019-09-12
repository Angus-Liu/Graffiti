package main

func test(x int) {
	i := 1 / x
	println(i)
}

func main() {
	defer println(1)
	defer test(0)
	defer println(2)
}
