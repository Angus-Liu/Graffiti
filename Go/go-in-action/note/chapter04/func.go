package main

// 定义函数类型
type FormatFunc func(string, ...interface{}) (string, error)

// 使用命名类型，可以简化参数签名
func format(f FormatFunc, s string, a ...interface{}) (string, error) {
	return f(s, a)
}

func main() {

}
