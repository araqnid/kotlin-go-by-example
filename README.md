# Go by Example: Kotlin Coroutines

A transcription of [Go by Example: Goroutines](https://gobyexample.com/goroutines) into Kotlin.

This was created as a learning exercise, to get an understanding of what these concepts were in Go,
and figure out if Kotlin had a reasonably similar feature set.

These examples make more sense if you compare the Go originals with the Kotlin versions, in which
I've put some commentary on the differences.

| Go                                                      | Kotlin                                                                                                                                            |
|---------------------------------------------------------|---------------------------------------------------------------------------------------------------------------------------------------------------|
| https://gobyexample.com/goroutines                      | [1_Coroutines.kt](src/main/kotlin/1_Coroutines.kt)                                                                                                |
| https://gobyexample.com/channels                        | [2_Channels.kt](src/main/kotlin/2_Channels.kt)                                                                                                    |
| https://gobyexample.com/channel-buffering               | [3_Channel_buffering.kt](src/main/kotlin/3_Channel_buffering.kt)                                                                                  |
| https://gobyexample.com/channel-synchronization         | [4_Channel_synchronization.kt](src/main/kotlin/4_Channel_synchronization.kt)<br/>[4a_Jobs_and_waiting.kt](src/main/kotlin/4a_Jobs_and_waiting.kt) |
| https://gobyexample.com/channel-directions              | [5_Channel_directions.kt](src/main/kotlin/5_Channel_directions.kt)<br/>[5a_Produce.kt](src/main/kotlin/5a_Produce.kt)                             |
| https://gobyexample.com/select                          | [6_Select.kt](src/main/kotlin/6_Select.kt)                                                                                                        |
| https://gobyexample.com/timeouts                        | [7_Timeouts.kt](src/main/kotlin/7_Timeouts.kt)<br/>[7a_Consuming_channels.kt](src/main/kotlin/7a_Consuming_channels.kt)                           |
| https://gobyexample.com/non-blocking-channel-operations | [8_Non_blocking_channel_operations.kt](src/main/kotlin/8_Non_blocking_channel_operations.kt)                                                      |
| https://gobyexample.com/closing-channels                | [9_Closing_channels.kt](src/main/kotlin/9_Closing_channels.kt)<br/>[9a_Produce_closes_channel.kt](src/main/kotlin/9a_Produce_closes_channel.kt)   |
| https://gobyexample.com/range-over-channels             | [10_Iterate_over_channels.kt](src/main/kotlin/10_Iterate_over_channels.kt)                                                                        |
| https://gobyexample.com/timers                          | [11_Timers.kt](src/main/kotlin/11_Timers.kt)                                                                                                      |
| https://gobyexample.com/tickers                         | [12_Tickers.kt](src/main/kotlin/12_Tickers.kt)                                                                                                    |
| https://gobyexample.com/worker-pools                    | [13_Worker_pools.kt](src/main/kotlin/13_Worker_pools.kt)                                                                                          |
| https://gobyexample.com/waitgroups                      | [14_Coroutine_scopes.kt](src/main/kotlin/14_Coroutine_scopes.kt)                                                                                  |
| https://gobyexample.com/rate-limiting                   | [15_Rate_limiting.kt](src/main/kotlin/15_Rate_limiting.kt)                                                                                        |
| https://gobyexample.com/atomic-counters                 | [16_Atomic_counters.kt](src/main/kotlin/16_Atomic_counters.kt)                                                                                    |
| https://gobyexample.com/mutexes                         | [17_Mutexes.kt](src/main/kotlin/17_Mutexes.kt)                                                                                                    |
| https://gobyexample.com/stateful-goroutines             | [18_Stateful_coroutines.kt](src/main/kotlin/18_Stateful_coroutines.kt)                                                                            |
