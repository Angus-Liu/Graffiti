package ch03

import kotlin.coroutines.*

/**
 * 协程名的实现
 */
class CoroutineName(val name: String) : AbstractCoroutineContextElement(Key) {
    companion object Key : CoroutineContext.Key<CoroutineName>
}

/**
 * 协程异常处理器的实现
 */
class CoroutineExceptionHandler(val onErrorAction: (Throwable) -> Unit) : AbstractCoroutineContextElement(Key) {
    companion object Key : CoroutineContext.Key<CoroutineExceptionHandler>

    fun onError(error: Throwable) {
        error.printStackTrace()
        onErrorAction(error)
    }
}

val coroutineContext = CoroutineName("demo") + CoroutineExceptionHandler {
    println("In coroutine exception handler...")
}

val continuation2 = suspend {
    println("In Coroutine")
//    throw NullPointerException()
    5
}.createCoroutine(object: Continuation<Int> {
    override val context: CoroutineContext = coroutineContext

    override fun resumeWith(result: Result<Int>) {
        println("Coroutine End: $result")

        result.onFailure {
            context[CoroutineExceptionHandler]?.onError(it)
        }
    }
})

fun main() {
    continuation2.resume(Unit)
}
