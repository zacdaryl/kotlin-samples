package com.jzm.coroutines

import kotlinx.coroutines.*
import kotlin.concurrent.thread

fun main() = runBlocking {
//    val job =  GlobalScope.launch { // 在后台启动一个新的协程并继续
//        delay(1000L) // 非阻塞的等待 1 秒钟（默认时间单位是毫秒）
//        println("World!") // 在延迟后打印输出
//    }

    launch { // launch a new coroutine in the scope of runBlocking
        println("launch start")
        delay(1000L)
        println("World!")
    }
//    thread {
//        Thread.sleep(1000L)
//        println("World!") // 在延迟后打印输出
//    }
    println("Hello,") // 协程已在等待时主线程还在继续
//    job.join() // wait until child coroutine completes
//    Thread.sleep(2000L) // 阻塞主线程 2 秒钟来保证 JVM 存活

//    runBlocking {     // but this expression blocks the com.jzm.security.com.jzm.coroutines.main thread
//        delay(2000L)  // ... while we delay for 2 seconds to keep JVM alive
//    }

//    delay(2000L)
}

