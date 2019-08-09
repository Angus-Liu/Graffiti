package ch07

data class Rectangle(val upperLeft: Point, val lowerRight: Point)

operator fun Rectangle.contains(p: Point) =
    p.x in upperLeft.x..lowerRight.x && p.y in upperLeft.y..lowerRight.y

fun main() {
    val rect = Rectangle(Point(10, 20), Point(50, 50))
    println(Point(20, 30) in rect)

    (0..100).forEach { print(it) }
}