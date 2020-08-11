package ch03

import kotlin.coroutines.Continuation
import kotlin.coroutines.ContinuationInterceptor
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.startCoroutine

class LogInterceptor : ContinuationInterceptor {
    override val key: CoroutineContext.Key<*> = ContinuationInterceptor

    override fun <T> interceptContinuation(continuation: Continuation<T>): Continuation<T> {
        return LogContinuation(continuation)
    }

}

class LogContinuation<T>(private val continuation: Continuation<T>) : Continuation<T> by continuation {
    override fun resumeWith(result: Result<T>) {
        println("before resumeWith: $result")
        continuation.resumeWith(result)
        println("after resumeWith.")
    }
}

fun main() {
    suspend {
        println("suspend")
        5
    }.startCoroutine(object : Continuation<Int> {
        override val context: CoroutineContext = LogInterceptor()

        override fun resumeWith(result: Result<Int>) {
            println("result: $result")
        }

    })
}