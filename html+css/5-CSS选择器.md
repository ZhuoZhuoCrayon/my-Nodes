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

### 标签选择器

```html
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>认识html标签</title>
<style type="text/css">
h1{
    font-weight:normal;
    color:red;
}
</style>
</head>
<body>
    <h1>勇气</h1>
    <p>三年级时，我还是一个胆小如鼠的小女孩，上课从来不敢回答老师提出的问题，生怕回答错了老师会批评我。就一直没有这个勇气来回答老师提出的问题。学校举办的活动我也没勇气参加。</p>
    <p>到了三年级下学期时，我们班上了一节公开课，老师提出了一个很简单的问题，班里很多同学都举手了，甚至成绩比我差很多的，也举手了，还说着："我来，我来。"我环顾了四周，就我没有举手。</p>
    <img src="http://img.mukewang.com/52b4113500018cf102000200.jpg" >
</body>
</html>
```
> 通过选择`html代码`中的**标签**，如上代码的`<html>` `<body>` `<h1>` `<p>` `<img>`

### 类选择器

语法
* **英文圆点开头**
* `类选器`名称任起，但不能是中文
```text
.类选器名称{css样式代码;}
```
使用方法
> 用合适的标签对修饰内容进行标记
>```text
><span>胆小如鼠</span>
>```
> 使用`class`为标签设置一个类，**类可以修饰多个标签**
>```text
><span class="stress">胆小如鼠</span>
>```
> 设置类选器css样式
> ```text
> .stress{color:red;}/*类前面要加入一个英文圆点*/
>```

### ID选择器

与类选择器的区别
* 使用`#`号选择
* 为标签设置为`id="idname"`

```css
#setGreen{
   color:green;
}
```
