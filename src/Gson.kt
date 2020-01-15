import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.google.gson.JsonPrimitive

val keyPair = RSA.generateKeyPair()
var encrypt = true

fun main() {
    val jsonObject = JsonObject()
    jsonObject.addProperty("id", "120")
    jsonObject.addProperty("name", "zhangsan")
    jsonObject.addProperty("phone", "18610808888")
    jsonObject.addProperty("identifier", "1101236879")

    val innerObj = JsonObject()
    innerObj.addProperty("innerId", "123")

    jsonObject.add("inner", innerObj)

    val innerArray = JsonArray()
    innerArray.add("a1")
    innerArray.add(99)
    innerArray.add('c')

    val arrayObj = JsonObject()
    arrayObj.addProperty("arrayObj", "obj in array")
    arrayObj.addProperty("phone", "18899999999")
    innerArray.add(arrayObj)

    jsonObject.add("innerArray", innerArray)

    printGson(jsonObject)

//    val keySet = jsonObject.keySet()
//    keySet.forEach {
//        if (it == "phone") jsonObject.addProperty(it, "88888888")
//    }

    println("*********")
    encrypt = false

    printGson(jsonObject)

    println("*********")

    transform(jsonObject)
}

fun printGson(jsonObject: JsonObject) {
    val entrySet = jsonObject.entrySet()
    entrySet.map { entry ->
        when (entry.value) {
            is JsonPrimitive -> {
                println("key: ${entry.key}, value: ${entry.value}")
                if (entry.key == "phone" && encrypt) {
                    jsonObject.addProperty(entry.key, RSA.encrypt(keyPair.second, entry.value.asString))
                }
            }
            is JsonObject -> {
                printGson(entry.value as JsonObject)
            }
            is JsonArray -> {
                val array = entry.value as JsonArray
                array.forEach {
                    if (it.isJsonPrimitive) {
                        println("array value: $it")
                    }else if (it.isJsonObject) {
                        printGson(it as JsonObject)
                    }
                }
            }
        }
    }
}

fun transform(jsonObject: JsonObject) {
    val list = jsonObject.entrySet().filter {
        it.value is JsonPrimitive
    }.map {
        Pair(it.key, it.value)
    }

    println(list)
}