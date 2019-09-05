## localStorage

HTML5支持在本地存储用户的浏览数据

### localStorage & sessionStorage
浏览器存储数据对象
* localStorage-用于长久保存整个网站的数据，保存数据没有过期，直到手动去除
* sessionStorage-用于临时保存同一窗口的数据，在关闭窗口或标签页后删除这些数据

```javascript
if(typeof (Storage)!=="undefined"){
    console.log("support localStorage sessionStorage");
}else{
    console.log("no support");
}
```

通用的API-以localStorage为例
* `localStorage.setItem(key,value)` 保存数据
* `localStorage.getItem(key)` 读取数据
* `localStorage.removeItem(key)` 删除单个数据
* `localStorage.clear()` 删除所有数据
* `localStorage.key(index)` 得到某个索引的key
 
### localStorage
**localStorage只在协议、主机名、相同端口号下才能读取和修改同一份localStorage！例如在同一台主机，不可以在百度访问到搜狗设置的localStorage**

点击测试
```html
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>菜鸟教程(runoob.com)</title> 
</head>
<body>
<p><button type="button" id="btn">点我！</button></p>
<script>
function clickCounter()
{
	if(typeof(Storage)!=="undefined")
	{
		if (localStorage.clickcount)
		{
			localStorage.clickcount=Number(localStorage.clickcount)+1;
		}
		else
		{
			localStorage.clickcount=1;
		}
		document.getElementById("result").innerHTML=" 你已经点击了按钮 " + localStorage.clickcount + " 次 ";
	}
	else
	{
		document.getElementById("result").innerHTML="对不起，您的浏览器不支持 web 存储。";
	}
}
document.getElementById("btn").onclick = clickCounter;
</script>
<div id="result"></div>
<p>点击该按钮查看计数器的增加。</p>
<p>关闭浏览器选项卡(或窗口),重新打开此页面,计数器将继续计数(不是重置)。</p>
</body>
</html>
```
> ![alt](img/count.png)

利用localStorage存储网站
```html
<!DOCTYPE html>
<html>  
<head>  
    <meta charset="utf-8">  
    <title>HTML5本地存储之Web Storage篇</title>  
</head>  
<body>  
    <div style="border: 2px dashed #ccc;width:320px;text-align:center;">     
        <label for="sitename">网站名(key)：</label>  
        <input type="text" id="sitename" name="sitename" class="text"/>  
        <br/>  
        <label for="siteurl">网 址(value)：</label>  
        <input type="text" id="siteurl" name="siteurl"/>  
        <br/>  
        <input type="button" onclick="save()" value="新增记录"/>  
        <hr/>  
        <label for="search_phone">输入网站名：</label>  
        <input type="text" id="search_site" name="search_site"/>  
        <input type="button" onclick="find()" value="查找网站"/>  
        <p id="find_result"><br/></p>  
    </div>  
    <br/>  
    <div id="list">  
    </div>  
    <script>
	// 载入所有存储在localStorage的数据
	loadAll(); 	
		
    //保存数据  
    function save(){  
        var siteurl = document.getElementById("siteurl").value;  
        var sitename = document.getElementById("sitename").value;  
        localStorage.setItem(sitename, siteurl);
        alert("添加成功");
    }
    //查找数据  
    function find(){  
        var search_site = document.getElementById("search_site").value;  
        var siteurl = localStorage.getItem(search_site);  
        var find_result = document.getElementById("find_result");  
        find_result.innerHTML = search_site + "的网址是：" + siteurl;  
    }
    //将所有存储在localStorage中的对象提取出来，并展现到界面上
    function loadAll(){  
        var list = document.getElementById("list");  
        if(localStorage.length>0){  
            var result = "<table border='1'>";  
            result += "<tr><td>key</td><td>value</td></tr>";  
            for(var i=0;i<localStorage.length;i++){  
                var sitename = localStorage.key(i);  
                var siteurl = localStorage.getItem(sitename);  
                result += "<tr><td>"+sitename+"</td><td>"+siteurl+"</td></tr>";  
            }  
            result += "</table>";  
            list.innerHTML = result;  
        }else{  
            list.innerHTML = "数据为空……";  
        }  
    }      
    </script>
</body>  
</html>
```
> ![alt](img/locals.png)

### sessionStorage
> 和localStorage方法一致，在关闭浏览器后缓存清除
>

### JSON对象
利用JSON对象的`JSON.stringify(json)`以及`JSON.parse(str)`方法，将json转成字符串存入浏览器，再把数据读取出来

[利用JSON对象作为存储](DOM.html)
