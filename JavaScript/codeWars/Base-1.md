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

---

## Format a string of names like 'Bart, Lisa & Maggie'.

### Information
* TIME: 2019/08/27
* LINK: [Click Here](https://www.codewars.com/kata/53368a47e38700bd8300030d/solutions/javascript)
* TAG: `STRINGS` `FORMATTING`

### Description
> 给定一个含有name属性的对象的对象数组，为name设置字符串格式，如下样例

### Example
```text
list([ {name: 'Bart'}, {name: 'Lisa'}, {name: 'Maggie'} ])
// returns 'Bart, Lisa & Maggie'

list([ {name: 'Bart'}, {name: 'Lisa'} ])
// returns 'Bart & Lisa'

list([ {name: 'Bart'} ])
// returns 'Bart'

list([])
// returns ''
```
> 如上

### My Answer
> 利用`join`把前n-1个name先用`, `进行间隔，再特判加` & `的情况
```javascript 1.8
function list(names){
  //your code here
  if(names.length===0)
    return "";
  if(names.length===1)
    return names[names.length-1].name;
  var res = names.map(e=>e.name).slice(0,names.length-1).join(", ");
  return res + " & " + names[names.length-1].name;
}
```

### Best Answer
> 和我的思路一样，采用特判三元组显得代码很简洁
```javascript 1.8
function list(names) {
  var xs = names.map(p => p.name)
  var x = xs.pop()
  return xs.length ? xs.join(", ") + " & " + x : x || ""
}
```

----

## Are they the "same"?

### Information
* TIME: 2019/08/27
* LINK: [Click Here](https://www.codewars.com/kata/550498447451fbbd7600041c/solutions/javascript)
* TAG: `ARRAYS`

### Description
> 求解a数组平方运算后是否和b数组元素全部重合，不考虑元素顺序

### Example
```text
a = [121, 144, 19, 161, 19, 144, 19, 11]  
b = [121, 14641, 20736, 361, 25921, 361, 20736, 361]
```
> true
> 
> a = [121, 144, 19, 161, 19, 144, 19, 11] 
> 
> b = [11*11, 121*121, 144*144, 19*19, 161*161, 19*19, 144*144, 19*19]

### My Answer
> 对数组1进行平方运算，对1，2数组进行排序（我这里直接用默认排序），因为只要保证对应顺序就ok
```javascript 1.8
function comp(array1, array2){
  if(array1===null || array2===null)
    return false;
  array1 = array1.map(e=>e*e).sort();
  array2 = array2.sort();
  for(var i=0;i<array1.length;i++)
    if(array2[i]!==array1[i])
      return false;
  return true;
}
```

### Best Answer
> 思路一样，借鉴up主在比较时用`every()`
```javascript 1.8
function comp(array1, array2) {
  if(array1 == null || array2 == null) return false;
  array1.sort((a, b) => a - b); array2.sort((a, b) => a - b);
  return array1.map(v => v * v).every((v, i) => v == array2[i]);
}
```

----