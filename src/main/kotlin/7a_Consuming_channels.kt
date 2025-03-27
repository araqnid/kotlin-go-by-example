import kotlinx.coroutines.channels.consume
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.selects.onTimeout
import kotlinx.coroutines.selects.select
import kotlin.time.Duration.Companion.seconds

/**
 * Kotlin channels can be **cancelled**. When this happens, any coroutine
 * trying to send to that channel will also be cancelled. It is good
 * practice to cancel a channel when it will no longer be used as that
 * will guard against bugs where coroutines suspend indefinitely trying
 * to send into a channel nothing will ever receive from.
 */
fun main() = runBlocking {
    /**
     * No need to specify capacity here: the cancellation of the channel
     * will automatically cancel its producer coroutine.
     */
    val c1 = produce {
        delay(2.seconds)
        send("result 1")
    }

    /**
     * The `consume` extension function will close the channel when the
     * block exits: it asserts that all the code to consume the channel
     * must be within the block.
     */
    c1.consume {
        select<Unit> {
            c1.onReceive { println(it) }
            onTimeout(1.seconds) { println("timeout 1") }
        }
    }

    val c2 = produce {
        delay(2.seconds)
        send("result 2")
    }

    c2.consume {
        select<Unit> {
            c2.onReceive { println(it) }
            onTimeout(3.seconds) { println("timeout 2") }
        }
    }
}
