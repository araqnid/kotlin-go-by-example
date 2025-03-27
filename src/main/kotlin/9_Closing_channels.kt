import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * See [Go by Example: Closing Channels](https://gobyexample.com/closing-channels)
 */
fun main() = runBlocking {
    val jobs = Channel<Int>(5)

    launch {
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

    for (j in 1..3) {
        jobs.send(j)
        println("sent job $j")
    }
    jobs.close()
    println("sent all jobs")

    // end of scope will automatically wait for launched coroutine to complete
}
