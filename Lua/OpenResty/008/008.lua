-- OpenResty 第八次作业：cosocket 使用（part 1）
-- 1、使用 cosocket 来做外部服务的健康检查，发送 syn 包，如果对方有回应，说明服务在线。
-- 测试，post 请求，body 如下：
-- {
-- "host": "172.18.1.1",
-- "port":6379
-- }
-- 返回服务是否在线{"online":true/false}
local sock = ngx.socket.tcp()
local ok, err = sock:connect("www.baidu.com", 80)
if not ok then
    ngx.say("failed to connect to baidu: ", err)
    return
end
local req_data = "GET / HTTP/1.1\r\nHost: www.baidu.com\r\n\r\n"
local bytes, err = sock:send(req_data)
if err then
    ngx.say("failed to send to baidu: ", err)
    return
end
local data, err, partial = sock:receive()
if err then
    ngx.say("failed to receive from baidu: ", err)
    return
end
sock:close()
ngx.say("successfully talk to baidu! response first line: ", data)

-- 2、使用cosocket 写一个 redis 客户端，要求支持 ping get set 方法，要求支持连接池大小、超时时间配置

