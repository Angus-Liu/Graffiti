package chapter02

import java.util.*

fun fizzBuzz(i: Int) = when {
    i % 15 == 0 -> "FizzBuzz "
    i % 3 == 0 -> "Fizz "
    i % 5 == 0 -> "Buzz "
    else -> "$i "
}

fun isLetter(c: Char) = c in 'a'..'z' || c in 'A'..'Z'

fun main() {
//    for (i in 100 downTo 1 step 2) {
//        print(fizzBuzz(i))
//    }
//    println()
//    for (i in 0 until 100) {
//        print(fizzBuzz(i))
//    }

//    val binaryReps = TreeMap<Char, String>()
//
//    for (c in 'A'..'F') {
//        val binary = Integer.toBinaryString(c.toInt())
//        binaryReps[c] = binary
//    }
//
//    for ((letter, binary) in binaryReps) {
//        println("$letter = $binary")
//    }

    println(isLetter('c'))
    println(isLetter('8'))
}