import kotlinx.coroutines.*
import java.util.concurrent.atomic.AtomicLong

/**
 * In Kotlin of course you can use the Java atomic classes.
 *
 * The [withContext] function applies a change to the "coroutine context"
 * for the given block. In this case, the [Dispatchers.Default] context
 * key specifies to use a worker thread pool to run all the coroutines
 * within the block, rather than dispatching everything from the `main`
 * thread.
 *
 * See [Go by Example: Atomic Counters](https://gobyexample.com/atomic-counters)
 */
fun main() = runBlocking {
    val ops = AtomicLong()

    withContext(Dispatchers.Default) {
        for (i in 1..50) {
            launch {
                for (c in 1..1000) {
                    ops.incrementAndGet()
                }
            }
        }
    }

    println("ops: ${ops.get()}")
}
