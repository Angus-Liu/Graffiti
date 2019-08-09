package ch08

val sum = { x: Int, y: Int -> x + y }
val sum2: (Int, Int) -> Int = { x, y -> x + y }

fun twoAndThree(a: Int, b: Int, operation: (Int, Int) -> Int) {
    val res = operation(a, b)
    println("The res is $res")
}

fun main() {
    twoAndThree(1, 2) { a, b -> a + b }
    twoAndThree(1, 2) { a, b -> a * b }
}