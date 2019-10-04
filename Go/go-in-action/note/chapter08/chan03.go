package main

import "sync"

func main() {
	var wg sync.WaitGroup
	wg.Add(3)

	a, b := make(chan int), make(chan int)

	// 接收端
	go func() {
		defer wg.Done()

		for {
			select {
			case x, ok := <-a:
				// 如果通道关闭，则设置为nil，阻塞
				if !ok {
					a = nil
					break
				}
				println("a", x)
			case x, ok := <-b:
				if !ok {
					b = nil
					break
				}
				println("b", x)
			}
			// 全部结束，退出循环
			if a == nil && b == nil {
				return
			}
		}

	}()

	// 发送端a
	go func() {
		defer wg.Done()
		defer close(a)

		for i := 0; i < 3; i++ {
			a <- i
		}
	}()

	// 发送端b
	go func() {
		defer wg.Done()
		defer close(b)

		for i := 0; i < 3; i++ {
			b <- i
		}
	}()

	wg.Wait()
}
