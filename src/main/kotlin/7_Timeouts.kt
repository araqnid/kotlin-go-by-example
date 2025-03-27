import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.selects.onTimeout
import kotlinx.coroutines.selects.select
import kotlin.time.Duration.Companion.seconds

/**
 * A [select] can also have a timeout clause.
 *
 * See [Go by Example: Timeouts](https://gobyexample.com/timeouts)
 */
fun main() = runBlocking {
    /**
     * If you don't specify `capacity = 1` here, this coroutine will suspend
     * indefinitely, making the program hang as runBlocking will wait for it
     * to complete.
     */
    val c1 = produce(capacity = 1) {
        delay(2.seconds)
        send("result 1")
    }

    select<Unit> {
        c1.onReceive { println(it) }
        onTimeout(1.seconds) { println("timeout 1") }
    }

    val c2 = produce(capacity = 1) {
        delay(2.seconds)
        send("result 2")
    }

    select<Unit> {
        c2.onReceive { println(it) }
        onTimeout(3.seconds) { println("timeout 2") }
    }
}
