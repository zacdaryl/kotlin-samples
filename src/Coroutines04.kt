import kotlinx.coroutines.*

fun main() = runBlocking {
    val job = launch {
        repeat(50) { i ->
            println("job: I'm sleeping $i ...")
            delay(500L)
        }
    }
    delay(2300L) // delay a bit
    println("main: I'm tired of waiting!")
    job.cancel() // cancels the job
//    job.join() // waits for job's completion
    println("main: Now I can quit.")

    /**********************/

//    val startTime = System.currentTimeMillis()
//    val job = launch(Dispatchers.Default) {
//        var nextPrintTime = startTime
//        var i = 0
////        while (i < 5) { // computation loop, just wastes CPU
//        while (isActive) {
//            // print a message twice a second
//            if (System.currentTimeMillis() >= nextPrintTime) {
//                println("job: I'm sleeping ${i++} ...")
//                nextPrintTime += 500L
//            }
//        }
//    }
//    delay(1300L) // delay a bit
//    println("main: I'm tired of waiting!")
//    job.cancelAndJoin() // cancels the job and waits for its completion
//    println("main: Now I can quit.")

    /**********************/

//    val job = launch {
//        try {
//            repeat(1000) { i ->
//                println("job: I'm sleeping $i ...")
//                delay(500L)
//            }
//        } finally {
//            println("job: I'm running finally")
//        }
//    }
//    delay(1300L) // delay a bit
//    println("main: I'm tired of waiting!")
//    job.cancelAndJoin() // cancels the job and waits for its completion
//    println("main: Now I can quit.")

    /**********************/

//    val job = launch {
//        try {
//            repeat(1000) { i ->
//                println("job: I'm sleeping $i ...")
//                delay(500L)
//            }
//        } finally {
//            withContext(NonCancellable) {
//                println("job: I'm running finally")
//                delay(1000L)
//                println("job: And I've just delayed for 1 sec because I'm non-cancellable")
//            }
//        }
//    }
//    delay(1300L) // delay a bit
//    println("main: I'm tired of waiting!")
//    job.cancelAndJoin() // cancels the job and waits for its completion
//    println("main: Now I can quit.")

    /**********************/

//    withTimeout(1300L) {
//        repeat(1000) { i ->
//            println("I'm sleeping $i ...")
//            delay(500L)
//        }
//    }

    /**********************/

//    val result = withTimeoutOrNull(1300L) {
//        repeat(100) { i ->
//            println("I'm sleeping $i ...")
//            delay(500L)
//        }
//        "Done" // will get cancelled before it produces this result
//    }
//    println("Result is $result")
}