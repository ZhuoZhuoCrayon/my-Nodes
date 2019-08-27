## Counting sheep

### Information
* TIME: 2019/08/27
* LINK: [Click Here](hhttps://www.codewars.com/kata/54edbc7200b811e956000556/solutions/javascript/me/best_practice)
* TAG: `ARRAYS`

### Description
> 对数组中的`true`计数

### Example
```text
[true,  true,  true,  false,
  true,  true,  true,  true ,
  true,  false, true,  false,
  true,  false, false, true ,
  true,  true,  true,  true ,
  false, false, true,  true]
```
> 17

### My Answer
> 用`filter()`筛出符合条件的数组，数组答案即为结果
```javascript 1.8
/*input your code*/
function countSheeps(arrayOfSheep) {
  // TODO May the force be with you
    var res = arrayOfSheep.filter(function(element){
      return element;
    });
    return res.length;
}
```

### Best Answer
> 借用`filter`，采用内置类型`Boolean`过筛
```javascript 1.8
function countSheeps(arrayOfSheeps) {
  return arrayOfSheeps.filter(Boolean).length;
}
```


---

## Consecutive strings

### Information
* TIME: 2019/08/27
* LINK: [Click Here](https://www.codewars.com/kata/56a5d994ac971f1ac500003e/solutions/javascript)
* TAG: 

### Description
> 求一个k宽滑动窗口内，字符串元素组成的最长字符串

### Example
```text
longest_consec(["zone", "abigail", "theta", "form", "libe", "zas", "theta", "abigail"], 2) 
```
> "abigailtheta"

### My Answer
> 遍历每一个滑动窗口，用reduce迭代出滑动窗口的字符串和，更新最长字符串
```javascript 1.8
function longestConsec(strarr, k) {
    // your code
    if(strarr.length===0||k>strarr.length||k<=0)
        return "";
    var 
      max_length=0,
      res="";
    for(var i=0;i<=strarr.length-k;i++){
        var subarr = strarr.slice(i,i+k);
        var str = subarr.reduce(function(x,y){
            return x+y;
        });
        if(str.length>max_length){
            max_length = str.length
            res = str;
        }
    }
    return res;
}
```

### Best Answer
> 首先，`join()`可以合成字符串！！！
>
> up主用map遍历滑动窗口，求出每个窗口的字符串长度。最后用max加上indexOf定位最长的窗口
>
> 涉及到`=>`，下面引用给出用法
```javascript 1.8
function longestConsec(strarr, k) {
  if( strarr.length==0 || k> strarr.length || k <1 ) return "";
  let lens = strarr.map( (_,i,arr) => arr.slice(i,i+k).join('').length ),
      i = lens.indexOf( Math.max(...lens) );
  return strarr.slice(i,i+k).join('')
}
```

### Extend & Reference
[JS中=>的使用](https://www.cnblogs.com/twoheads/p/9876396.html)