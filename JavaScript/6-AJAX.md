## 6-AJAX

### 背景
> Web运作原理:一次HTTP请求对应一个页面。所以在用户提交表单(发起了一个请求)，浏览器就会刷新页面
>
> 通过JS来发送这个请求，接收数据后再用JS更新页面，就可以实现不刷新页面来传输数据

### XMLHttpRequest
> AJAX请求是异步执行的，需要通过回调函数获取响应
```javascript
function success(text) {
    var textarea = document.getElementById('test-response-text');
    textarea.value = text;
}
function fail(code) {
    var textarea = document.getElementById('test-response-text');
    textarea.value = 'ERROR' + code;
}
var request = new XMLHttpRequest(); //新建XMLHttpRequest对象
request.onreadystatechange = function() {
    if(request.readyState === 4){
        if(request.status === 200){
            return success(request.response);       
        }else{
            return fail(request.status);
        }
    }
}
request.open('GET','/api/categories');
request.send();
alert('send');
```
> 对于较低版本的IE，需要把`var request = new XMLHttpRequest();`换成`var request = new ActiveXObject('Microsoft.XMLHTTP');`

通用写法
```javascript
var request;
if(window.XMLHttpRequest){
    request = new XMLHttpRequest()
}else{
    request = new ActiveXObject('Microsoft.XMLHTTP');
}
```

### 安全限制
> JS在发送AJAX请求时，要求URL域名和当前页面必须一致(协议、域名、端口号一致)
>
> 在该限制下，JS可以通过`Flash`插件访问外域，但是现在过时，第二种方法是在同源域名下设一个代理服务器
> 
> 第三种方式称为JSONP，但只能使用GET请求，返回JSON数据
>
> [参考:JSON](https://www.cnblogs.com/dowinning/archive/2012/04/19/json-jsonp-jquery.html)

基本思路，在`<script>`设置`src`，在`<script></script>`设置处理代码
```html
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title></title>
    <script type="text/javascript">
    var localHandler = function(data){
        alert('我是本地函数，可以被跨域的remote.js文件调用，远程js带来的数据是：' + data.result);
    };
    </script>
    <script type="text/javascript" src="http://remoteserver.com/remote.js"></script>
</head>
<body>

</body>
</html>
```

通过回调函数的动态AJAX请求
> 前面的缺点很明显，就是在运行时便已调用，于是通过设置组件响应，动态添加请求代码，可以实现动态刷新

回调函数添加在URL后`?callback=refreshPrice`
```javascript
function refreshPrice(data) {
    var p = document.getElementById('test-jsonp');
    p.innerHTML = '当前价格：' +
        data['0000001'].name +': ' + 
        data['0000001'].price + '；' +
        data['1399001'].name + ': ' +
        data['1399001'].price;
}

function getPrice() {   //将这个加入组件的事件
    var
        js = document.createElement('script'),
        head = document.getElementsByTagName('head')[0];
    js.src = 'http://api.money.126.net/data/feed/0000001,1399001?callback=refreshPrice';
    head.appendChild(js);
}
```

### CORS
> Cross-Origin Resource Sharing，是HTML5规范定义的如何跨域访问资源
待更