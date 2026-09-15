package br.com.thiago.paymentdemo.model

data class Sale(
    val id: String,
    val amountCents: Long,
    val idempotencyKey: String,
    val state: SaleState = SaleState.PENDING,
) {
    companion object {
        fun brandNewSale(id: String, amountCents: Long, idempotencyKey: String): Sale =
            Sale(
                id = id,
                amountCents = amountCents,
                idempotencyKey = idempotencyKey
            )
    }
}

enum class SaleState {
    PENDING,
    CONFIRMED,
    DECLINED,
    UNKNOWN
}

sealed class PaymentResult {
    object Approved: PaymentResult()
    object Declined: PaymentResult()
    object NoAnswer: PaymentResult()
}