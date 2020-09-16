class TryCatch {
    var a = 1;
    fun foo(): Int{
        try {
            println("try: a = ${++a}")
            10/0
            return a 
        }
        catch(e: Exception) {
            println("catch: a = ${++a}")
            return a
        }
        finally {
            println("finally: a = ${++a}")
        } 
    }
}

fun main(args: Array<String>) {
    val trycatch = TryCatch()
    val reta = trycatch.foo()
    println("com.jzm.security.com.jzm.coroutines.main: reta = $reta, a = ${trycatch.a}")
}

