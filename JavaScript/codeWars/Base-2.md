## Count of positives / sum of negatives

### Information
* TIME: 2019/08/27
* LINK: [Click Here](https://www.codewars.com/kata/count-of-positives-slash-sum-of-negatives/train/javascript)
* TAG: `NUMBERS` `ARRAYS`

### Description
> 求一个数组中正数的数量以及负数的和，用一个数组返回，如下

### Example
```text
[1, 2, 3, 4, 5, 6, 7, 8, 9, 10, -11, -12, -13, -14, -15]
```
> [10, -65]

### My Answer
> 利用`filter`分别筛出正负
>
> 特判情况，数组为null或者空，直接返回空数组 
```javascript 1.8
function countPositivesSumNegatives(input) {
    if(input===null||input.length===0)
        return [];
    pos_sum = input.filter(s=>s>0).length;
    neg_arr = input.filter(s=>s<0);
    var neg_sum = neg_arr.length===0? 0:neg_arr.reduce((x,y)=>x+y)
    return [pos_sum, neg_sum];
}
```
---

## Sort the odd

### Information
* TIME: 2019/08/27
* LINK: [Click Here](https://www.codewars.com/kata/578aa45ee9fd15ff4600090d/solutions/javascript/all/best_practice)
* TAG: `ARRAYS`

### Description
> 一个数组，偶数（包括0）位置不变，对奇数进行排序

### Example
```text
[5, 3, 2, 8, 1, 4]
```
> [1, 3, 2, 8, 5, 4]

### My Answer
> 筛出奇数进行排序，然后把原数组的偶数重新插入
```javascript 1.8
function sortArray(array) {
  if(array.length===0)
    return array;
  odds = array.filter(x=>x%2===1).sort((x,y)=>x>=y);
  for(var i=0;i<array.length;i++)
    if(array[i]%2===0)
      odds.splice(i,0,array[i]);
  return odds;
}
```

### Best Answer
> 把odd声明为const，就可以在map中调用
>
> 在重组的时候，up主用了map和shift(删除数组头部元素并返回该元素)，遍历原数组，遇到奇数就用已经排好序的odd数组的
> 元素插入
```javascript 1.8
function sortArray(array) {
  const odd = array.filter((x) => x % 2).sort((a,b) => a - b);
  return array.map((x) => x % 2 ? odd.shift() : x);
}
```
---

## Who likes it?

### Information
* TIME: 2019/08/27
* LINK: [Click Here](https://www.codewars.com/kata/5266876b8f4bf2da9b000362/solutions/javascript)
* TAG: `ARRAYS` `FORMATTING`

### Description
> 给出一个名称数组，格式化点赞数字符串，如样例

### Example
```text
likes [] // must be "no one likes this"
likes ["Peter"] // must be "Peter likes this"
likes ["Jacob", "Alex"] // must be "Jacob and Alex like this"
likes ["Max", "John", "Mark"] // must be "Max, John and Mark like this"
likes ["Alex", "Jacob", "Mark", "Max"] // must be "Alex, Jacob and 2 others like this"
```



### Best Answer
> 运用对象数组，分为5种情况
```javascript 1.8
function likes(names) {
  return {
    0: 'no one likes this',
    1: `${names[0]} likes this`, 
    2: `${names[0]} and ${names[1]} like this`, 
    3: `${names[0]}, ${names[1]} and ${names[2]} like this`, 
    4: `${names[0]}, ${names[1]} and ${names.length - 2} others like this`, 
  }[Math.min(4, names.length)]
}
```

> 运用`switch`
```javascript 1.8
function likes(names) {
  names = names || [];
  switch(names.length){
    case 0: return 'no one likes this'; break;
    case 1: return names[0] + ' likes this'; break;
    case 2: return names[0] + ' and ' + names[1] + ' like this'; break;
    case 3: return names[0] + ', ' + names[1] + ' and ' + names[2] + ' like this'; break;
    default: return names[0] + ', ' + names[1] + ' and ' + (names.length - 2) + ' others like this';
  }
}
```
---
## Duplicate Encoder

### Information
* TIME: 2019/08/28
* LINK: [Click Here](https://www.codewars.com/kata/54b42f9314d9229fd6000d9c/solutions/javascript/all/best_practice)
* TAG: `AZRRAYS` `STRING`

### Description
> 给定单词，忽略大小写，如果字符在字符串其他地方没有出现，修改为'(' 否则')'

### Example
```text
"din"      =>  "((("
"recede"   =>  "()()()"
"Success"  =>  ")())())"
"(( @"     =>  "))((" 
```

### My Answer
> 坑：原本想利用`splice`把当前遍历到的元素删除，后来发现这个方法是在原数组中操作的！！！，即使用临时变量也不能解决：）
>
> 最后用`slice`切片分析，如果没有出现，则'('否则')'
```javascript 1.8
function duplicateEncode(word){
    // ...
    var word_arr = word.toLowerCase().split('');
    var res = word_arr.map((x,it,wd)=>
        wd.slice(0,it).indexOf(x)+wd.slice(it+1).indexOf(x)===-2?'(':')');
    return res.join('');
}

```

### Best Answer
> 原来有`lastIndexOf`...
```javascript 1.8
function duplicateEncode(word){
  return word
    .toLowerCase()
    .split('')
    .map( function (a, i, w) {
      return w.indexOf(a) == w.lastIndexOf(a) ? '(' : ')'
    })
    .join('');
}
```

### Extend & Reference
> `convert`  兑换