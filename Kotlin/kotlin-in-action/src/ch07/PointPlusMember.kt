package ch07

data class Point(val x: Int, val y: Int) {
    operator fun plus(other: Point) = Point(x + other.x, y + other.y)
}


fun main() {
    var p1 = Point(1, 2)
    val p2 = Point(2, 4)
    p1 += (p1 + p2)
    println(p1)
    val list = listOf(1,2,4)
    println(list[1])
}
