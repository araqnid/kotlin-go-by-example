import kotlinx.coroutines.*
import kotlin.time.Duration
import kotlin.time.Duration.Companion.seconds

/**
 * Kotlin doesn't have a direct equivalent of timer objects. However, a timer is just a simple coroutine
 * that uses [delay].
 *
 * Rather than using a channel to indicate the channel firing, we can simply use the completion of the
 * timer job (coroutine).
 *
 * See [Go by Example: Timers](https://gobyexample.com/timers)
 */
fun main() = runBlocking {
    val timer1 = launchTimer(2.seconds)

    timer1.join()
    println("Timer 1 fired")

    val timer2 = launchTimer(1.seconds)
    timer2.cancel() // This will terminate the timer coroutine
    if (timer2.isCancelled) {
        println("Timer 2 stopped")
    }

    delay(2.seconds)
}

/**
 * This is an example of a custom *coroutine launcher*. It simply starts a new coroutine,
 * in this case by delegating to [launch]. All coroutine launchers are extension functions
 * of the [CoroutineScope] interface, which defines the scope that the new coroutine(s)
 * will be attached to. In this case, the calls to [launchTimer] will propagate the
 * [runBlocking] scope.
 */
private fun CoroutineScope.launchTimer(timeout: Duration): Job {
    return launch {
        delay(timeout)
    }
}