package br.com.thiago.paymentdemo.fake

import br.com.thiago.paymentdemo.Acquirer
import br.com.thiago.paymentdemo.model.PaymentResult
import java.io.IOException

class ThrowingAcquirer: Acquirer {
    override suspend fun send(
        amountCents: Long,
        idempotencyKey: String
    ): PaymentResult =
        throw IOException("Erro de rede")

    override suspend fun status(idempotencyKey: String): PaymentResult? = null

}