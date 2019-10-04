package main

import "sync"

func main() {
	var wg sync.WaitGroup
	wg.Add(2)

	a, b := make(chan int), make(chan int)

	// 接收端
	go func() {
		defer wg.Done()

		for {
			var (
				name string
				x    int
				ok   bool
			)

			// 随机选择可用channel接收数据
			select {
			case x, ok = <-a:
				name = "a"
			case x, ok = <-b:
				name = "b"
			}
			// 如果任一通道关闭，则终止接收
			if !ok {
				return
			}
			// 输出接收的数据
			println(name, x)
		}
	}()

	// 发送端
	go func() {
		defer wg.Done()
		defer close(a)
		defer close(b)

		for i := 0; i < 10; i++ {
			// 随机选择发送channel
			select {
			case a <- i:
			case b <- i * 10:
			}
		}
	}()

	wg.Wait()
}
