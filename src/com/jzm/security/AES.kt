package com.jzm.security

import java.security.SecureRandom
import java.util.*
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey
import javax.crypto.spec.SecretKeySpec
import kotlin.random.Random

class AES {
    companion object {
        private const val transformation = "com.jzm.security.AES/ECB/PKCS5Padding"

        fun encrypt(key: String, plainText: String): String {
            val secretKey = secretKey(key)
            val keySpec = SecretKeySpec(secretKey.encoded, "com.jzm.security.AES")
            val cipher = Cipher.getInstance(transformation)
            cipher.init(Cipher.ENCRYPT_MODE, keySpec)

            val cipherByteArray = cipher.doFinal(plainText.toByteArray())
            return Base64.getEncoder().encodeToString(cipherByteArray)
        }

        fun decrypt(key: String, cipherText: String): String{
            val secretKey = secretKey(key)
            val keySpec = SecretKeySpec(secretKey.encoded, "com.jzm.security.AES")
            val cipher = Cipher.getInstance(transformation)
            cipher.init(Cipher.DECRYPT_MODE, keySpec)

            val base64DecodeArray = Base64.getDecoder().decode(cipherText)
            val plainByteArray = cipher.doFinal(base64DecodeArray)

            return String(plainByteArray)
        }

        fun randomKey(): String{
            val alphanumeric = ('0'..'9') + ('a'..'z') + ('A'..'Z')
            return List(32) { Random.nextInt(alphanumeric.size) }
                .map { alphanumeric[it] }
                .joinToString(separator = "")
        }

        private fun secretKey(key: String): SecretKey {
            val keyGenerator = KeyGenerator.getInstance("com.jzm.security.AES")
            val secureRandom = SecureRandom.getInstance("SHA1PRNG")
            secureRandom.setSeed(key.toByteArray())
            keyGenerator.init(128, secureRandom)
            return keyGenerator.generateKey()
        }
    }
}

fun main() {
    val keyStr = AES.randomKey();
    println("com.jzm.security.AES key: $keyStr")

    val plainText = "18610886666 AES测试"
    val cipherText = AES.encrypt(keyStr, plainText)

    println("com.jzm.security.AES encrypted: $cipherText")

    val decryptedText = AES.decrypt(keyStr, cipherText)
    println("com.jzm.security.AES decrypted: $decryptedText")
}