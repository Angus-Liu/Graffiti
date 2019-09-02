-- 2、使用cosocket 写一个 redis 客户端，要求支持 ping get set 方法，要求支持连接池大小、超时时间配置

local redis = require "redis"

local r = redis:new("127.0.0.1", 6379)
r.set_timeout(100)
r.ping()
r.set("name", "angus")
r.get("name")
r.set_keepalive(10000, 100)