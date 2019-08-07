package ch02

class Person (
    val name: String,
    var isMarried: Boolean
)

fun main() {
    val person = Person("Angus", false)
    println(person.name)
    println(person.isMarried)
}