package com.jzm.security

import java.util.*
import javax.crypto.Cipher
import javax.crypto.spec.SecretKeySpec
import kotlin.random.Random

class DES {
    companion object {
        private const val transformation = "com.jzm.security.DES/ECB/PKCS5Padding"

        fun encrypt(key: String, plainText: String): String {
            val keySpec = SecretKeySpec(key.toByteArray(), "com.jzm.security.DES")
            val cipher = Cipher.getInstance(transformation)
            cipher.init(Cipher.ENCRYPT_MODE, keySpec)

            val cipherByteArray = cipher.doFinal(plainText.toByteArray())
            val base64ByteArray = Base64.getEncoder().encode(cipherByteArray)

            return String(base64ByteArray)
        }

        fun decrypt(key: String, cipherText: String): String{
            val keySpec = SecretKeySpec(key.toByteArray(), "com.jzm.security.DES")
            val cipher = Cipher.getInstance(transformation)
            cipher.init(Cipher.DECRYPT_MODE, keySpec)

            val base64DecodeArray = Base64.getDecoder().decode(cipherText)
            val plainByteArray = cipher.doFinal(base64DecodeArray)

            return String(plainByteArray)
        }

        fun randomKey(): String{
            val alphanumeric = ('0'..'9') + ('a'..'z') + ('A'..'Z')
            return List(8) { Random.nextInt(alphanumeric.size) }
                .map { alphanumeric[it] }
                .joinToString(separator = "")
        }
    }
}

fun main() {
//    val name = com.jzm.security.DES.decrypt("JQgajVCB", "FyS12O7Wbxlq+YMWCcAp4zQvD0S2mV+N")
//    println(name)

    println(DES.encrypt("JQgajVCB", "123"))
    println("***")
}