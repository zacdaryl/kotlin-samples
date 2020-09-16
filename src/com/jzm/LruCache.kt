package com.jzm

class LruCache {
}

fun main() {
    val linkedHashMap = LinkedHashMap<String, String>().apply {
        put("a", "1")
        put("b", "2")
        put("c", "3")
        put("d", "4")
    }


//    val value = linkedHashMap.get("b")
//    println("value get from lhm: $value")

    linkedHashMap.put("b", "2b")
    println(linkedHashMap.entries)
    linkedHashMap.put("e", "e")
    println(linkedHashMap.entries)
    linkedHashMap.put("b", "3b")
    println(linkedHashMap.entries)

    val iterator = linkedHashMap.entries.iterator()
    val last = iterator.next()
    println("last: $last")
}