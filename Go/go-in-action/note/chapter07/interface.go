package main

type data struct {
	x int
}

func (*data) String() {}

func main() {
	d := data{100}
	var t interface{} = d
	println(t.(data).x)

	var u interface{} = &d
	println(u.(*data).x)
	u.(*data).x = 1
	println(d.x)

	var m interface{} = nil
	var n interface{} = (*int)(nil)
	println(m == nil, n == nil)

	var l *data
	println(l == nil)
}
