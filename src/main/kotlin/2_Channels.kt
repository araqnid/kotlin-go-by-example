import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * Kotlin has channels that are very similar to Go's.
 *
 * See [Go by Example: Channels](https://gobyexample.com/channels)
 */
fun main() = runBlocking {
    val messages = Channel<String>()

    launch {
        messages.send("ping")
    }

    val message = messages.receive()
    println(message)
}