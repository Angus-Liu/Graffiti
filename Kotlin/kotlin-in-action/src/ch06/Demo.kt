package ch06

fun strLen(s: String) = s.length

class Person(val firstName: String, val lastName: String) {
    override fun equals(other: Any?): Boolean {
        val otherPerson = other as? Person ?: return false
        return otherPerson.firstName == firstName && otherPerson.lastName == lastName
    }

    override fun hashCode(): Int = firstName.hashCode() * 37 + lastName.hashCode()
}

fun sendEmailTo(email: String) {
    println("Sending email to $email")
}

fun main() {
//    var p1 = Person("Angus", "Liu")
//    val p2 = Person("Angus", "Liu")
//    println(p1 == p2)
//
//    val email: String? = "Hello World"
//    email?.let { sendEmailTo(it) }
//
//    val source = arrayListOf(3, 5, 7)
//    val target: MutableCollection<Int> = mutableListOf(3, 5, 7)

    val s = IntArray(5) { i -> (i + 1) * (i + 1) }
    println(s.joinToString())
}
