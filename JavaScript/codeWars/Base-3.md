## Directions Reduction

### Information
* TIME: 2019/08/29
* LINK: [Click Here](https://www.codewars.com/kata/550f22f4d758534c1100025a/solutions/javascript)
* TAG: `RE` `ARRAYS`

### Description
> 如果南北相邻或者东西相邻视为无用功，去除这些无用功

### Example
```text
["NORTH", "SOUTH", "SOUTH", "EAST", "WEST", "NORTH", "WEST"]
```
> ["WEST"]

### My Answer
> 运用堆栈的思想，如果栈顶和即将入栈的元素反方向，就可以消去栈顶，否则该元素入栈
```javascript 1.8
function dirReduc(arr){
  // ...
  console.log(arr);
  var res=[arr[0]];
  for(var i=1;i<arr.length;i++){
    if(res.length&&(res[res.length-1][0]==='N'&&arr[i][0]==='S'||
       res[res.length-1][0]==='S'&&arr[i][0]==='N'||
       res[res.length-1][0]==='W'&&arr[i][0]==='E'||
       res[res.length-1][0]==='E'&&arr[i][0]==='W')){
       res.pop();
    }else{
       res.push(arr[i]);
    }
  }
  return res;
}
```

### Best Answer
> 利用`reduce`迭代
```javascript 1.8
function dirReduc(plan) {
  var opposite = {
    'NORTH': 'SOUTH', 'EAST': 'WEST', 'SOUTH': 'NORTH', 'WEST': 'EAST'};
  return plan.reduce(function(dirs, dir){
      if (dirs[dirs.length - 1] === opposite[dir])
        dirs.pop();
      else
        dirs.push(dir);
      return dirs;
    });
}
```
> 利用正则表达式
```javascript 1.8
function dirReduc(arr) {
  var str = arr.join(''), pattern = /NORTHSOUTH|EASTWEST|SOUTHNORTH|WESTEAST/;
  while (pattern.test(str)) str = str.replace(pattern,'');
  return str.match(/(NORTH|SOUTH|EAST|WEST)/g)||[];
}
```

---

## KATA-NAME

### Information
* TIME: 2019/08/29
* LINK: [Click Here](https://www.codewars.com/kata/558fc85d8fd1938afb000014/solutions/javascript)
* TAG: `ARRAYS`

### Description
> 求出一个数组中最小两个数的和

### Example
```text
[19, 5, 42, 2, 77]
```
> 7

### My Answer
> 对序列升序，求前两个元素和
```javascript 1.8
function sumTwoSmallestNumbers(numbers) {  
  //Code here
  var sort_nums = numbers.sort((x,y)=>x>y);
  return sort_nums[0]+sort_nums[1];
}
```
