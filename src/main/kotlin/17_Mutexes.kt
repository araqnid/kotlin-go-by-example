import kotlinx.coroutines.*
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

/**
 * See [Go by Example: Mutexes](https://gobyexample.com/mutexes)
 */
fun main() = runBlocking {
    val c = Container(counters = mutableMapOf("a" to 0, "b" to 0))

    suspend fun doIncrement(name: String, n: Int) {
        for (i in 1..n) {
            c.inc(name)
        }
    }

    withContext(Dispatchers.Default) {
        launch { doIncrement("a", 10000) }
        launch { doIncrement("a", 10000) }
        launch { doIncrement("b", 10000) }
    }

    println(c.counters)
}

private class Container(val mu: Mutex = Mutex(), val counters: MutableMap<String, Int>) {
    suspend fun inc(name: String) {
        mu.withLock {
            counters[name] = counters[name]!! + 1
        }
    }
}
