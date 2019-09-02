-- 请求 url
local url = ngx.var.uri
ngx.say("url: " .. url)
-- 方法
local method = ngx.var.request_method
ngx.say("method: " .. method)
-- querystring
local query_string = ngx.req.get_uri_args()
for k, v in pairs(query_string) do
    ngx.say("query string: ", k, "=", v)
end
-- post请求中的 json body
if ngx.var.request_method == "POST" then
    local json = require("cjson")
    ngx.req.read_body()
    local json_body = ngx.req.get_body_data()
    local json_body_str = json.encode(json_body);
    if string.len( json_body_str ) <= 100 then
        ngx.say("json body: " .. json_body)
    else 
        ngx.exit(413)
    end
end
