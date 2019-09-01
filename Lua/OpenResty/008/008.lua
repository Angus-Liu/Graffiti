-- OpenResty 第八次作业：cosocket 使用（part 1）

-- 1、使用 cosocket 来做外部服务的健康检查，发送 syn 包，如果对方有回应，说明服务在线。

-- 测试，post 请求，body 如下：
-- {
-- "host": "172.18.1.1",
-- "port":6379
-- }

-- 返回服务是否在线{"online":true/false}

-- 2、使用cosocket 写一个 redis 客户端，要求支持 ping get set 方法，要求支持连接池大小、超时时间配置
