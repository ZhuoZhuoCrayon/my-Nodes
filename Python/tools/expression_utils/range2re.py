# -*- coding: utf-8 -*-
from typing import List, Tuple


class RangePosition:
    BEGIN = 0
    END = 1


def get_upper_range(begin: int) -> Tuple[int, int]:
    """
    以begin为下界，获取可正则化的最大范围
    大致规则如下：
    取得的上界的位数=begin位数
    将begin的最后一位变成9，得到的范围是可正则化的
    从个位数起，若位值为0，将它上一位 置9得到的范围是可正则化的
    """
    end_str = str(begin)
    for index in range(len(end_str) - 1, -1, -1):
        if end_str[index] == "0":
            end_str = "9".join([end_str[:index], end_str[index + 1:]])
        else:
            end_str = "9".join([end_str[:index], end_str[index + 1:]])
            break
    return begin, int(end_str)


def get_lower_range(end: int) -> Tuple[int, int]:
    """
    以end为上界，获取可正则化的最大范围
    大致规则如下：
    取得的下界的位数=end位数
    将end的最后一位变成0，得到的范围是可正则化的
    从个位数起，若位值为9，将它上一位 置0得到的范围是可正则化的
    """
    begin_str = str(end)
    for index in range(len(begin_str) - 1, -1, -1):
        if begin_str[index] == "9":
            begin_str = "0".join([begin_str[:index], begin_str[index + 1:]])
        else:
            begin_str = "0".join([begin_str[:index], begin_str[index + 1:]])
            break
    return int(begin_str), end


def split_range_left(begin: int, end: int) -> List[Tuple[int, int]]:
    """将一个范围切割成若干可正则化范围
    从begin->end切割，得到的最后一个范围的上界>=end
    """
    split_range_list = []
    while begin < end:
        range_part = get_upper_range(begin)
        split_range_list.append(range_part)
        # 从切割右界下一个数开始继续切割
        begin = range_part[RangePosition.END] + 1
    return split_range_list


def split_range_right(begin: int, end: int) -> List[Tuple[int, int]]:
    """将一个范围切割成若干可正则化范围
    从end->begin切割，得到的最后一个范围的下界<=begin
    """
    split_range_list = []
    while begin < end:
        range_part = get_lower_range(end)
        split_range_list.append(range_part)
        end = range_part[RangePosition.BEGIN] - 1
    split_range_list.reverse()
    return split_range_list


def range2re(begin: int, end: int) -> List[str]:
    """获取可匹配一个范围内所有整数的正则表达式列表"""
    if begin == end:
        return [str(begin)]
    split_by_left = split_range_left(begin, end)
    mid_left = split_by_left.pop()
    # 从begin->end切割的最后一个范围>=end, 需要对剩余范围做一个准确切割
    split_by_right = split_range_right(mid_left[RangePosition.BEGIN], end)
    mid_right = split_by_right.pop(0)

    split_ranges = []
    split_ranges.extend(split_by_left)

    # 有交集，对于左切，start是准确的，对于右切，end是准确的，取left.start - right.end
    if all(
        [
            mid_right[RangePosition.BEGIN] < mid_left[RangePosition.END],
            mid_left[RangePosition.BEGIN] < mid_right[RangePosition.END],
        ]
    ):
        split_ranges.append((mid_left[RangePosition.BEGIN], mid_right[RangePosition.END]))
    else:
        split_ranges.extend([mid_left, mid_right])
    split_ranges.extend(split_by_right)

    # 将切割得到的范围转为正则
    re_str_list = []
    for split_range in split_ranges:
        begin_str = str(split_range[RangePosition.BEGIN])
        end_str = str(split_range[RangePosition.END])
        split_range_re = ""
        for index in range(len(begin_str)):
            if begin_str[index] == end_str[index]:
                split_range_re += begin_str[index]
            else:
                split_range_re += f"[{begin_str[index]}-{end_str[index]}]"
        re_str_list.append(split_range_re)
    return re_str_list
