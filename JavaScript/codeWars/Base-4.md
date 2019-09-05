## Put the exclamation marks and question marks to the balance, Are they balanced?

### Information
* TIME: 2019/09/05
* LINK: [Click Here](https://www.codewars.com/kata/57fb44a12b53146fe1000136/train/javascript)
* TAG: 

### Description
> 字符串权值有`!-2`和`?-3`，求解给定两个字符串的权重关系，返回重的一边或平衡

### Example
```text
balance("!!","??") === "Right"
balance("!??","?!!") === "Left"
balance("!?!!","?!?") === "Left"
balance("!!???!????","??!!?!!!!!!!") === "Balance"
```

### My Answer
> 切割字符串然后map求出权值数组，reduce求和
```javascript
/*input your code*/
function balance(left,right){
     function sum(str){return str.split('').map(e=>e==='!'?2:3).reduce((x,y)=>x+y);}
     var 
         leftWeight = sum(left),
         rightWeight = sum(right);
     if(leftWeight-rightWeight===0)return 'Balance';
     return leftWeight>rightWeight?'Left':'Right';
}
```

### Best Answer
> `[...str]`等价于`str.split('')`
>
> 解决rudece的初值问题`s+(a=='?'?3:2),0)`
>
> 三元组嵌套 `l==r?"Balance":l>r?"Left":"Right"`
```javascript
/*code*/
function balance(left,right){
   let l=[...left].reduce((s,a)=>s+(a=='?'?3:2),0);
   let r=[...right].reduce((s,a)=>s+(a=='?'?3:2),0);
   return l==r?"Balance":l>r?"Left":"Right"
}
```

### Extend & Reference
> ` exclamation` n.感叹；感悟语；感叹词

---
## Break camelCase

### Information
* TIME: 2019/09/05
* LINK: [Click Here](https://www.codewars.com/kata/5208f99aee097e6552000148/solutions/javascript)
* TAG: `STRINGS`

### Description
> 给定一个字符串，在所有大写字符的前面插入空格

### Example
```text
solution('camelCasing')
```
> 'camel Casing'

### My Answer
> 利用正则表达式的全局搜索模式逐步替换大写字符
> 由于字符串的变化会导致正则搜索每次从新开始，于是需要备份
```javascript
// complete the function
function solution(string) {
    var re = /[A-Z]/g,
        res = string;
    while(true){
        var char = re.exec(string);
        console.log(char)
        if(char===null)
            break;
        res = res.replace(char,' '+char);
        re.lastIndex;
    }
    return res;
}
```

### Best Answer
> 用`$n`表示匹配到的字段的第n个字符...然后直接替换
```javascript
// complete the function
function solution(string) {
  return(string.replace(/([A-Z])/g, ' $1'));

}
```

---
## Buying a car

### Information
* TIME: 2019/09/05
* LINK: [Click Here](https://www.codewars.com/kata/buying-a-car/solutions/javascript?show-solutions=1)
* TAG: `MATHEMATICS`

### Best Answer
> 这是一道神奇的数学题，于是让我们只学习下`Math.round()`
```javascript
function nbMonths(startPriceOld, startPriceNew, savingperMonth, percentLossByMonth){
  var months = 0, moneySaved = 0;
  while (startPriceNew > startPriceOld + moneySaved){
    moneySaved += savingperMonth;
    startPriceOld -= (startPriceOld * (percentLossByMonth / 100));
    startPriceNew -= (startPriceNew * (percentLossByMonth / 100));
    months++;
    if (months % 2 == 1){
      percentLossByMonth += .5;
    }
  }
  return [months, Math.round(startPriceOld + moneySaved - startPriceNew)];
}
```

### Extend & Reference
> [Math.round()](https://www.w3school.com.cn/jsref/jsref_round.asp)