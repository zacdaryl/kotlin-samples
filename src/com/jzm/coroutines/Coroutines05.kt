package com.jzm.coroutines

import kotlinx.coroutines.*

fun main() = runBlocking<Unit>(CoroutineName("com.jzm.security.com.jzm.coroutines.main")) {
//    launch { // context of the parent, com.jzm.security.com.jzm.coroutines.main runBlocking coroutine
//        println("com.jzm.security.com.jzm.coroutines.main runBlocking      : I'm working in thread ${Thread.currentThread().name}")
//    }
//    launch(Dispatchers.Unconfined) { // not confined -- will work with com.jzm.security.com.jzm.coroutines.main thread
//        println("Unconfined            : I'm working in thread ${Thread.currentThread().name}")
//    }
//    launch(Dispatchers.Default) { // will get dispatched to DefaultDispatcher
//        println("Default               : I'm working in thread ${Thread.currentThread().name}")
//    }
//    launch(newSingleThreadContext("MyOwnThread")) { // will get its own new thread
//        println("newSingleThreadContext: I'm working in thread ${Thread.currentThread().name}")
//    }

    /***************************************/

//    launch(Dispatchers.Unconfined) { // not confined -- will work with com.jzm.security.com.jzm.coroutines.main thread
//        println("Unconfined      : I'm working in thread ${Thread.currentThread().name}")
//        delay(500)
//        println("Unconfined      : After delay in thread ${Thread.currentThread().name}")
//    }
//    launch { // context of the parent, com.jzm.security.com.jzm.coroutines.main runBlocking coroutine
//        println("com.jzm.security.com.jzm.coroutines.main runBlocking: I'm working in thread ${Thread.currentThread().name}")
//        delay(1000)
//        println("com.jzm.security.com.jzm.coroutines.main runBlocking: After delay in thread ${Thread.currentThread().name}")
//    }

    /***************************************/

//    val a = async {
//        com.jzm.coroutines.log("I'm computing a piece of the answer")
//        6
//    }
//    val b = async {
//        com.jzm.coroutines.log("I'm computing another piece of the answer")
//        7
//    }
//    com.jzm.coroutines.log("The answer is ${a.await() * b.await()}")


    /***************************************/

//    newSingleThreadContext("Ctx1").use { ctx1 ->
//        newSingleThreadContext("Ctx2").use { ctx2 ->
//            runBlocking(ctx1) {
//                com.jzm.coroutines.log("Started in ctx1")
//                withContext(ctx2) {
//                    com.jzm.coroutines.log("Working in ctx2")
//                }
//                com.jzm.coroutines.log("Back to ctx1")
//            }
//        }
//    }
//
//    println("My job is ${coroutineContext[Job]}")

    /***************************************/

    // launch a coroutine to process some kind of incoming request
//    val request = launch {
//        // it spawns two other jobs, one with GlobalScope
//        GlobalScope.launch {
//            println("job1: I run in GlobalScope and execute independently!")
//            delay(1000)
//            println("job1: I am not affected by cancellation of the request")
//        }
//        // and the other inherits the parent context
//        launch {
//            delay(100)
//            println("job2: I am a child of the request coroutine")
//            delay(1000)
//            println("job2: I will not execute this line if my parent request is cancelled")
//        }
//    }
//    delay(500)
//    request.cancel() // cancel processing of the request
//    delay(1000) // delay a second to see what happens
//    println("com.jzm.security.com.jzm.coroutines.main: Who has survived request cancellation?")


    /***************** Parental responsibilities **********************/
    // launch a coroutine to process some kind of incoming request
//    val request = launch {
//        repeat(3) { i -> // launch a few children jobs
//            launch  {
//                delay((i + 1) * 1000L) // variable delay 200ms, 400ms, 600ms
//                println("Coroutine $i is done")
//            }
//        }
//        println("request: I'm done and I don't explicitly join my children that are still active")
//    }
//    request.join() // wait for completion of the request, including all its children
//    println("Now processing of the request is complete")

    /*****************  **********************/
    log("Started com.jzm.security.com.jzm.coroutines.main coroutine")
    // run two background value computations
    val v1 = async(CoroutineName("v1coroutine")) {
        delay(500)
        log("Computing v1")
        252
    }
    val v2 = async(CoroutineName("v2coroutine")) {
        delay(1000)
        log("Computing v2")
        6
        var a = 2 * 4
        a
    }
    log("The answer for v1 / v2 = ${v1.await() / v2.await()}")


}

fun log(msg: String) = println("[${Thread.currentThread().name}] $msg")