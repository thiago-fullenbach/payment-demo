package br.com.thiago.paymentdemo.utils

import java.util.UUID

object UuidUtils {
    fun generateRandomUuidStr(): String {
        return UUID.randomUUID().toString()
    }
}