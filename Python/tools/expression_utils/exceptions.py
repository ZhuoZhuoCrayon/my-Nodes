# -*- coding: utf-8 -*-


class ExpressionBaseException(Exception):
    ERROR_CODE = "000"
    MESSAGE = "表达式解析模块异常"


class ExpressionSyntaxException(ExpressionBaseException):
    ERROR_CODE = "001"
    MESSAGE = "表达式语法错误"


class ExpressionParseException(ExpressionBaseException):
    ERROR_CODE = "002"
    MESSAGE = "表达式解析异常"
