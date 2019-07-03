var http = require('http');
var url = require("url");
var body = require("body-parser");
var express = require('express');
var data = require('./jsondata');
var app = express();

//配置post请求参数获取
app.use(body.urlencoded({ extended: false })); 

//设置跨域访问
app.all('*', function(req, res, next) {
    res.header("Access-Control-Allow-Origin", "*");
    res.header("Access-Control-Allow-Headers", "X-Requested-With");
    res.header("Access-Control-Allow-Methods", "PUT,POST,GET,DELETE,OPTIONS");
    res.header("X-Powered-By", ' 3.2.1');
    res.header("Content-Type", "application/json;charset=utf-8");
    next();
});

//设置返回数据格式
var result = {
    "status": "200",
    "message": "success",
    "data": []
}

app.get('/user', function(req, res) {
    console.log("收到get请求")
	console.log(data.udata);
	result.data = data.udata;
	res.send(JSON.stringify(result));
})

app.post("/*",(req,res) => {
	//获取post传参
	console.log("收到post请求")
	let name = req.body.name;
	console.log(name);
	result.data = data.udata;
	res.send(JSON.stringify(result));
})

app.listen(3000, 'localhost');
