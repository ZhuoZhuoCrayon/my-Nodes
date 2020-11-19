# -*- coding: utf-8 -*-
import re
import csv


class MatchType:
    WORD = 0
    RANGE = 1
    WORD_LIST = 2


builtin_char_list = ["*", "[", "]", "?", "!"]

parse_result_writer = open("parse_result.txt", "w")


def list_enum_exp_parse_value(enum_exp_parse_list):
    if isinstance(enum_exp_parse_list, str):
        return [enum_exp_parse_list]
    enum_value_list = []
    for value_exp in enum_exp_parse_list:
        if isinstance(value_exp, str):
            enum_value_list.append(value_exp)
        else:
            enum_value_list.extend(value_exp)
    return enum_value_list


def validate_range_scope(range_expression):
    range_list = range_expression.split("-")
    if len(range_list) != 2:
        return False
    begin, end = range_list[0], range_list[1]
    if begin.isdecimal() and end.isdecimal() and int(begin) < int(end):
        return True
    if len(begin) == 1 and len(end) == 1 and begin.isalpha() and end.isalpha() and ord(begin) < ord(end):
        return True
    return False


def replace_builtin_enum_char():
    # 设置规则，对于内置符号，必须
    pass


def get_eum_expressions(expression_part: str):
    # 解析[.....]

    enum_str_list = []
    last_enum_end = -1

    enum_begin = expression_part.find("[")
    while enum_begin != -1:
        enum_end = expression_part.find("]", enum_begin)

        if enum_end == -1:
            raise Exception("表达式解析错误")

        enum_expression = expression_part[enum_begin: enum_end + 1]
        enum_value_list = list_enum_exp_parse_value(parse_enum_expression(enum_expression[1: -1], 4))
        print(enum_value_list)
        enum_str_list.append(enum_expression)

        if last_enum_end + 1 != enum_begin:
            print(expression_part[last_enum_end + 1: enum_begin])
        last_enum_end = enum_end
        enum_begin = expression_part.find("[", enum_end)


def get_match_type(sub_expression):
    if "," in sub_expression:
        return MatchType.WORD_LIST
    elif "-" in sub_expression:
        # TODO: 用[[]来屏蔽内置符比较合理
        if not validate_range_scope(sub_expression):
            return MatchType.WORD
        return MatchType.RANGE
    else:
        return MatchType.WORD


def parse_enum_expression(enum_expression, indent):
    # print(f"match:{enum_expression}")
    match_status_func = {
        MatchType.WORD: lambda x: x,
        MatchType.WORD_LIST: parse_word_list_expression,
        MatchType.RANGE: parse_range_expression,
    }
    match_type = get_match_type(enum_expression)

    sub_expressions = match_status_func[match_type](enum_expression)
    if match_type == MatchType.WORD:
        return match_status_func[match_type](enum_expression)
    parse_result_writer.write(f"{' ' * indent}{sub_expressions}\n")
    return [parse_enum_expression(sub_expression, indent + 4) for sub_expression in sub_expressions]


def parse_word_list_expression(word_list_expression: str):
    elements = word_list_expression.split(",")
    return [element.strip() for element in elements]


def parse_range_expression(range_expression: str):
    # TODO 0-9a-z类似这种的识别
    range_list = range_expression.split("-")
    if len(range_list) != 2:
        raise Exception("表达式解析错误")
    begin, end = range_list[0], range_list[1]
    # 验证范围表达式的有效性
    if begin.isdecimal() and end.isdecimal() and int(begin) < int(end):
        return [str(number) for number in range(int(begin), int(end) + 1)]
    if len(begin) == 1 and len(end) == 1 and begin.isalpha() and end.isalpha() and ord(begin) < ord(end):
        return [chr(_ascii) for _ascii in range(ord(begin), ord(end) + 1)]
    raise Exception("表达式解析错误")


def mock_sops_var(expression):
    return re.sub(r"[$]?{.*?}", "<MOCK_SOPS_VAR>", expression)


if __name__ == "__main__":
    test_expression_parts = [
        # 正常的输入
        "[1, 2, 3].[txt, tar]",
        "file[1-3].txt",
        "cxx[python, java]dssf[3.6.8, 10.3.2].exe[1-3, a-z]",
        "aa[b-c, 0-9]aa",
        "[1-4, 6-8, a-z][a]",
        "[aaaaaaaa]"
    ]

    csv_file = open("expression.csv", "r")
    csv_file_reader = csv.reader(csv_file)

    test_expression_parts.extend(
        [mock_sops_var(line[0]) for line in csv_file_reader if line]
    )

    for test_expression_part in test_expression_parts:
        parse_result_writer.write(f"parse <{test_expression_part}> ...\n")
        get_eum_expressions(test_expression_part)
        parse_result_writer.write("---------------------------------------------------------------------------\n")
