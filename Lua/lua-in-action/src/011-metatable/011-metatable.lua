---
--- Generated by EmmyLua(https://github.com/EmmyLua)
--- Created by Angus.
--- DateTime: 2019/8/25 20:31
---

local mytable = {}
local mymetatable = {}
setmetatable(mytable, mymetatable)

local set1 = { 1, 2, 3 }
local set2 = { 3, 4, 5, 10 }

local union = function(self, another)
    local set = {}
    local result = {}

    for i, j in pairs(self) do
        set[j] = true
    end

    for i, j in pairs(another) do
        set[j] = true
    end

    for i, j in pairs(set) do
        table.insert(result, i)
    end

    return result
end

setmetatable(set1, { __add = union })

local set3 = set1 + set2
for i, j in pairs(set3) do
    print(i, j)
end

print(#set3)