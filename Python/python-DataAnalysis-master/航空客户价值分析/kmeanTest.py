import pandas
"""
data = pandas.read_excel('./data/zscoredata.xls')
new_data = (data - data.mean(axis=0))/(data.std(axis=0))

outout_file = './data/new_zscoredata.xls'
new_data.to_excel(outout_file,header=None,index=False)
"""
import pandas as pd
import numpy as np
from sklearn.cluster import KMeans
import matplotlib.pyplot as plt
from pylab import mpl

# 正常显示中文
mpl.rcParams['font.sans-serif'] = ['FangSong'] # 指定默认字体
mpl.rcParams['axes.unicode_minus'] = False # 解决保存图像是负号'-'显示为方块的问题

# 计算欧式距离
def dist(A, B):
    return np.sum(np.power(A-B,2))

# 测试k取值
def test_Kmeans(data):
    data = data.values
    nums = range(2,10)  #k值范围
    SSE = []
    for num in nums:
        sse = 0
        #构建模型
        kmodel = KMeans(n_clusters=num,n_jobs=2)
        kmodel.fit(data)

        # 获取簇中心
        cluster_ceter_list = kmodel.cluster_centers_
        # 簇类序号
        cluster_list = kmodel.labels_.tolist()
        for index in range(len(data)):
            #计算同簇离心距
            cluster_num  = cluster_list[index]
            sse += dist(data[index,:],cluster_ceter_list[cluster_num])
        print("k="+str(num)+'-sse='+str(sse))
        SSE.append(sse)
    return nums, SSE

# 测试k取值
def test_K_value(data):
    nums, SSE = test_Kmeans(data)
    #画图，通过观察SSE与k的取值尝试找出合适的k值
    # 中文和负号的正常显示
    plt.rcParams['font.sans-serif'] = 'SimHei'
    plt.rcParams['font.size'] = 12.0
    plt.rcParams['axes.unicode_minus'] = False
    # 使用ggplot的绘图风格
    plt.style.use('ggplot')
    ## 绘图观测SSE与簇个数的关系
    fig=plt.figure(figsize=(10, 8))
    ax=fig.add_subplot(1,1,1)
    ax.plot(nums,SSE,marker="+")
    ax.set_xlabel("n_clusters", fontsize=18)
    ax.set_ylabel("SSE", fontsize=18)
    fig.suptitle("KMeans", fontsize=20)
    plt.show()

# 绘制雷达图
def analysis(data, k:int):
    #构建模型
    kmodel = KMeans(n_clusters=k, n_jobs=2)
    kmodel.fit(data)
    class_count = pd.Series(kmodel.labels_).value_counts()  # 统计每个类别的数量
    class_center = pd.DataFrame(kmodel.cluster_centers_)    # 找出聚类中心

    # 找出类中心坐标值中的最大值最小值
    max = class_center.values.max()
    min = class_center.values.min()

    make_data = pd.concat([class_center, class_count], axis=1)
    make_data.columns = list(data.columns) + [u'类别数目']

    # print(make_data.head())

    fig = plt.figure(figsize=(10, 8))
    ax = fig.add_subplot(111, polar=True)
    center_num = make_data.values

    # 特征
    feature = ["客户关系长度", "消费时间间隔", "消费频率", "飞行里程", "折扣系数平均值"]
    N = len(feature)

    for i, v in enumerate(center_num):
        # 设置雷达图角度，用于平分割开一个圆面
        angles = np.linspace(0, 2*np.pi, N, endpoint=False)
        center = np.concatenate((v[:-1],[v[0]]))
        angles = np.concatenate((angles,[angles[0]]))

        ax.plot(angles, center, 'o-', linewidth=2, label='第%d类人群，%d人'%(i+1,v[-1]))
        ax.fill(angles, center, alpha=0.25)
        ax.set_thetagrids(angles*180/np.pi, feature, fontsize=15)
        ax.set_ylim(min-0.1,max+0.1)
        plt.title('客户群特征分析图',fontsize=20)
        ax.grid(True)

        plt.legend(loc='upper right', bbox_to_anchor=(1.3,1.0),ncol=1,
                   fancybox=True,shadow=True)
    plt.show()


# 测试k值
data = pandas.read_excel('./data/new_zscoredata.xls')
# test_K_value(data)
for k in range(4,8):  #4-7
    analysis(data,k=k)


