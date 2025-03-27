import kotlinx.coroutines.channels.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.time.Duration.Companion.seconds

/**
 * See [Go by Example: Worker Pools](https://gobyexample.com/worker-pools)
 */
fun main() = runBlocking {
    val numJobs = 5
    val jobs = produce {
        for (j in 1..numJobs) {
            send(j)
        }
    }
    val results = Channel<Int>(numJobs)

    for (w in 1..3) {
        launch { worker(w, jobs, results) }
    }

    results.consume {
        // We still have to carefully only take numJobs elements from the results channel,
        // as nothing will close the results. However, once we exit the `consume` block, it
        // will be cancelled from the receive side.
        for (a in 1..numJobs) {
            results.receive()
        }
    }
}

private suspend fun worker(id: Int, jobs: ReceiveChannel<Int>, results: SendChannel<Int>) {
    for (j in jobs) {
        println("worker $id started  job $j")
        delay(1.seconds)
        println("worker $id finished job $j")
        results.send(j * 2)
    }
}