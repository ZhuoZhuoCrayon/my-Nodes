## Integers: Recreation One

### Information
* TIME: 2019/09/06
* LINK: [Click Here](https://www.codewars.com/kata/55aa075506463dac6600010d/solutions/javascript)
* TAG: `ALGORITHMS`

### Description
> 给定m,n，求解在这个范围内的所有 因数平方和是一个平方数的 数

### Example
```text
list_squared(1, 250) --> [[1, 1], [42, 2500], [246, 84100]]
list_squared(42, 250) --> [[42, 2500], [246, 84100]]
```

### My Answer
> 求出每个数所有的因数各自平方求和，然后对和开平方，按`round()`取整后和原平方数对比
```javascript
function listSquared(m, n) {
    // your code
    var 
        divisors = [],
        squared = 0;
    var res = [];
    for(var num = m;num<=n;num++){
        divisors = [];
        for(var i=1;i<=num;i++){
            if(num%i===0)
                divisors.push(i);
        }
        squared = divisors.map(num=>num*num).reduce((x,y)=>x+y);
        if(Math.abs(Math.round(Math.sqrt(squared))-Math.sqrt(squared))<0.00001){
            console.log(Math.sqrt(squared)+" "+Math.round(Math.sqrt(squared)));
            res.push([num,squared]);
        }
    }
    return res;
}
```

### Best Answer
> 直接在循环中算因数的平方和，利用取余的方式验证开方后是否是这个整数`Math.sqrt(temp) % 1 == 0`
```javascript
function listSquared(m, n) {
  var arr = [];
  for (var i = m; i <= n; i++){
    var temp = 0;
    for (var j = 1; j <= i; j++) {
      if ( i % j == 0) temp += j*j;  
    };
    if ( Math.sqrt(temp) % 1 == 0) arr.push([i, temp]);
  };
  return arr;
}
```


## Explosive Sum

### Information
* TIME: 2019/09/06
* LINK: [Click Here](https://www.codewars.com/kata/explosive-sum/train/javascript)
* TAG: `DP`

### Description
> 整数分拆，把一个数拆成若干数之和

### Example
```text
sum(1) // 1
sum(2) // 2  -> 1+1 , 2
sum(3) // 3 -> 1+1+1, 1+2, 3
sum(4) // 5 -> 1+1+1+1, 1+1+2, 1+3, 2+2, 4
sum(5) // 7 -> 1+1+1+1+1, 1+1+1+2, 1+1+3, 1+2+2, 1+4, 5, 2+3
```

### My Answer
> 递推式如下
> 
> ![alt](../img/0906.png)
```javascript
function sum(num) {
    var dp = [];
    for(var i=1;i<=num;i++){
        dp[i] = [];
        for(var j=1;j<=num;j++){
            if(i===1||j===1){
                dp[i][j] = 1;
            }else if(i===j){
                dp[i][j] = dp[i][j-1] + 1;
            }else if(i>j){
                dp[i][j] = dp[i][j-1] + dp[i-j][j];
            }else{
                dp[i][j] = dp[i][i];
            }
        }
    }
    return dp[num][num];
}
```
