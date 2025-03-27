import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.SendChannel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.selects.select
import kotlin.time.Duration.Companion.seconds

/**
 * See [Go by Example: Select](https://gobyexample.com/select)
 */
fun main() = runBlocking {
    val c1 = Channel<String>()
    val c2 = Channel<String>()

    launch {
        delay(1.seconds)
        c1.send("one")
    }

    launch {
        delay(2.seconds)
        c2.send("two")
    }

    for (i in 0 until 2) {
        // We need to be explicit about the result of the blocks being Unit type here.
        // If the blocks actually returned values (which would be returned from select),
        // Kotlin would be able to infer that type.
        select<Unit> {
            c1.onReceive { msg1 -> println("received $msg1") }
            c2.onReceive { msg2 -> println("received $msg2") }
        }
    }
}
