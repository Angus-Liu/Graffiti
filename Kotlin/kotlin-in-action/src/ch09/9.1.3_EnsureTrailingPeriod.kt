package ch09

import java.lang.Appendable
import java.lang.StringBuilder

fun <T> ensureTrailingPeriod(seq: T) where T :CharSequence, T : Appendable {
    if (!seq.endsWith('.')) {
        seq.append('.')
    }
}

fun main() {
    val word = StringBuilder("Angus Liu")
    ensureTrailingPeriod(word)
    println(word)
    
}