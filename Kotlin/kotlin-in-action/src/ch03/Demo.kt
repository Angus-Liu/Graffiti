package ch03

import java.lang.StringBuilder

fun <T> joinToString(collection: Collection<T>, separator: String = "， ", prefix: String = "", postfix: String = ""): String {
    val result = StringBuilder(prefix)
    for((index, element) in collection.withIndex()) {
        if (index > 0) result.append(separator)
        result.append(element)
    }
    result.append(postfix)
    return result.toString()
}

fun String.lastChar(): Char = this.get(this.length - 1)

fun main() {
    val set = hashSetOf(1, 2, 3)
    val list = arrayListOf(1, 2, 3)
    val map = hashMapOf(1 to "1", 2 to "2")
    println(set.javaClass)
    println(list.javaClass)
    println(map.javaClass)
    println(joinToString(list))
    println(joinToString(set, postfix = "]"))
    println("Angus".lastChar())
    println("12.345-6.A".split("\\.".toRegex()))
}