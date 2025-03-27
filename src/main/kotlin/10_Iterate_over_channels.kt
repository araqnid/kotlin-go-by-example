import kotlinx.coroutines.channels.consume
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.runBlocking

/**
 * In Kotlin you can iterate through a receive channel just like an Iterable or a Sequence.
 *
 * Remember the good practice of surrounding all the consumption of a channel's items
 * with [consume]: the common case of consuming a channel's items by iterating over them
 * can be combined into a single call to [consumeEach]:
 *
 * ```
 * queue.consume {
 *   for (elem in queue) {
 *     println(elem)
 *   }
 * }
 * ```
 *
 * See [Go by Example: Range over Channels](https://gobyexample.com/range-over-channels)
 */
fun main() = runBlocking {
    val queue = produce {
        send("one")
        send("two")
    }

    queue.consumeEach { println(it) }
}
