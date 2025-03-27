import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.time.Instant
import kotlin.time.Duration
import kotlin.time.Duration.Companion.milliseconds

/**
 * See [Go by Example: Rate limiting](https://gobyexample.com/rate-limiting)
 */
fun main() = runBlocking {
    // Basic rate limiting

    val requests = produce(capacity = 5) {
        for (i in 1..5) {
            send(i)
        }
    }

    val limiter = launchTicker(200.milliseconds)

    limiter.consume {
        requests.consumeEach { req ->
            limiter.receive()
            println("request $req ${Instant.now()}")
        }
    }

    println()

    // Bursty rate limiting
    val burstyLimiter = Channel<Instant>(3)
    for (i in 1..3) {
        burstyLimiter.send(Instant.now())
    }

    launch {
        for (t in launchTicker(200.milliseconds)) {
            burstyLimiter.send(t)
        }
    }

    val burstyRequests = produce(capacity = 5) {
        for (i in 1..5) {
            send(i)
        }
    }

    burstyLimiter.consume {
        burstyRequests.consumeEach { req ->
            burstyLimiter.receive()
            println("request $req ${Instant.now()}")
        }
    }
}

private fun CoroutineScope.launchTicker(interval: Duration): ReceiveChannel<Instant> {
    return produce {
        while (true) {
            delay(interval)
            send(Instant.now())
        }
    }
}
