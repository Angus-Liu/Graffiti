local json = require "cjson"
local redis = require "resty.redis"
local red = redis:new()

red:set_timeout(1000)

local ok, err = red:connect("127.0.0.1", 6379)
if not ok then
    ngx.say("failed to connect: ", err)
    return
end

local count, err = red:get("count")
if count == ngx.null then
    count = 0
end

count = count + 1

local ok,err = red:set("count", count)
if not ok then
    ngx.say("failed to set count: ", count)
    return
end 

local resp = {
    ["count"] = count,
    ["now"] = os.date("%Y-%m-%d %H:%M:%S")
}

ngx.say(json.encode(resp))

local ok, err = red:set_keepalive(10000, 100)
if not ok then
    ngx.say("failed to set keepalive: ", err)
    return
end