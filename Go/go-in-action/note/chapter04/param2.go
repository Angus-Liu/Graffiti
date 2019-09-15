package main

func test2(p **int) {
	x := 100
	*p = &x
}
func main() {
	var p *int
	println(&p)
	test2(&p)
	println(*p)
}
