package br.com.thiago.paymentdemo.model

data class Sale(
    val id: String,
    val amountCents: Long,
    val idempotencyKey: String,
    val state: SaleState = SaleState.PENDING,
)

enum class SaleState {
    PENDING,
    CONFIRMED,
    FAILED,
    UNKNOWN
}

sealed class PaymentResult {
    object Approved: PaymentResult()
    object Declined: PaymentResult()
    object NoAnswer: PaymentResult()
}