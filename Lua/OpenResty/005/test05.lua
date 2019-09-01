-- OpenResty  第五次作业来啦，这次继续 lua 的学习

-- 1、curl localhost:8080/test，返回 Test.java 的内容，这个 .java 文件在本地，需要使用 lua 逐行读取，每一行增加一个行号。
-- 先空着，换 MAC 写
file = io.open("005/test05.lua", "r")
local lineNum = 0
for line in file:lines() do
    lineNum = lineNum + 1
    print(lineNum .. ': ' .. line)
end

-- 2、lua 中如何使用可变参数
-- Lua 在函数参数列表中使用三点 ... 表示函数有可变的参数，举个例子
function sum(...)
    local res = 0
    for num in pairs { ... } do
        res = res + num;
    end
    return res
end
print(sum(1, 2, 3))
print('------------------')

-- 3、LuaJIT 是什么？怎么查看它的版本，与 lua 和 OpenResty 的关系是什么
-- (1) LuaJIT is a Just-In-Time Compiler for the Lua programming language.
--     LuaJIT是 Lua 编程语言的即时编译器，采用 C 语言编写，其语法和标准 Lua 的语法没多大区别，但效率快数倍。
-- (2) 在 OpenResty 的安装目录下可以找到 LuaJIT 及其版本号。
--     我的 MAC 是在 /usr/local/opt/openresty/luajit/bin/luajit-2.1.0-beta3，
--     我的 Windows，可以直接运行 OpenResty 文件夹下的 luajit.exe 显示其版本号。
-- (4) Lua 是 OpenResty 平台上使用的开发语言，OpenResty 通过 LuaJIT 编译执行 Lua 代码。

-- 4、如何做二进制运算？假设有 local x,y = 1, 4，试着打印与、或、非、异或、左移右移操作。
-- Lua 5.1 版本使用一个外部的 bit 库，提供几个位操作函数。
-- Lua 5.2 版本提供了一个内置库bit32，同样以函数的形式提供位操作。
-- Lua 5.3 版本后，Lua像其他语言一样开始提供内置操作符实现位操作。
-- 以下是借助 bit 库实现的位运算（我 Windows 上的 Lua 是 5.1 版本）：
require "bit"
local x, y = 1, 4
print(bit.band(x, y))  -- 与
print(bit.bor(x, y))   -- 或
print(bit.bnot(x))     -- 非
print(bit.bnot(y))     -- 非
print(bit.bxor(x, y))  -- 异或
print(bit.lshift(x, 1)) -- 左移
print(bit.rshift(y, 1)) -- 右移
print('------------------')

-- 5、使用 lua 实现 LeetCode happy number 算法题（这题我经常面试的时候让面试的同学写，很简单）
-- https://leetcode.com/problems/happy-number/

function isHappy(n)
    if n > 10 then
        local sum = 0
        while n > 0
        do
            local num = math.fmod(n, 10)
            sum = sum + (num * num);
            n = math.modf(n / 10)
        end
        return isHappy(sum)
    else
        if n == 1 or n == 7 or n == 10
        then
            return true
        else
            return false
        end
    end
end

print(isHappy(1))
print(isHappy(2))
print(isHappy(19))
print(isHappy(101))