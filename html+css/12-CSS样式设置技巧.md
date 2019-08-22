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

