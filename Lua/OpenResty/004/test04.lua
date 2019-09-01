-- OpenResty 第四次作业来啦，这次还是lua语言相关的内容，三分钟搞定
-- 1、面向对象
-- (1) 有 local x={} ，尝试给 x 添加id，name，birthday 字段和 toString()方法
local x = {}
x.id = 1
x.name = 'angus'
x.birthday = '1996-07-02'
function x.toString ()
    print('id:' .. x.id .. ', name:' .. x.name .. ', ' .. 'birthday:' .. x.birthday)
end
x.toString()
print('-----------------------------')
-- (2) lua 的元表（Metatable）有什么用，举一个列子说明它的用法
-- 在 Lua table 中可以访问对应的 key 得到相应的 value，但是却无法对两个 table 进行操作。
-- 因而此时需要通过元表(Metatable)来改变 table 的行为，其每个行为关联了对应的元方法。
local t = setmetatable({ 1, 2, 3, 4 }, {
    __add = function(t, n)
        for i = 1, #t do
            t[i] = t[i] + n[i]
        end
        return t
    end;
    __tostring = function(t)
        local str = ''
        for k, v in pairs(t) do
            str = str .. 'k:' .. k .. ', v:' .. v .. '\n'
        end
        return str
    end
})
local n = { 9, 9, 9, 9 }
print(t + n)
print('-----------------------------')
-- (3) lua 的 self 有什么用，说明它的用法
-- 类似 Java 中的 this 引用，代表调用者本身
-- 2、如何实现从字符串中加载一段 lua 代码，然后执行这段 lua 代码？
-- 通过 loadstring 加载运行
local script = 'print("this is a script")'
loadstring(script)()
print('-----------------------------')
--3、String 的用法，有字符串定义 local s = "hello, world"
local s = "hello, world"
--(1) 将 s 转大写打印
print(string.upper(s))
print('-----------------------------')
--(2) 截取逗号之前和之后的字符串并打印
local m = string.find(s, ',')
print(s.sub(s, 0, m - 1))
print(s.sub(s, m + 1, #s))
print('-----------------------------')
--(3) for 循环打印 s 每个字符的 ASCII 码值
for i = 1, #s do
    print(string.byte(s,i,i))
end