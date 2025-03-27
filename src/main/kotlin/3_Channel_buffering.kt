import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.runBlocking

/**
 * Kotlin channels can be buffered or unbuffered. Buffered channels will allow sends
 * until the buffer as full, and then suspend senders until receivers have taken
 * values. However, an unbuffered channel will suspend the sender or receiver until
 * both are present: in Kotlin, this is called a _rendezvous channel_.
 *
 * The [Channel] function to create a channel can take a numeric buffer size, or
 * one of the "magic" constants:
 *
 * * [Channel.BUFFERED] creates a buffered channel with a platform-reasonable default size
 * * [Channel.RENDEZVOUS] creates a rendezvous channel
 * * [Channel.UNLIMITED] creates a channel with an unbounded buffer size
 * * [Channel.CONFLATED] creates a specialised channel with a single-entry buffer. The
 *   buffered value is overwritten if it is not taken by a receiver before the next send.
 *
 * See [Go by Example: Channel Buffering](https://gobyexample.com/channel-buffering)
 */
fun main() = runBlocking {
    val messages = Channel<String>(2)

    messages.send("buffered")
    messages.send("channel")

    println(messages.receive())
    println(messages.receive())
}
