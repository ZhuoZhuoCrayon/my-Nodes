import pandas as pd
import numpy as np
import matplotlib
import matplotlib.pyplot as plt
"""
“pima.csv”数据前 9个字段的含义：    
(1) Number of times pregnant    
(2) Plasma glucose concentration a 2 hours in an oral glucose tolerance test 
(3) Diastolic blood pressure (mm Hg)    
(4) Triceps skin fold thickness (mm)    
(5) 2-Hour serum insulin (mu U/ml)    
(6) Body mass index (weight in kg/(height in m)^2)    
(7) Diabetes pedigree function    
(8) Age (years)    
(9) Class variable (0 or 1) 

（1）怀孕次数
（2）口服葡萄糖耐量试验中2小时的血糖浓度
（3）舒张压（mm Hg）
（4）三头肌皮褶厚度（mm）
（5）2小时血清胰岛素（μU/ml）
（6）体重指数（体重kg/（身高m）^2）
（7）糖尿病谱系功能
（8）年龄（年）
（9）类变量（0或1）
 
b) 实验要求： 
参考案例二完成以下任务： 
（1）任选两个字段绘制散点图。 
（2）使用全部或者部分特征绘制散布图。 
（3）绘制调和曲线图。 
"""
# Number of times pregnant,Plasma glucose concentration,
# Diastolic blood pressure,Triceps skin fold thickness,
# 2-Hour serum insulin,Body mass index,
# Diabetes pedigree function,Age,Class variable
pima_data=pd.read_csv("../dataset/pima.csv",parse_dates=True,index_col=0)
#------------------------------------------------------------------------任选两个字段绘制散点图---------------------------------------------------------------------#
"""
averageAge=pima_data['Age'].mean()      #计算年龄平均值

#画出高于平均年龄的血糖浓度和胰岛素浓度散点图
ax=pima_data[pima_data['Age']>=averageAge].plot(x='Plasma glucose concentration',y='2-Hour serum insulin',kind='scatter',c='red',ax=None,label='Above average age')
#画出低于平均年龄的~
pima_data[pima_data['Age']<averageAge].plot(x='Plasma glucose concentration',y='2-Hour serum insulin',kind='scatter',c='blue',ax=ax,label='Below average age')
#年龄对胰岛素分泌和血糖降解的区分度
plt.title("Distinction between Age and Insulin Secretion and Glucose Degradation",size=20)
plt.show()
"""
#---------------------------------------------------------------------------------------------------------------------------------------------------------------#

#------------------------------------------------------------------------使用全部或者部分特征绘制散布图--------------------------------------------------------------------#
"""
color={0:'red',1:'green'}
pd.plotting.scatter_matrix(pima_data.iloc[:,:-1],diagonal='kde',marker='0',s=40,alpha=0.4,c=pima_data['Class variable'].apply(lambda x:color[x]))
plt.show()
"""
#---------------------------------------------------------------------------------------------------------------------------------------------------------------#

pd.plotting.andrews_curves(pima_data,'Class variable')
plt.show()

