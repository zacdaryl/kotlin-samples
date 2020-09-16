package com.jzm.coroutines

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking<Unit> {
    launch {
        println("start in run blocking launch")
        delay(200L)
        println("task from runBlocking")
    }

    coroutineScope {
        launch {
            println("start in run nested launch")
            delay(500L)
            println("task from nested launch")
        }

        println("start in run nested scope")
        delay(100L)
        println("task from coroutineScope")
    }

    println("CoroutineScope is over")
}