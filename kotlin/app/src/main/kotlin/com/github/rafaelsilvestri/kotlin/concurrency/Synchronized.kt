package com.github.rafaelsilvestri.kotlin

import kotlinx.coroutines.*

/**
 * Simple Coroutine file that handles concurrency through the @Synchronized feature.
 */

val storage = HashMap<String, List<Int>>()
var counter = 0

// when synchronize only increment, duplications happen even with final result of counter is always the same
//@Synchronized
fun increment() {
    counter++
}

fun get() = counter

// when increment and get are synchronized in the same method, duplications never happen
@Synchronized
fun incrementAndGet(): Int {
    increment()
    return get()
}

fun main() = runBlocking {
    // execute 50x the get more accurate output
    for (x in 1..50) {
        counter = 0
        storage.clear()

        val scope = CoroutineScope(newFixedThreadPoolContext(5, "counterPool"))
        scope.launch {
            val coroutines = 1.rangeTo(5).map {
                launch {
                    val item = mutableListOf<Int>()
                    for (i in 1..1000) { // each of them, increment the counter 1000 times.
                        item.add(incrementAndGet()) // increment and get the value
                    }
                    // store values incremented by each thread
                    storage.putIfAbsent(Thread.currentThread().name, item)
                }
            }

            coroutines.forEach { coroutine ->
                coroutine.join() // wait for all coroutines to finish their jobs.
            }
        }.join()

        println("The final value of counter is ${get()}")

        // merge lists to count duplications
        val result = mutableListOf<Int>()
        for (k in storage.keys) {
            storage[k]?.let { result.addAll(it) }
        }
        // print duplicated results if exists
        println(result.groupingBy { it }.eachCount().filter { it.value > 1 })
    }
}