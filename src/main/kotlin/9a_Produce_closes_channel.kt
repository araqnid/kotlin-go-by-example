import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.runBlocking

/**
 * If you use [produce], then the sender channel will automatically be closed when the producer block exits.
 */
fun main() = runBlocking {
    val jobs = produce {
        for (j in 1..3) {
            send(j)
            println("sent job $j")
        }
        println("sent all jobs")
    }

    while (true) {
        val j = jobs.receiveCatching()
        if (j.isSuccess) {
            println("received job ${j.getOrNull()}")
        } else {
            println("received all jobs")
            break
        }
    }
}
