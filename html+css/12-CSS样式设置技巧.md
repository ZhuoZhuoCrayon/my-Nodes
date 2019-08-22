## 12-CSS样式设置技巧

### 水平居中设置

行内元素
> 通过给父元素设置`text-align:center`实现

定宽块状元素:块状元素的宽度width为固定值
> 设置左右的margin值为`auto` 实现居中
>
> 宽度`width`必须设置!
>
> ```css
> margin-left:auto;
> margin-right:auto;
> /*简写*/
> margin:20px auto;
> ```


不定宽块状元素：块状元素的宽度width不固定

方法1：加入table标签
> 利用table标签的长度自适应性---即不定义其长度也不默认父元素body的长度
>
> 因此可以看做一个定宽度块元素，然后再利用定宽度块状居中的margin的方法，使其水平居中
```html
<style>
table{
    border:1px solid;
    margin:0 auto;
}
</style>

<div>
 <table>
  <tbody>
    <tr><td>
    <ul>
        <li>我是第一行文本</li>
        <li>我是第二行文本</li>
        <li>我是第三行文本</li>
    </ul>
    </td></tr>
  </tbody>
 </table>
</div>
```

方法2：设置为行内元素
> 设置需居中元素 `display:inline`
>
> 在其父元素使用 `text-align:center`
>
> 优势是不用增加无语义标签，但也存在着一些问题：它将块状元素的 display 类型改为 inline，变成了行内元素，所以少了一些功能，比如设定长度值

方法3：设置浮动和相对定位来实现
> 在父元素设置
> ```css
>    float:left;
>    position:relative;
>    left:50%
> ```
>
> 居中元素设置
> ```css
>   position:relative;
>   left:-50%;
> ```

### 垂直居中设置

**父元素高度确定的多行文本**

方法1：利用table
>  table  (包括tbody、tr、td)标签，同时设置 vertical-align：middle

```html
<style>
table td{height:500px;background:#ccc}
</style>

<body>
<table><tbody><tr><td class="wrap">
<div>
    <p>看我是否可以居中。</p>
</div>
</td></tr></tbody></table>
</body>
```
> td 标签默认情况下就默认设置了 vertical-align 为 middle，所以我们不需要显式地设置了

方法2：设置为表格单元显示
> 在 chrome、firefox 及 IE8 以上的浏览器下可以设置块级元素的 display 为 table-cell,激活 vertical-align 属性，但注意 IE6、7 并不支持这个样式, 兼容性比较差

```html
<style>
.container{
    height:300px;
    background:#ccc;
    display:table-cell;/*IE8以上及Chrome、Firefox*/
    vertical-align:middle;/*IE8以上及Chrome、Firefox*/
}
</style>

<div class="container">
    <div>
        <p>看我是否可以居中。</p>
        <p>看我是否可以居中。</p>
        <p>看我是否可以居中。</p>
    </div>
</div>
```
> 这种方法的好处是**不用添加多余的无意义的标签**，但缺点也很明显，它的兼容性不是很好，不兼容 IE6、7而且这样修改display的block变成了table-cell，破坏了原有的块状元素的性质

隐性改变display类型
> 当元素设置为`position : absolute` 或 `float:right/left`，元素的display显示类型就会自动变为以 display:inline-block（块状元素）的方式显示，当然就可以设置元素的 width 和 height 了，且默认宽度不占满父元素
