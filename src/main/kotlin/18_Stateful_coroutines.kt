import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.SendChannel
import kotlinx.coroutines.selects.select
import java.util.concurrent.atomic.AtomicInteger
import kotlin.random.Random
import kotlin.time.Duration.Companion.milliseconds
import kotlin.time.Duration.Companion.seconds

/**
 * See [Go by Example: Stateful Goroutines](https://gobyexample.com/stateful-goroutines)
 */
fun main() = runBlocking {
    val readOps = AtomicInteger(0)
    val writeOps = AtomicInteger(0)

    val reads = Channel<ReadOp>()
    val writes = Channel<WriteOp>()

    withContext(Dispatchers.Default) {
        launch {
            val state = mutableMapOf<Int, Int>()
            while (true) {
                select<Unit> {
                    reads.onReceive { read ->
                        read.resp.send(state[read.key] ?: 0)
                    }
                    writes.onReceive { write ->
                        state[write.key] = write.value
                        write.resp.send(Unit)
                    }
                }
            }
        }

        for (r in 1..100) {
            launch {
                while (true) {
                    val resp = Channel<Int>()
                    val read = ReadOp(key = Random.nextInt(5), resp = resp)
                    reads.send(read)
                    resp.receive()
                    readOps.incrementAndGet()
                    delay(1.milliseconds)
                }
            }
        }

        for (w in 1..10) {
            launch {
                while (true) {
                    val resp = Channel<Unit>()
                    val write = WriteOp(key = Random.nextInt(5), value = Random.nextInt(100), resp = resp)
                    writes.send(write)
                    resp.receive()
                    writeOps.incrementAndGet()
                    delay(1.milliseconds)
                }
            }
        }

        delay(1.seconds)
        // Cancel *all* coroutines launched inside this scope
        coroutineContext.job.cancelChildren()

        println("readOps: ${readOps.get()}")
        println("writeOps: ${writeOps.get()}")
    }
}

private class ReadOp(val key: Int, val resp: SendChannel<Int>)
private class WriteOp(val key: Int, val value: Int, val resp: SendChannel<Unit>)
