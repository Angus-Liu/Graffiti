package main

import "fmt"

type pool chan []byte

func newPool(cap int) pool {
	return make(chan []byte, cap)
}

func (p pool) get() []byte {
	var v []byte
	select {
	// 返回
	case v = <-p:
	// 返回失败，新建
	default:
		v = make([]byte, 10)
	}
	return v
}

func (p pool) put(b []byte) {
	select {
	// 放回
	case p <- b:
	// 放回失败，放弃
	default:
	}
}

func main() {
	var p pool = newPool(2)
	p.put([]byte{1, 2, 3})
	p.put([]byte{3, 2, 1})
	p1 := p.get()
	fmt.Println(p1)
	p2 := p.get()
	fmt.Println(p2)
}
