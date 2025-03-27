import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.selects.select

/**
 * Kotlin does not have a 'onDefault' or similar for `select`. Non-suspending operations are available as
 * alternatives, but writing a [select] across them without a timeout is not supported.
 *
 * See [Go by Example: Non-Blocking Channel Operations](https://gobyexample.com/non-blocking-channel-operations)
 * @see select for a chart of channel operations with non-suspending and select equivalents
 */
fun main() { // Note no need for runBlocking here as this code has no suspend points
    val messages = Channel<String>()
    val signals = Channel<Unit>()

    messages.tryReceive().let {
        when {
            it.isSuccess -> println("received message ${it.getOrThrow()}")
            else -> println("no message received")
        }
    }

    val msg = "hi"
    messages.trySend(msg).let {
        when {
            it.isSuccess -> println("sent message $msg")
            else -> println("no message sent")
        }
    }

    Pair(messages.tryReceive(), signals.tryReceive()).let { (msg, sig) ->
        when {
            msg.isSuccess -> println("received message ${msg.getOrThrow()}")
            sig.isSuccess -> println("received signal ${sig.getOrThrow()}")
            else -> println("no activity")
        }
    }
}
