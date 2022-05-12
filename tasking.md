**AC1 当我通过userId查询时，返回用户的id, name, age信息**

Given 后端有user数据
When 我通过users/{id}接口发送查询请求
Then 能接收到该id用户的id，name，age信息

- [ ] given 数据库中存在的id 
  when 请求用户信息 
  then 返回id，name，age

GET /users/{id}

Response
```json
{
  "id": "number",
  "name": "string",
  "age": "number"
}

```


