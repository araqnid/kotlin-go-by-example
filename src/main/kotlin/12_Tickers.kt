import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.SendChannel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.time.Instant
import kotlin.time.Duration
import kotlin.time.Duration.Companion.milliseconds

/**
 * Kotlin doesn't have a direct equivalent of tickers, but a ticker is simply a coroutine alternating
 * between producing items and delaying. As with timers, we can simply cancel the coroutine in order
 * to stop the production.
 *
 * Note that cancelling the ticker will cause its embedded channel to be marked as _failed_ (as
 * described by [SendChannel.close]). This in turn will cause the cancellation of the coroutine
 * performing [consumeEach]: there is no need to additionally coordinate.
 *
 * See [Go by Example: Tickers](https://gobyexample.com/tickers)
 */
fun main() = runBlocking {
    val ticker = launchTicker(500.milliseconds)

    launch {
        ticker.consumeEach {
            println("Tick at $it")
        }
    }

    delay(1600.milliseconds)
    ticker.cancel()

    println("Ticker stopped")
}

private fun CoroutineScope.launchTicker(interval: Duration): ReceiveChannel<Instant> {
    return produce {
        while (true) {
            delay(interval)
            send(Instant.now())
        }
    }
}
