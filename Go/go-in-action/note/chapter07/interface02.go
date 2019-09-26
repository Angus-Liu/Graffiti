package main

import "fmt"

type TestError struct{}

func (*TestError) Error() string { return "" }

func test(x int) (int, error) {
	if x < 0 {
		x = 0
		return 0, new(TestError)
	} else {
		x += 100
	}
	return x, nil
}

func main() {
	//x,err := test(100)
	//println(x, err)
	//println(err == nil)

	var x interface{} = func(x int) string {
		return fmt.Sprintf("d:%d", x)
	}

	switch v := x.(type) {
	case nil:
		println("nil")
	case *int:
		println(*v)
	case func(int) string:
		println(v(100))
	case fmt.Stringer:
		fmt.Println(v)
	default:
		println("unknown")
	}
}
