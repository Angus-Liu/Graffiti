package main

import (
	"encoding/json"
	"fmt"
)

func main() {
	d := struct {
		Id   int    `json:"id"`
		Name string `json:"name"`
	}{1, "hello"}
	bytes, _ := json.Marshal(d)
	fmt.Println(string(bytes))
}
