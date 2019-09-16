package main

import (
	"errors"
	"fmt"
)

func main() {
	// 栈最大容量为5
	stack := make([]int, 0, 5)

	// 入栈操作
	push := func(x int) error {
		n := len(stack)
		if n == cap(stack) {
			return errors.New("stack is full")
		}
		stack = stack[:n+1]
		stack[n] = x
		return nil
	}

	// 出栈操作
	pop := func() (int, error) {
		n := len(stack)
		if n == 0 {
			return 0, errors.New("stack is empty")
		}
		r := stack[n-1]
		stack = stack[:n-1]
		return r, nil
	}

	// 入栈
	for i := 0; i < 6; i++ {
		_ = push(i)
		fmt.Println(stack)
	}
	// 出栈
	for i := 0; i < 6; i++ {
		_, _ = pop()
		fmt.Println(stack)
	}
}
