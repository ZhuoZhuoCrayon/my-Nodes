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
