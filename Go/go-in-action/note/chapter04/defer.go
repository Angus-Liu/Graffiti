package main

func test6() (z int) {
	defer func() {
		println("defer:", z)
		z += 100
	}()
	return 100
}

func main() {
	//x, y := 1, 2
	//
	//defer func(a int) {
	//	println("defer x,y=", a, y)
	//}(x)
	//
	//x += 100
	//y += 100
	//println(x, y)

	println("test:", test6())
}
