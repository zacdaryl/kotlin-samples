package com.jzm

import kotlin.random.Random

class ScopeFun {
    fun getRandomInt(): Int {
        return Random.nextInt(100).also {
            println("getRandomInt() generated value $it")
            println("getRandomInt()  $this")
        }
    }

    fun r(): String? {
        val str: String? = "d"
        val a = str?.let {
//            return "h"
            "b"
        }

        return a

//        return "r"
    }
}

fun main() {
    ScopeFun().getRandomInt()

    val str = "Hello"

    str.run {
        println("The receiver string length: $length")
        println("The receiver string length: ${this.length}")

        //Error:(22, 47) Kotlin: Unresolved reference: it
//        println("The receiver string length: $it")
    }

    str.let {
        println("The receiver string length: ${it.length}")

        //Error:(29, 48) Kotlin: 'this' is not defined in this context
//        println("The receiver string length: ${this.length}")
    }

    mutableListOf<Int>().apply {
        add(1)
        add(2)
        add(3)
        add(4)
    }.also { println(it) }

    mutableListOf<Int>().also {
        it.add(1)
        it.add(2)
        it.add(4)
        it.add(3)
    }
        .also { println(it) }
        .sortDescending()

    ScopeFun().r().also (::println)

}
