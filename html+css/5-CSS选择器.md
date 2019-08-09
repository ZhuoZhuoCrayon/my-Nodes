## 5-CSS选择器

### 选择器
css样式声明如下
```text
选择器{
    样式;
}
```
> `{}`之前的部分就是选择器
> 
> 选择器指明了`{}`中**样式**的作用对象，也就是`样式·作用了网页中的哪些元素

```html
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>选择器</title>
<style type="text/css">
body{
    font-size:12px;
    color:red;	
}
</style>
</head>
<body>
<p>慕课网（IMOOC）是学习编程最简单的免费平台。慕课网提供了丰富的移动端开发、php开发、web前端、html5教程以及css3视频教程等课程资源。它富有交互性及趣味性，并且你可以和朋友一起编程。</p>
</body>
</html>
```
> `body`就是选择器
>
> `font-size:12px;color:red;`就是样式，作用于**选择器-body**
