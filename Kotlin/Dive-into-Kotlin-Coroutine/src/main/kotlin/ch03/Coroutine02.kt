package ch03

import kotlin.coroutines.Continuation
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext
import kotlin.coroutines.startCoroutine

/**
 * 创建 Kotlin 协程
 */

fun <R, T> launchCoroutine(receiver: R, block: suspend R.() -> T) {
    block.startCoroutine(receiver, object : Continuation<T> {
        override val context: CoroutineContext = EmptyCoroutineContext

        override fun resumeWith(result: Result<T>) {
            println("Coroutine End: $result")
        }
    })
}

//@RestrictsSuspension
class ProducerScope<T>() {
    suspend fun produce(value: T): T {
        println(value)
        return value
    }
}

suspend fun delay(i: Int) {
    println("In delay: $i")
}

fun main() {
    launchCoroutine(ProducerScope<Int>()) {
        println("In Coroutine")
        produce(512)
        delay(1000)
        produce(1024)
    }
}


