package main

import (
	"sync"
	"time"
)

func main() {
	var wg sync.WaitGroup
	for i := 0; i < 10; i++ {
		wg.Add(1)
		go func(id int) {
			defer wg.Done()
			time.Sleep(time.Second)
			println("goroutine", id, "done")
		}(i)
	}

	go func() {
		println("go wg wait...")
		wg.Wait()
		println("go wg exit.")
	}()

	println("main wait...")
	wg.Wait()
	println("main exit.")
}
