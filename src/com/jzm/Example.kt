package com.jzm

import sun.security.provider.MD5
import java.nio.charset.Charset
import java.security.MessageDigest
import java.util.*

class Foo {
    var value: Int = 0
        set(value) {
            field = value
            println("call in set fun: $field")
        }
}

enum class Type {
    json,
    form
}

fun main() {
    val nums = arrayOf(1,2,3,4)
    for (i in nums) {
        println(i)
    }

    for(i in nums.indices) {
        println(i)
    }


    //guid
    UUID.randomUUID().toString().plus(Date().time)
        .run {
            MessageDigest.getInstance("MD5")
                .digest(toByteArray())
                .joinToString("") { "%02x".format(it) }
        }
        .also { println("guid: $it") }

    //xor
    println("1 xor 2: ${1.xor(2)}")
    println("1 xor 3: ${1.xor(3)}")

    //shr, shl
    println("3 shr = ${3.shr(1)}")
    println("3 shl = ${3.shl(1)}")
    println("6 shr = ${6.shr(1)}")

    println("9 & 8 = ${9.and(8)}")

    //uuid
    println("uuid: ${UUID.randomUUID()}")
    println("time: ${Date().time}")
    return


    Foo()
        .apply { value = 5 }
        .apply { value = 6 }
        .also { println(it.value) }

    println(Type.json)

    println(Integer.bitCount(0))
    println(Integer.bitCount(1))
    println(Integer.bitCount(2))
    println(Integer.bitCount(3))
    println(Integer.bitCount(4))
    println(Integer.bitCount(5))
}