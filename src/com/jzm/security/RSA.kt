package com.jzm.security

import java.security.*
import java.security.spec.PKCS8EncodedKeySpec
import java.security.spec.X509EncodedKeySpec
import java.util.*
import javax.crypto.Cipher

class RSA {
    companion object {
        const val transformation = "com.jzm.security.RSA/ECB/PKCS1Padding"

        // 公钥加密
        fun encrypt(publicKey: String, plainText: String): String {
            val byteArray = Base64.getDecoder().decode(publicKey)
            val keyFactory = KeyFactory.getInstance("com.jzm.security.RSA")
            val keySpec = X509EncodedKeySpec(byteArray)
            val pubKey = keyFactory.generatePublic(keySpec) as PublicKey

            val cipher = Cipher.getInstance(transformation)
            cipher.init(Cipher.ENCRYPT_MODE, pubKey)

            val cipherByteArray = cipher.doFinal(plainText.toByteArray())
            val base64 = Base64.getEncoder().encode(cipherByteArray)

            return String(base64)
        }

        // 私钥解密
        fun decrypt(privateKey: String, cipherText: String): String {
            val base64Buffer = Base64.getDecoder().decode(privateKey)
            val keySpec = PKCS8EncodedKeySpec(base64Buffer)
            val keyFactory = KeyFactory.getInstance("com.jzm.security.RSA")
            val priKey = keyFactory.generatePrivate(keySpec) as PrivateKey

            val cipher = Cipher.getInstance(transformation)
            cipher.init(Cipher.DECRYPT_MODE, priKey)

            val rawByteArray = Base64.getDecoder().decode(cipherText.toByteArray())
            val plainBuffer = cipher.doFinal(rawByteArray)
            return String(plainBuffer)
        }

        fun verify(publicKey: String, sign: String, data: String): Boolean {
            val byteArray = Base64.getDecoder().decode(publicKey)
            val keyFactory = KeyFactory.getInstance("com.jzm.security.RSA")
            val keySpec = X509EncodedKeySpec(byteArray)
            val pubKey = keyFactory.generatePublic(keySpec) as PublicKey

            val signature = Signature.getInstance("SHA1withRSA")
            signature.initVerify(pubKey)
            signature.update(data.toByteArray())

            val isPassed = signature.verify(Base64.getDecoder().decode(sign))
            return isPassed
        }

        fun sign(privateKey: String, data: String): String {
            val base64Buffer = Base64.getDecoder().decode(privateKey)
            val keySpec = PKCS8EncodedKeySpec(base64Buffer)
            val keyFactory = KeyFactory.getInstance("com.jzm.security.RSA")
            val priKey = keyFactory.generatePrivate(keySpec) as PrivateKey

            val signature = Signature.getInstance("SHA1withRSA")
            signature.initSign(priKey)
            signature.update(data.toByteArray())

            val byteArray = signature.sign()
            return Base64.getEncoder().encodeToString(byteArray)
        }

        //生成公私钥对
        fun generateKeyPair(): Pair<String, String> {
            val keyPairGenerator = KeyPairGenerator.getInstance("com.jzm.security.RSA")
            val keypair = keyPairGenerator.genKeyPair()

            val private = Base64.getEncoder().encodeToString(keypair.private.encoded)
            val public = Base64.getEncoder().encodeToString(keypair.public.encoded)

            return Pair(private, public)
        }
    }
}

fun main() {
    val keyPair = RSA.generateKeyPair()

    val plainText = "2020哈哈"

//    var pub = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDdaJz4jsBw8YUfHtOsJIXqi/LBuEJcLLy2C2avORSPCbNmph9f67owSByw+nt4AL6nsB4IdcC6IeOUUjJSLnPskFrX18O7QYE9dr3cwypOfllB2dr6MsEJH5yDpvIIbaVCt3rZZ6tzgatbPdUpop8/t7f7QTjbCYvYx2W0k9SFJwIDAQAB"

    println("public key:\n ${keyPair.second}")
//    println("public key: $pub")
//    pub = keyPair.second
    val cipherText = RSA.encrypt(keyPair.second, plainText)
    println("com.jzm.security.RSA encrypt: $cipherText")

    println("private key: ${keyPair.first}")
    val decryptedText = RSA.decrypt(keyPair.first, cipherText)
    println("com.jzm.security.RSA decrypt: $decryptedText")
    println("decrypt result is equal to plain text? ${decryptedText == plainText}")

    //签名、验签
    val sign = RSA.sign(keyPair.first, cipherText)
    println("sign: $sign")
    println("verify sign: ${RSA.verify(keyPair.second, sign, cipherText)}")


//    // 验签名
//    val sign = "Txa7faf42HJXSee+iNDk+Hm7qtzzG2rba7yHTDYoBSCg1dAASvmbdBdSN1QEJFrkOQbrSECVzuvdo4s/pelIcvpLkpdJwWrFqLwRSah4hgS/VsgNSpd8iPM5A8TQhooqnrpch2Y62zcgmaK7hqcuB+SdkeDJhnvC75/iozZJwc8="
//    val data = "PZClfikEFH2atueBFd8+1g3FnlYWLus7iobk8pgYTkZLaY7G1HM4UTwnMAcs7yLQ3AbshGvjAE/pRcNaEusdn8zGVTRVRKogookPrv3GpW5e42l6+bwH5bRnxLszfCeprHbqlJ9Q5Nsm2BMVh3Gk+qTAUzlYUvyX9k/g7l3ACzvoOIjHKdLkWpQdP89tNCurMwsIDHbp9KdPWK/fpAekEPG6q/nmtkPZLy2lKEWKo4lMz+da/JxwSKCR0e0fZ4KdOFNuQHhp/R1Wk0I+dKjUzoheWuJ0TRL9HJiUyZJkwI20OZpS+GkpJnqgNj8rJcJbMGs/ZX4InFeOA4HR5ijC0/UiC8+hbakbhcwyp6nbvA8NAUbkRJiC8cT68etxaZzBvo4HbvtonZW5xCbXXfwfEw8Q+YzT5bGZQkt+GWRa1S5lISIyJ1J8W1I6IpHkQJqv9k/g7l3ACztZRxbHkktZ5DKnaURiDOA2gYjCAlacHYPO1Ut3hWvlJ2hbOOBDzz/La9th30a/oTGM5bdIY1PcXIF66S9bNrHqSpa/deTkuy7k+Us5MBr9mgvzR5UbFL1de+Kcwd3GLHrlLgjISLXmqFI6IpHkQJqv9k/g7l3ACzsds3TCtSMw9vofODhiLIDU5mTe8EVlhlLioIwoQSyOgrjAKUVMdVSZT+jksn3KGX/3MrwLSUSsvPyR4/YgF34eeXCTX2jb5FHdZMWkiwGlMstEm+kyUD/ankBjp8Y2ndbFHXt+cS3SqEZpV8KmqpC6QrztTy17FRH9cBrCT8QoovlXLd5gCumuJQLwn+rebdp6XjmtZ+oaMoxDUEb4beiWXe51Dve6hK71eFt6MwO9hFBXbx85G3oN1H7xzWXFQbl0X84W5+MVQFRKlf91VUr0vKtzecAwMZRIGRdfkXLdn5unallTEGUH"
//
//    val isPassed = com.jzm.security.RSA.verify(pub, sign, data)
//    println("verify sign: $isPassed")
}