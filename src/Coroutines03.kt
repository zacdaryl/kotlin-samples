import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.concurrent.thread

fun main() = runBlocking {
//    repeat(100_000) {
//        launch {
//            delay(1000L)
//            print(".")
//        }
////        thread {
////            Thread.sleep(1000L)
////            print(".")
////        }
//    }

    GlobalScope.launch {
        repeat(1000) { i ->
            println("I am sleeping $i")
            delay(500L)
        }
    }

    delay(2100L)
}