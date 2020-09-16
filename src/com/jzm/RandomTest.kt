import java.util.*
import kotlin.random.Random

fun main() {
    val randomString = UUID.randomUUID().toString().substring(0,8)
    println(randomString)

    val alphanumeric = ('0'..'9') + ('A'..'Z') + ('a'..'z')

    val random = List(8) { Random.nextInt(0, alphanumeric.size) }
        .map { alphanumeric[it] }
        .joinToString(separator = "")

    println(random.toUpperCase())

    val randomIntList = List(2) {Random.nextInt(10)}
    println(randomIntList)
}