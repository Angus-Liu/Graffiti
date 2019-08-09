package ch04

import ch01.Person

object Payroll {
    val allEmployees = arrayListOf<Person>()

    fun calculcateSalary() {
        for (person in allEmployees) {

        }
    }
}

open class User (val nickname: String) {
    companion object  {
        fun newSubscribingUser(email: String) = User(email.substringBefore("@"))
        fun newFacebookUser(accountId: Int) = User(accountId.toString())
    }
}

class SubscribingUser(nickname: String) : User(nickname) {
    
}

fun main() {
    Payroll.allEmployees
    println(User.newSubscribingUser("angus@qq.com"))
    println(User.newFacebookUser(1))
}

