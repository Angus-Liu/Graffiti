package ch08

import com.sun.org.apache.xerces.internal.xs.StringList

data class Person(val firstName: String, val lastName: String, val phoneNumber: String? = null)

class ContactListFilters {
    var prefix: String = ""
    var onlyWithPhoneNumber: Boolean = false

    fun getPredicate(): (Person) -> Boolean {
        val startsWithPrefix = { p: Person -> p.firstName.startsWith(prefix) || p.lastName.startsWith(prefix) }
        if (!onlyWithPhoneNumber) {
            return startsWithPrefix
        }
        return { startsWithPrefix(it) && it.phoneNumber != null }
    }
}

fun main() {
    val contacts = listOf(Person("Angus", "Liu", "123"), Person("Tom", "An"))
    val contactListFilters = ContactListFilters()
    with(contactListFilters) {
        prefix = "An"
        onlyWithPhoneNumber = false
    }
    println(contacts.filter(contactListFilters.getPredicate()))
}