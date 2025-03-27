import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.time.Duration.Companion.seconds

/**
 * Kotlin will always wait for a coroutine scope's coroutines to finish before
 * the scope will be completed. Most coroutine scopes are linked to blocks
 * of code, as with [runBlocking] that we have seen so far.
 *
 * You can create a new scope at any time using [coroutineScope]. This effectively
 * forms an implicit wait group for all the coroutines launched inside it.
 *
 * See [Go by Example: WaitGroups](https://gobyexample.com/waitgroups)
 */
fun main() = runBlocking {
    coroutineScope {
        for (i in 1..5) {
            launch {
                worker(i)
            }
        }
        println("All workers have launched now")
    }

    println("All workers have completed now")
}

private suspend fun worker(id: Int) {
    println("Worker $id starting")
    delay(1.seconds)
    println("Worker $id done")
}
