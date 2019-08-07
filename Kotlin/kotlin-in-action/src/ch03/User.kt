package ch03

import java.lang.IllegalArgumentException

class User(val id: Int? = null, val name: String? = null, val address: String? = null)

fun User.validateBeforeSave() {
    fun validate(value: String?, fieldName: String) {
        if (value.isNullOrEmpty()) {
            throw IllegalArgumentException("Can't save user $id: empty $fieldName")
        }
    }
    validate(name, "Name")
    validate(address, "Address")
}

fun saveUser(user: User) {
    user.validateBeforeSave()
}

fun main() {
    val user = User(1, "Angus")
    saveUser(user)
}