# -*- coding: utf-8 -*-


class MatchType:
    WORD = 0
    RANGE = 1
    WORD_LIST = 2


builtin_char_list = ["*", "[", "]", "?", "!"]


def validate_range_scope(begin: str, end: str):
    if all([begin.isdecimal(), end.isdecimal(), begin < end]):
        return True
    if all([len(begin) == 1, len(end) == 1, begin.isalpha(), end.isalpha(), ord(begin) < ord(end)]):
        return True
    raise Exception("表达式解析错误")


def replace_builtin_enum_char():
    # 设置规则，对于内置符号，必须
    pass


def get_eum_expressions(expression_part: str):
    # 解析[.....]

    enum_str_list = []

    enum_begin = expression_part.find("[")
    while enum_begin != -1:
        enum_end = expression_part.find("]", enum_begin)

        if enum_end == -1:
            raise Exception("表达式解析错误")

        enum_expression = expression_part[enum_begin: enum_end + 1]

        enum_str_list.append(enum_expression)

        enum_begin = expression_part.find("[", enum_end)

    return [parse_enum_expression(enum_str[1:-1], 4) for enum_str in enum_str_list]


def get_match_type(sub_expression):
    if "," in sub_expression:
        return MatchType.WORD_LIST
    elif "-" in sub_expression:
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
    print(f"{' ' * indent}{sub_expressions}")
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
    validate_range_scope(begin, end)
    return [chr(_ascii) for _ascii in range(ord(begin), ord(end) + 1)]


if __name__ == "__main__":
    test_expression_parts = [
        # 正常的输入
        "[1, 2, 3].[txt, tar]",
        "file[1-3].txt",
        "[python, java][3.6.8, 10.3.2].exe[1-3]",
        # 异常，比如嵌套
        "[a, b[ab, cd], e]",
        "[1-4, 6-8, a-z]",
    ]

    for test_expression_part in test_expression_parts:
        print(f"parse <{test_expression_part}> ...")
        get_eum_expressions(test_expression_part)
        print("---------------------------------------------------------------------------")
