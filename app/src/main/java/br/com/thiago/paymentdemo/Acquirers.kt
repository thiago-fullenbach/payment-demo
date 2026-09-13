package br.com.thiago.paymentdemo

import br.com.thiago.paymentdemo.model.PaymentResult
import kotlinx.coroutines.delay

interface Acquirer {
    suspend fun send(amountCents: Long, idempotencyKey: String): PaymentResult
    suspend fun status(idempotencyKey: String): PaymentResult?
}

class FakeAcquirer: Acquirer {
    private val salesMap = mutableMapOf<String, PaymentResult>()
    private val delaySend: Long = 1_500
    private val delayStatus: Long = 100

    override suspend fun send(amountCents: Long, idempotencyKey: String): PaymentResult {
        val paymentState = salesMap[idempotencyKey]
        if (paymentState != null)
            return paymentState
        delay(delaySend)
        salesMap[idempotencyKey] = PaymentResult.Approved
        return PaymentResult.Approved
    }

    override suspend fun status(idempotencyKey: String): PaymentResult? {
        delay(delayStatus)
        return salesMap[idempotencyKey]
    }
}