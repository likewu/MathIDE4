package cn.leafcolor.langparser

import cn.leafcolor.langparser.exception.ParseException
import cn.leafcolor.langparser.model.ParseResult
import java.io.File

class Parser private constructor() {
    companion object {
        private var parser: Parser? = null

        fun getInstance(): Parser {
            return parser ?: Parser().also {
                parser = it
            }
        }
    }

    fun execute(language: String, name: String, source: String, path: String, extRootDir: File?): ParseResult {
        //val mathlandDir = File(Environment.getExternalStorageDirectory(), "MathLand")
        //val mathlandDir = File("/storage/emulated/0/", "MathLand")
        val mathlandDir = File(extRootDir, "MathLand")
        if (mathlandDir.exists() == false) {
            val parseException = ParseException("Please make sure MathLand is installed and sdCard 'MathLand' directory is exist.", 0, 0)
            return ParseResult(parseException)
        }

        if (path.contains(mathlandDir.absolutePath)  == false) {
            val parseException = ParseException("Please make sure the source in sdCard 'MathLand' directory.", 0, 0)
            return ParseResult(parseException)
        }

        return ParseResult(null)
    }
}