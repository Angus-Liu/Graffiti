package ch02

import java.io.BufferedReader
import java.io.StringReader
import java.lang.NumberFormatException

fun readNumber(reader: BufferedReader) {
    val number = try {
        Integer.parseInt(reader.readLine())
    } catch (e : NumberFormatException) {
        null
    }
    println(number)
}

fun main() {
    var reader = BufferedReader(StringReader("not a number"))
    readNumber(reader)
    reader = BufferedReader(StringReader("12"))
    readNumber(reader)
}