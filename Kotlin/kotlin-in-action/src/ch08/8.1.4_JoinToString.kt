package ch08

fun <T> Collection<T>.joinToString(
    separator: String = ", ",
    prefix: String = "(",
    postfix: String = ")",
    transform: (T) -> String = { it.toString() }
): String {
    val res = StringBuilder(prefix)
    for ((index, element) in this.withIndex()) {
        if (index > 0) res.append(separator)
        res.append(transform(element))
    }
    res.append(postfix)
    return res.toString()
}

fun main() {
    val list = listOf("1", "2", "3", "4", "a", "b")
    println(list.joinToString { it.toUpperCase() })
}