import kotlinx.coroutines.*
import kotlin.time.Duration.Companion.seconds

private fun f(from: String) {
    for (i in 0 until 3) {
        println("$from: $i")
    }
}

/**
 * Coroutines need to be run inside a "scope". A coroutine scope defines
 * some context for every coroutine: on the JVM, that includes which
 * thread (or pool of threads) will be used for execution.
 *
 * One of the simplest ways to create a scope is [runBlocking], which
 * will run all the coroutines inside the scope on the current thread,
 * and only return once they have all finished. This waiting behaviour
 * is common to all coroutine scopes.
 *
 * The 'launch' method (properly [CoroutineScope.launch]) starts a new
 * coroutine within the scope, with no expectation that it will return
 * a value.
 *
 * See [Go by Example: Goroutines](https://gobyexample.com/goroutines)
 */
fun main() = runBlocking {
    f("direct")

    launch { f("coroutine") }

    launch {
        println("going")
    }

    delay(1.seconds)
    println("done")
}
