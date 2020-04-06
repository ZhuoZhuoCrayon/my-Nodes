# -*- coding:utf-8 -*-
# author: Crayon
# datetime: 2020/4/5 22:02
# IDE: PyCharm
# filename: grammar_classifier


# 检查是否为单字符变量表
def is_single_char_list(variables: list):
    for variable in variables:
        if len(variable) != 1:
            return False
    return True


# 检查变量表是否合法
# 1：变量表不能为空
# 2：变量表不能出现变量重复
# 3：起始字符S是单字符且存在于V
# 4：V、T交集为空
# 5：变量表不含空字符%
def is_legal_var_table(grammar: dict):
    v_list = grammar['V']
    t_list = grammar['T']
    # 1
    if len(v_list) == 0 or len(t_list) == 0:
        return False
    # 2
    if len(v_list) != set(v_list) or len(t_list) != set(t_list):
        return False
    # 3
    if len(grammar['S']) != 1 or grammar['S'] not in v_list:
        return False
    # 4
    if len([v for v in v_list if v in t_list]) > 0:
        return False
    # 5
    if '%' in v_list or '%' in t_list:
        return False
    return True


# 返回变量类型
# V：非终结符
# E：终结符
# N：空字符
# E：错误
def type_of_var(grammar: dict, var: str):
    if var in grammar['V']:
        return 'V'
    elif var in grammar['T']:
        return 'T'
    elif var == '%':
        return 'N'
    else:
        return 'E'


def is_psg(grammar: dict):
    generative_formulas = grammar['P']
    # print(generative_formulas)
    for formula_from, formula_to in generative_formulas:
        # print(formula_from, formula_to)
        # 默认%只出现在右部
        if formula_from.find('%') != -1:
            return False
        # %在右部只能作为单项出现
        if formula_to.find('%') != -1 and len(formula_to) != 1:
            return False

        # 检查产生式中是否出现非法字符
        cnt = 0
        for var in formula_from:
            var_type = type_of_var(grammar, var)
            if var_type == 'E':
                return False
            elif var_type == 'V':
                cnt = cnt + 1
        # 左部至少存在一个元素属于V
        if cnt == 0:
            return False
        for var in formula_to:
            if type_of_var(grammar, var) == 'E':
                return False

    return True


# 满足： formula_to >= formula_from
def is_csg(grammar: dict):
    generative_formulas = grammar['P']
    for formula_from, formula_to in generative_formulas:
        if not len(formula_to) >= len(formula_from):
            return False
    return True


# 左部是单字符变量，且属于V
def is_cfg(grammar: dict):
    generative_formulas = grammar['P']
    for formula_from, formula_to in generative_formulas:
        if not (len(formula_from) == 1 and type_of_var(grammar, formula_from) == 'V'):
            return False
    return True


# 右线性文法
# 左部是单字符变量且属于V      --cfg已证
# 右部结构：wB / w
#   w 属于 T*
#   B 是单字符变量且属于V
def is_right_rg(grammar: dict):
    generative_formulas = grammar['P']
    for formula_from, formula_to in generative_formulas:
        # 空符合要求
        if formula_to == '%':
            continue
        # 检查w字段是否都在T集合
        for var in formula_to[0:-1]:
            if type_of_var(grammar, var) != 'T':
                return False
        # 最后一个字符有可能是B或者是w的一部分，只要不是非法字符就ok
        if type_of_var(grammar, formula_to[-1]) == 'E':
            return False
    return True


# 左线性文法
# 左部是单字符变量且属于V      --cfg已证
# 右部结构：Bw / w
#   w 属于 T*
#   B 是单字符变量且属于V
def is_left_rg(grammar: dict):
    generative_formulas = grammar['P']
    for formula_from, formula_to in generative_formulas:
        # 空符合要求
        if formula_to == '%':
            continue
        # 检查w段是否都属于T
        for var in formula_to[1:]:
            if type_of_var(grammar, var) != 'T':
                return False
        # 第一个字符有可能是B或者是w的一部分，只要不是非法字符就ok
        if type_of_var(grammar, formula_to[0]) == 'E':
            return False
    return True


# 文法归类
def classifier(grammar: dict):
    # noinspection PyBroadException
    try:
        if not is_psg(grammar):
            return 'null'
        elif not is_csg(grammar):
            return 'psg'
        elif not is_cfg(grammar):
            return 'csg'
        elif is_left_rg(grammar):
            return 'left_rg'
        elif is_right_rg(grammar):
            return 'right_rg'
        else:
            return 'cfg'
    except Exception as e:
        print(e)
        return 'classify error'
