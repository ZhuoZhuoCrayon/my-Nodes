# -*- coding: utf-8 -*-
import re
import csv
import time
from typing import List, Tuple
from collections import defaultdict


class MatchType:
    WORD = 0
    RANGE = 1
    WORD_LIST = 2
    BUILD_IN_ENUM = 3


builtin_char_list = ["*", "[", "]", "?", "!"]

# parse_result_writer = open("parse_result.txt", "w")


def expand_list_element(nested_list: List) -> List:
    if isinstance(nested_list, str):
        return [nested_list]
    enum_value_list = []
    for value_exp in nested_list:
        if isinstance(value_exp, str):
            enum_value_list.append(value_exp)
        else:
            enum_value_list.extend(value_exp)
    return enum_value_list


def get_range_scope(range_expression: str) -> Tuple[str, str]:
    range_list = range_expression.split("-")
    return range_list[0], range_list[1]


def num_list2re(num_list: List[int]) -> str:
    num_list = sorted(list(set(num_list)))
    if num_list[0] < num_list[-1] and len(num_list) == num_list[-1] - num_list[0] + 1:
        return f"[{num_list[0]}-{num_list[-1]}]"
    diff = list(set(range(0, 10)) - set(num_list))
    if len(diff) < 5:
        return f"[!{''.join([str(num) for num in diff])}]"
    else:
        return str(num_list[0]) if len(num_list) == 1 else f"[{''.join([str(num) for num in num_list])}]"


def compress_num_range(range_expression: str) -> List[str]:
    begin, end = get_range_scope(range_expression)
    range_list = [str(number) for number in range(int(begin), int(end) + 1)]
    number_group_by_len = defaultdict(list)
    for str_num in range_list:
        number_group_by_len[len(str_num)].append(str_num)

    re_list = []
    for num_len, str_num_list in number_group_by_len.items():
        index_bits_map = defaultdict(list)
        for str_num in str_num_list:
            for index in range(num_len):
                index_bits_map[index].append(int(str_num[index]))
        sub_re_list = [num_list2re(bits) for bits in index_bits_map.values()]
        re_list.append("".join(sub_re_list))
    return re_list


def is_single_alpha_range(range_expression: str) -> bool:
    begin, end = get_range_scope(range_expression)
    if begin.islower() != end.islower():
        return False
    if len(begin) == 1 and len(end) == 1 and begin.isalpha() and end.isalpha() and ord(begin) < ord(end):
        return True
    return False


def is_number_range(range_expression: str) -> bool:
    begin, end = get_range_scope(range_expression)
    if begin.isdecimal() and end.isdecimal() and int(begin) < int(end):
        return True
    return False


def is_range_format(range_expression: str) -> bool:
    range_list = range_expression.split("-")
    if len(range_list) != 2:
        return False
    return True


def replace_builtin_enum_char():
    # 设置规则，对于内置符号，必须
    pass


def get_eum_expressions(expression_part: str) -> List[str]:
    # 解析[.....]

    expressions_parsed = [""]
    last_enum_end = -1
    enum_begin = expression_part.find("[")
    while enum_begin != -1:
        enum_end = expression_part.find("]", enum_begin)

        if enum_end == -1:
            raise Exception("表达式解析错误")

        enum_expression = expression_part[enum_begin: enum_end + 1]
        enum_value_list = expand_list_element(parse_enum_expression(enum_expression[1: -1], 4))

        sub_prefix = expression_part[last_enum_end + 1: enum_begin]
        sub_expressions_parsed = [f"{sub_prefix}{enum_value}" for enum_value in enum_value_list]

        expressions_parsed = [
            f"{exp_prefix}{sub_exp}" for exp_prefix in expressions_parsed for sub_exp in sub_expressions_parsed
        ]

        last_enum_end = enum_end
        enum_begin = expression_part.find("[", enum_end)

    sub_suffix = expression_part[last_enum_end + 1:]
    expressions_parsed = [f"{exp_prefix}{sub_suffix}" for exp_prefix in expressions_parsed]
    return expressions_parsed


def get_match_type(sub_expression: str) -> int:
    if sub_expression.startswith("[") and sub_expression.endswith("]"):
        return MatchType.BUILD_IN_ENUM
    elif "," in sub_expression:
        return MatchType.WORD_LIST
    elif "-" in sub_expression:
        if is_range_format(sub_expression):
            if is_single_alpha_range(sub_expression) or is_number_range(sub_expression):
                return MatchType.RANGE
            return MatchType.WORD
        return MatchType.WORD
    else:
        return MatchType.WORD


def parse_enum_expression(enum_expression: str, indent: int) -> List:
    match_status_func = {
        MatchType.WORD: lambda x: x,
        MatchType.BUILD_IN_ENUM: lambda x: x,
        MatchType.WORD_LIST: parse_word_list_expression,
        MatchType.RANGE: parse_range_expression,
    }
    match_type = get_match_type(enum_expression)

    sub_expressions = match_status_func[match_type](enum_expression)
    if match_type in [MatchType.WORD, MatchType.BUILD_IN_ENUM]:
        return match_status_func[match_type](enum_expression)
    # parse_result_writer.write(f"{' ' * indent}{sub_expressions}\n")
    return [parse_enum_expression(sub_expression, indent + 4) for sub_expression in sub_expressions]


def parse_word_list_expression(word_list_expression: str) -> List[str]:
    elements = word_list_expression.split(",")
    return [element.strip() for element in elements]


def parse_range_expression(range_expression: str) -> List[str]:
    if is_single_alpha_range(range_expression):
        return [f"[{range_expression}]"]
    if is_number_range(range_expression):
        return compress_num_range(range_expression)
    raise Exception("表达式解析错误")


def mock_sops_var(expression):
    return re.sub(r"[$]?{.*?}", "<MOCK_SOPS_VAR>", expression)


if __name__ == "__main__":
    expressions = [
        # 正常的输入
        # "[1, 2, 3].[txt, tar]",
        # "file[1-3].txt",
        # "cxx[python, java]dssf[3.6.8, 10.3.2].exe[1-3, a-z]",
        # "aa[b-c, 0-9]aa",
        # "[1-10000, 6-8, a-z][a]",
        # "[aaaaaaaa]"
    ]

    with open("expression.csv", "r") as csv_file:
        csv_file_reader = csv.reader(csv_file)
        expressions.extend(
            [line[0] for line in csv_file_reader if line]
        )

    parse_results = [[expression, ""] for expression in expressions]

    expressions = [mock_sops_var(exp) for exp in expressions]

    begin_time = time.clock()

    for idx, exp in enumerate(expressions):
        # parse_result_writer.write(f"parse <{test_expression_part}> ...\n")
        result = get_eum_expressions(mock_sops_var(exp))
        parse_results[idx][1] = result
        # print(result)
        # parse_result_writer.write(f"result <{result}>\n")
        # parse_result_writer.write("---------------------------------------------------------------------------\n")
    end_time = time.clock()

    print(f"num：{len(expressions)}, run_time: {end_time - begin_time}")

    parse_results = [
        [parse_result[0], ", ".join(parse_result[1])]
        for parse_result in parse_results
    ]

    parse_not_change_results = [
        parse_result
        for parse_result in parse_results
        if mock_sops_var(parse_result[0]) == parse_result[1]
    ]

    parse_not_change_results.insert(0, ["进程ID(表达式)", "解析结果(fnmatch可匹配, 以逗号分割)"])

    parse_results = [
        parse_result
        for parse_result in parse_results
        if mock_sops_var(parse_result[0]) != parse_result[1]
    ]

    parse_results.insert(0, ["进程ID(表达式)", "解析结果(fnmatch可匹配, 以逗号分割)"])

    with open("parse_result.csv", "w") as result_csv_file:
        result_csv_writer = csv.writer(result_csv_file)
        result_csv_writer.writerows(parse_results)

    with open("parse_no_change_result.csv", "w") as result_csv_file:
        result_csv_writer = csv.writer(result_csv_file)
        result_csv_writer.writerows(parse_not_change_results)
