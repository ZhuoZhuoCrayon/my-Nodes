import pandas as pd
import numpy as np
import matplotlib
import matplotlib.pyplot as plt

"""
（1）绘制所有便利店的10月的客流量折线图。

（2）绘制每类商家10月份的日平均客流量折线图。

（3）选择一个商家，统计每月的总客流量，绘制柱状图。

（4）选择一个商家，统计某个月中，周一到周日的每天平均客流量，并绘制柱状图。

（5）选择一个商家，绘制客流量直方图。
（6）选择一个商家，绘制客流量密度图。

（7）统计某个月各个类别商店总客流量占该月总客流量的比例，绘制饼图。
df = pd.read_csv('000917.csv',encoding='gbk')
df = df[df['涨跌幅']!='None']
df['涨跌幅'] = df['涨跌幅'].astype(np.float64)
"""

shop_data=pd.read_csv("../dataset/shop_payNum_new.csv",parse_dates=True,index_col=0)



#--------------------------------绘制所有便利店的10月的客流量折线图--------------------------------------#

data_onMouth_10=shop_data.loc[shop_data.index.month==10]    #筛选出10月份的数据
footfall=data_onMouth_10.groupby(['shop_id']).sum()         #按shop_id分类并计算每家便利店10月份的客流量
footfall.sort_values(by="shop_id",ascending="false")        #按shop_id排序

_x=footfall.index;      #shop_id作为x轴
_y=footfall.values;     #客流量作为y轴


plt.figure(figsize=(16,10),dpi=100)     #设置图尺寸
plt.xticks(range(len(_x))[::3],_x[::3].astype(int))         #设置x轴长度，因为id太多，每隔3个进行显示
# plt.xticks(x,xtk,size=12,rotation=50) #设置字体大小和字体倾斜度
#在折线上显示每个id及对应值
for x,y in zip(range(len(_x)),_y):
    plt.text(x,y,str(_x[x])+","+str(y),ha='center',size=6)
plt.xlabel('shop id',size=15)
plt.ylabel('footfall of every shop',size=15)
plt.title('work[1]:footfall on Oct',size=20)

plt.plot(range(len(_x)),_y)
plt.grid()
plt.show()      #显示图表

#--------------------------------------------------------------------------------------------------#


#-------------------------------绘制每类商家10月份的日平均客流量折线图----------------------------------#
"""
# 对客流量按类别归类，取每类平均
footfall_mean=data_onMouth_10['pay_num'].groupby(data_onMouth_10['cate_2_name']).mean()

# 绘制折线图
footfall_mean.plot(kind='line')
# 设置标签及标题
plt.xlabel('cate_2_name',size=15)
plt.ylabel('the mean of footfall',size=15)
plt.title('work[2]:the mean of footfall of every type on Oct',size=20)
plt.show()
"""
#-------------------------------------------------------------------------------------------------#

#-----------------------------选择一个商家，统计每月的总客流量，绘制柱状图--------------------------------#
"""
shop14_data=shop_data[shop_data.shop_id==14]    # 取出id为14的商家
# 对客流量按月份归类求和
shop14_data=shop14_data['pay_num'].groupby(shop14_data.index.month).sum()

# 绘图
_x=shop14_data.index
_y=shop14_data.values

plt.figure(figsize=(16,10),dpi=80)  #尺寸
plt.bar(range(len(_x)),_y)  #绘制柱状图

# 添加数值
for x,y in zip(range(len(_x)),_y):
    plt.text(x,y+5,str(y),ha='center',size=12)

plt.xticks(range(len(_x)),_x)
plt.yticks(range(max(_y)+50)[::150])    #设置y轴间隔为150
plt.xlabel("MONTH",size=15)
plt.ylabel("total footfall",size=15)
plt.title("NO.14 Shop Footfall Every Month",size=20)
plt.show()
"""
#-------------------------------------------------------------------------------------------------#

#----------------------------------选择一个商家，绘制客流量直方图--------------------------------------#
"""
shop14_data=shop_data[shop_data.shop_id==14]['pay_num']     #筛选出id14的客流量数据

_x=shop14_data.index
_y=shop14_data.values

#画图
plt.figure(figsize=(16,10),dpi=80)
plt.bar(range(len(_x)),_y)
plt.xticks(range(len(_x))[::30],_x[::30].astype(str))   #日期数量太多，每隔30个显示
plt.xlabel("TIME",size=15)
plt.ylabel("FOOTFALL",size=15)
plt.title("NO.14 Shop Footfall",size=20)
plt.show()
"""
#-------------------------------------------------------------------------------------------------#

#----------------------------------选择一个商家，绘制客流量密度图-------------------------------------#
"""
shop14_data=shop_data[shop_data.shop_id==14]['pay_num']     #筛选出id14的客流量数据
shop14_data.plot(x='SHOP 14',kind="kde")        #绘制密度图
plt.show()
"""
#-------------------------------------------------------------------------------------------------#

#------------------统计某个月各个类别商店总客流量占该月总客流量的比例，绘制饼图-----------------------------#
footfall_class=data_onMouth_10['pay_num'].groupby(data_onMouth_10['cate_2_name']).sum()
footfall_rate=footfall_class/footfall_class.sum()

footfall_rate.plot(kind='pie')
plt.title("Different Type Shop Footfall Rate",size=20)
plt.show()
#-------------------------------------------------------------------------------------------------#