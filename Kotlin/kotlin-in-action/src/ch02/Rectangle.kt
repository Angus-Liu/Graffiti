package ch02

class Rectangle(val height: Int, val width: Int) {
    val isSquare: Boolean
        get() {
            return height == width
        }
}

fun main() {
    val rectangle = Rectangle(12,12)
    println(rectangle.isSquare)
}