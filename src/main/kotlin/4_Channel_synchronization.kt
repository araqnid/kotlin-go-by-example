import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.time.Duration.Companion.seconds

/**
 * Functions need to be marked as `suspend` if they are going to call any suspending operations.
 *
 * Suspend functions can simply call other suspend functions, or regular functions. However,
 * to call a suspend function from a regular function, you need to launch (or otherwise start)
 * a coroutine to hold it.
 */
private suspend fun worker(done: Channel<Unit>) {
    println("working...")
    delay(1.seconds)
    println("done")

    done.send(Unit)
}

/**
 * In Kotlin, it makes sense to use [Unit] as the type where we don't care what the data in the
 * channel **is**, we only need to wait for it to arrive.
 *
 * See [Go by Example: Channel Synchronization](https://gobyexample.com/channel-synchronization)
 */
fun main() = runBlocking {
    val done = Channel<Unit>(1)

    launch { worker(done) }

    // Removing this line will **not** cause the program to immediately exit, as runBlocking
    // will wait for all its launched coroutines to complete.
    done.receive()
}