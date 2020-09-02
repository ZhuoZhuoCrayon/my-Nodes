#-*- coding: utf-8 -*-
import pandas as pd
from scipy.interpolate import lagrange

# 读取数据，由于数据没有类名，于是header设置为None
data=pd.read_excel('../data/missing_data.xls',header=None)
dataBasic=data.describe()   #输出数据的基本情况
print(dataBasic)

# 数据长度
data_len=len(data)

# 打印每列缺失值情况
for i in dataBasic.columns:
    print("INDEX-"+str(i)+" LOSS "+str(int(data_len-dataBasic[i][0])))

# 拉格朗日补值法
def ployinterp_column(s,n,k=5):
    y=s[list(range(n-k,n))+list(range(n+1,n+1+k))]
    y=y[y.notnull()]    #去掉空值
    return lagrange(y.index,list(y))(n)     #返回插值结果

"""
拉格朗日函数的使用
x=[1,2,3,4,7]
y=[5,7,10,3,9]
a=lagrange(x,y)
print(a)
print(a(1),a(2),a(3))
print(a[1],a[2],a[3])

        4         3         2
0.5472 x - 7.306 x + 30.65 x - 47.03 x + 28.13      插值函数

5.000000000000007 7.000000000000014 10.00000000000005       以括号索引输出x对应的值
-47.02777777777778 30.65277777777778 -7.3055555555555545    以[]索引输出的是x的一次方，二次方，...的值
"""
# data[i].isnull()  检查data第i列的值是否为空，返回一个bool值列表
for i in data.columns:
    for j in range(len(data)):
        if(data[i].isnull())[j]:
            data.loc[j,i]=ployinterp_column(data[i],j)  #在空值部分插值

outputfile='../data/complete_data.xls'
data.to_excel(outputfile,header=None,index=False)	#插值结果写入文件
