import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.time.Duration.Companion.seconds

private suspend fun worker() {
    println("working...")
    delay(1.seconds)
    println("done")
}

/**
 * Rather than coordinating a launched coroutine by creating a channel, we
 * can observe it directly. The result of [launch] is a [Job], which provides
 * a way for us to wait for the launched job to finish.
 */
fun main() = runBlocking {
    val job: Job = launch { worker() }

    job.join()
}