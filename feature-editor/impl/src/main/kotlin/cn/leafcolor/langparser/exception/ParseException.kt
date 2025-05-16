package cn.leafcolor.langparser.exception

class ParseException(
    message: String?,
    val lineNumber: Int,
    val columnNumber: Int
) : RuntimeException(message)