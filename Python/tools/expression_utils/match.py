# -*- coding: utf-8 -*-
import fnmatch
from typing import List

from Python.tools.expression_utils.parse import parse_exp2unix_shell_style


def match(name: str, expression: str) -> bool:
    """Test whether NAME matches EXPRESSION.

    Patterns are Unix shell style:

    *               matches everything
    ?               matches any single character
    [seq]           matches any character in seq
    [!seq]          matches any char not in seq

    Features different from fnmatch：

    [word1, word2]  matches any word in list
    [1-1000]        matches any number in range
    """
    # 将表达式中的枚举语法转化为若干Unix shell style的模式串
    exprs_unix_shell_style = parse_exp2unix_shell_style(expression)
    # name与任一模式串匹配成功即与expression匹配成功
    for expr in exprs_unix_shell_style:
        if fnmatch.fnmatch(name, expr):
            return True
    return False


def list_match(names: List[str], expression: str) -> List[str]:
    """Return the subset of the list NAMES that match EXPRESSION."""
    exprs_unix_shell_style = parse_exp2unix_shell_style(expression)
    filter_results = []
    for expr in exprs_unix_shell_style:
        filter_results.extend(fnmatch.filter(names, expr))
    return list(set(filter_results))
