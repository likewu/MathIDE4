package cn.leafcolor.langparser.model

import cn.leafcolor.langparser.exception.ParseException

data class ParseResult(
    val exception: ParseException?
)