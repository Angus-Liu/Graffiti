package ch05

import java.io.File

class Book(val title: String, val authors: List<String>)

fun File.isInsideHiddenDirectory() = generateSequence(this) { it.parentFile }.any { it.isHidden }

fun main() {
//    val books = listOf(
//        Book("book 1", listOf("Angus")),
//        Book("book 2", listOf("Angus", "Tom"))
//    )
//    val set = books.flatMap { it.authors }.toSet()
//    println(set)
//
//    val numbersTo100 = generateSequence(0){it + 1}.takeWhile { it <= 100 }.sum()
//    println(numbersTo100)

    val file = File("/Users/angus/a/c.txt")
    println(file.isInsideHiddenDirectory())
}