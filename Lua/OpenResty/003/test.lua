-- OpenResty 第三次作业，这次以 lua 语言基础第一部分：

-- 1、lua 有哪些数据类型，试着打印所有的数据类型
-- Lua 中有 8 个基本类型分别为：nil、boolean、number、string、userdata、function、thread 和 table。
print(type(nil))

print(type(false))

print(type(123))

print(type("Hello world"))


local file = io.open("test.lua", "r")
print(type(file))

print(type(print))


function fun()
	print("Hello")
end
local thread = coroutine.create(fun)
print(type(thread))

print(type({}))

print('=================================')


-- 2、如何使用 for 循环把拼接 1~100 的数字按顺序拼接为一个字符串

local str = ""
for i=1, 100 do
	str = str .. i
end
print(str)

print('=================================')


-- 3、函数多返回值处理：写一个函数 fun(a,b)同时返回 a+b 和 a-b 的值

function fun(a, b)
	return a + b, a - b
end

print(fun(1,2))

print('=================================')

-- 4、local t={red=1, blue=2, yellow=3}，尝试打印 t 的大小，并遍历t，打印 key 和 value

local t={red=1, blue=2, yellow=3}

print(#t)

for k, v in pairs(t) do
    print('key:' .. k, 'value:' .. v)
end
print('=================================')

-- 5、如何引入第三方json模块，序列化如下的结构{a=1, b=2}为字符串，并打印
local cjson = require "cjson"
local jsonData = [[{"a":1,"b":2}]]
print(cjson.encode(jsonData))
