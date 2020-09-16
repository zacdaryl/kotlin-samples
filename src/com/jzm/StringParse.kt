fun main() {
    val headers = "Content-Disposition: attachment; filename=\"filename.jpg\""

    println(headers.split("=")[1].replace("\"", ""))

    var text = getText()
//    if (text?.contains("bc")!!) println("safe")
    text?.length
    println(text?.length)

    text?.let {
        println("safe");
    }

//    if (text?.contains("h")) {
//        println("contains h")
//    }

    text?.contains("h")?.also {
        println("contains h")
    }

    println(text?.contains("h"))

    var safeCall: SafeCall?
    safeCall = null
    safeCall?.call()

}

fun getText(): String? {
//    return "hhh"
    return null
}

class SafeCall {
    fun call() {
        println("safe call")
    }
}