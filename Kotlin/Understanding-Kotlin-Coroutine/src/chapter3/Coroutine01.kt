package chapter3

import kotlin.coroutines.*

/**
 * 创建 Kotlin 协程
 */
val continuation = suspend {
    println("In Coroutine")
    5
}.createCoroutine(object: Continuation<Int> {
    override val context: CoroutineContext = EmptyCoroutineContext

    override fun resumeWith(result: Result<Int>) {
        println("Coroutine End: $result")
    }
})



fun main() {
    continuation.resume(Unit)
}


