from random import shuffle
import pandas as pd
import numpy as np
from sklearn.tree import DecisionTreeClassifier #导入决策树模型
from sklearn.metrics import confusion_matrix    #导入混淆矩阵
import matplotlib.pyplot as plt
from sklearn.metrics import roc_curve   #导入ROC曲线函数

"""
绘制混淆矩阵
label:数据类别标签 predict:训练后的归类标签
title:标题
"""
def get_cm(label,predict,title='None'):
    cm = confusion_matrix(label, predict)
    plt.matshow(cm, cmap=plt.cm.Greens)
    plt.colorbar()
    for x in range(len(cm)):
        for y in range(len(cm)):
            plt.annotate(cm[x, y], xy=(x, y), horizontalalignment='center',
                         verticalalignment='center')
    plt.title(title,size=20)
    plt.show()

"""
绘制ROC曲线
神经网络和决策树允许缺省
test:测试数据集 net:神经网络 tree_model:决策树
titile:标题
"""
def get_roc(test,net,tree_model,title='None'):
    if net!='None':
        fpr1, tpr1, thresholds1 = roc_curve(test[:, 3], net.predict(test[:, :3]).reshape(len(test)),pos_label=1)
        plt.plot(fpr1, tpr1, linewidth=2, label='ROC of LM', color='blue')  # 作出ROC曲线
    if tree_model!='None':
        fpr2, tpr2, thresholds2 = roc_curve(test[:, 3], tree_model.predict_proba(test[:, :3])[:, 1], pos_label=1)
        plt.plot(fpr2, tpr2, linewidth=2, label='ROC of CART', color='green')

    plt.xlabel('False Positive Rate')  # 坐标轴标签
    plt.ylabel('True Positive Rate')  # 坐标轴标签
    plt.ylim(0, 1.05)  # 边界范围
    plt.xlim(0, 1.05)  # 边界范围
    plt.legend(loc=4)  # 图例
    plt.title(title, size=20)
    plt.show()  # 显示作图结果


"""
该模块用于解决决策树模型图无法显示中文的问题
dot_data:决策树的文本
"""
import os
def deal_chinese(dot_data):
    with open('dot_data.dot', 'w', encoding='utf-8') as f:  ##将生成树写入，因为含有中文，所以encoding='utf-8'
        f.writelines(dot_data)  ##另外，需要注意，这里的生成格式可以有很多种，常见的有    .dot  .txt
    import codecs
    txt_dir = 'dot_data.dot'
    txt_dir_utf8 = 'dot_data_utf8.dot'

    # 通过修改决策树文本，替换字体，替换成 Microsoft YaHei
    with codecs.open(txt_dir, 'r',encoding='utf-8') as f, codecs.open(txt_dir_utf8, 'w', encoding='utf-8') as wf:
        for line in f:
            newline = line.replace('node [shape=box] ;', 'edge [fontname="Microsoft YaHei"];\n'
                                                         'node [shape=box, fontname="Microsoft YaHei" size="20,20"];')
            wf.write(newline)

    # 读取支持中文显示的决策树结构
    with open('dot_data_utf8.dot', encoding='utf-8') as f:
        dot_graph = f.read()

    #删除临时文件
    os.remove(txt_dir)
    os.remove(txt_dir_utf8)

    return dot_graph
#-----------------------------------------------------------------------------------------------------------------------



"""------------------------------------------------CART--------------------------------------------------------------"""
model_data=pd.read_excel('../data/model.xls')   #数据读入
data=model_data.as_matrix()     #数据矩阵化
shuffle(data)   #打乱数据顺序

p=0.8       #训练集占比

# 分割训练集和测试集
train=data[:int(len(data)*p),:]
test=data[int(len(data)*p):,:]

tree_model=DecisionTreeClassifier() #初始化决策树
"""
    train[:,:3]表示前三列数据，用于做决策条件
    train[:,3]表示最后一列，是最终结果
    决策树的目的，是通过前三列的决策条件，得到最终结果
"""
tree_model.fit(train[:,:3],train[:,3])  #进行训练

#保存树模型
"""
treefile='../data/tree_model.tree'
joblib.dump(tree_model,treefile)
"""

predict=tree_model.predict(train[:,:3])     #标签归类
# 绘制混淆矩阵
get_cm(label=train[:,3],predict=predict,title="CART Model-CM")
# 绘制ROC曲线
get_roc(test=test,net='None',tree_model=tree_model,title="CART Model-ROC")



#-------------------------------------LM--------------------------------------------------------------------------------
from keras.models import Sequential
from keras.layers.core import Dense,Activation

net=Sequential()    #建立神经网络
net.add(Dense(input_dim = 3, output_dim = 10)) #添加输入层（3节点）到隐藏层（10节点）的连接
net.add(Activation('relu')) #隐藏层使用relu激活函数
net.add(Dense(input_dim = 10, output_dim = 1)) #添加隐藏层（10节点）到输出层（1节点）的连接
net.add(Activation('sigmoid')) #输出层使用sigmoid激活函数

net.compile(loss = 'binary_crossentropy', optimizer = 'adam')   #编译模型
net.fit(train[:,:3], train[:,3], nb_epoch=1000, batch_size=1)   #训练模型，循环1000次

predict_result = net.predict_classes(train[:,:3]).reshape(len(train)) #预测结果变形

# 绘制混淆矩阵
get_cm(label=train[:,3],predict=predict_result,title="LM Model-CM")
# 绘制ROC曲线
get_roc(test=test,net=net,tree_model='None',title="LM Model-ROC")


#绘制两种方法得出的ROC曲线，便于对比
get_roc(test=test,net=net,tree_model=tree_model,title='Compare of LM and CART')

"""决策树可视化--------------------------------------------------------------------------------------------------------"""
import pydotplus
from IPython.display import Image
from sklearn import tree

data_feature_name=model_data.columns[:3]        #取出特征数据
# 得到分类类别
data_target_name=np.unique(model_data[u'是否窃漏电']).astype('str')
#tree_model为决策树分类器fit之后得到的模型，注意这里必须在fit后执行，在predict之后运行会报错
dot_data = tree.export_graphviz(tree_model, out_file=None,feature_names=data_feature_name,
                                class_names=data_target_name) # doctest: +SKIP]

dot_graph=deal_chinese(dot_data)    #解决中文乱码

#获取决策树可视化图片
graph = pydotplus.graph_from_dot_data(dot_graph)
filename='tree.png'
graph.write_png(filename)	 # 生成png文件
a=Image(graph.create_png())
#-----------------------------------------------------------------------------------------------------------------------