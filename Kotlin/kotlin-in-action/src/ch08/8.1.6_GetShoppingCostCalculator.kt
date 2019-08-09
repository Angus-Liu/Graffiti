package ch08

enum class Delivery {
    STANDARD, EXPEDITED
}

class Order(val itemCount: Int)

fun getShoppingCostCalculator(delivery: Delivery): (Order) -> Double {
    when (delivery) {
        Delivery.STANDARD -> return { order -> 6 + 2.1 * order.itemCount }
        Delivery.EXPEDITED -> return { order -> 1.2 * order.itemCount }
    }
}

fun main() {
    val calculator = getShoppingCostCalculator(Delivery.STANDARD)
    println("Shopping costs ${calculator(Order(3))}")
}