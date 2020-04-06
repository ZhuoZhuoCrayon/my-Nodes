# -*- coding:utf-8 -*-
# author: Crayon
# datetime: 2020/4/6 10:24
# IDE: PyCharm
# filename: __init__


from src import grammar_reader, grammar_classifier


if __name__ == '__main__':
    num = 1
    # 初始化正确数
    correct = 0
    # 读取已解析的文法列表
    grammar_list = grammar_reader.read_grammars_from_txt()
    for grammar in grammar_list:
        print('[' + str(num) + ']读取文法并解析如下：')
        print(grammar)
        # 分类
        ans = grammar_classifier.classifier(grammar)
        print('分类结果：[' + ans + ']')
        if ans in grammar['TYPE']:
            print('分类正确')
            correct = correct + 1
        else:
            print('分类错误, 预期结果:' + str(grammar['TYPE']))
        num = num + 1
        print('=======================================================')
    print('正确率 = ' + str(correct / len(grammar_list)))
