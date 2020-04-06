# -*- coding:utf-8 -*-
# author: Crayon
# datetime: 2020/4/5 20:41
# IDE: PyCharm
# filename: grammar_reader

"""
Grammar.txt文件说明
文法之间以`--------TYPE`（八个-号）分隔
文法行顺序满足 G = {V, T, P, S} ，即文法结构-四行结构
V：非终结符集
T：终结符集
P：产生式的有限集
S：开始符号
其中，VTP以英文逗号分隔
！！！注意，以%代替空字符！！！
"""
grammar_url = '../file/Grammar.txt'
each_grammar_size = 5


# 读取文法数据，返回文法列表
def read_grammars_from_txt():
    with open(grammar_url, mode='r', encoding='utf8') as txt_reader:
        txt_content = txt_reader.read()
    txt_content_list = txt_content.split('\n')
    length = len(txt_content_list)
    grammar_list = []
    for begin in range(0, length, each_grammar_size):
        # print(txt_content_list[begin:begin + each_grammar_size])
        grammar = {}
        untreated_grammar = txt_content_list[begin:begin + each_grammar_size]
        grammar['V'] = untreated_grammar[0].split(',')
        grammar['T'] = untreated_grammar[1].split(',')
        grammar['S'] = untreated_grammar[3]

        # 产生式解析封装：
        # 考虑产生式左部相等情况，以元组列表存储产生式
        generative_formulas = []
        untreated_generative_formulas = untreated_grammar[2].split(',')
        for untreated_generative_formula in untreated_generative_formulas:
            formula_from, formula_to = untreated_generative_formula.split('->')
            # print(formula_from, formula_to)
            # 此时formula_to格式可能是A|B|C形式，再进行一次切割
            # 录入：S->A,S->B,S->C
            for to in formula_to.split('|'):
                generative_formulas.append((formula_from, to))
        grammar['P'] = generative_formulas
        grammar['TYPE'] = untreated_grammar[-1][8:].split(',')
        # print(grammar)
        grammar_list.append(grammar)
    # for grammar in grammar_list:
    #     print(grammar)
    return grammar_list


if __name__ == '__main__':
    read_grammars_from_txt()
