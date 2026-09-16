package br.com.thiago.paymentdemo.fake

import br.com.thiago.paymentdemo.Acquirer
import br.com.thiago.paymentdemo.model.PaymentResult

class ApprovingAcquirer: Acquirer {
    override suspend fun send(
        amountCents: Long,
        idempotencyKey: String
    ): PaymentResult = PaymentResult.Approved

    override suspend fun status(idempotencyKey: String): PaymentResult? = PaymentResult.Approved
}