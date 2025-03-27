import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.runBlocking

/**
 * In Kotlin, you can create a channel and launch a coroutine at the same time.
 * The launched "producer" coroutine has access to the `SendChannel` interface,
 * and the return value of `produce` is the `ReceiveChannel`.
 *
 * You can specify a buffer capacity parameter to `produce` which is used to
 * construct the channel.
 */
fun main() = runBlocking {
    val pings = produce {
        send("passed message")
    }
    val pongs = produce(capacity = Channel.RENDEZVOUS) {
        val message = pings.receive()
        send(message)
    }
    println(pongs.receive())
}
