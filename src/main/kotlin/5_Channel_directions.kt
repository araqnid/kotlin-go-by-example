import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.SendChannel
import kotlinx.coroutines.runBlocking

private suspend fun ping(pings: SendChannel<String>, msg: String) {
    pings.send(msg)
}

private suspend fun pong(pings: ReceiveChannel<String>, pongs: SendChannel<String>) {
    val msg = pings.receive()
    pongs.send(msg)
}

/**
 * See [Go by Example: Channel Directions](https://gobyexample.com/channel-directions)
 */
fun main() = runBlocking {
    val pings = Channel<String>(1)
    val pongs = Channel<String>(1)
    ping(pings, "passed message")
    pong(pings, pongs)
    println(pongs.receive())
}
