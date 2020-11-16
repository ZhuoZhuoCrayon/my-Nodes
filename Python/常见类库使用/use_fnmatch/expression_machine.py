# -*- coding: utf-8 -*-

builtin_char_list = ["*", "[", "]", "?", "!"]


def replace_builtin_enum_char():
    # 设置规则，对于内置符号，必须
    pass

def parse_expression_part(expression_part: str):
    # 解析[.....]

    enum_str_list = []

    enum_begin = expression_part.find("[")
    while enum_begin != -1:
        enum_end = expression_part.find("]", enum_begin)

        if enum_end == -1:
            raise Exception("表达式解析错误")

        enum_expression = expression_part[enum_begin:enum_end + 1]

        enum_str_list.append(enum_expression)

        enum_begin = expression_part.find("[", enum_end)

    return enum_str_list


if __name__ == '__main__':
    test_expression_parts = [
        # 正常的输入
        "[1, 2, 3].[txt, tar]",
        "file[1-3].txt",
        "[python, java][3.6.8, 10.3.2].exe[1-3]",
        # 异常，比如嵌套
        "[a, b[ab, cd], e]"
    ]

    for test_expression_part in test_expression_parts:
        print(test_expression_part, f"\nresult:{parse_expression_part(test_expression_part)}")
