package br.com.thiago.paymentdemo.utils

import br.com.thiago.paymentdemo.model.PaymentResult
import br.com.thiago.paymentdemo.model.Sale
import br.com.thiago.paymentdemo.model.SaleState

fun PaymentResult.toSaleState(): SaleState = when (this) {
    PaymentResult.Approved -> SaleState.CONFIRMED
    PaymentResult.Declined -> SaleState.FAILED
    PaymentResult.NoAnswer -> SaleState.UNKNOWN
}

fun List<Sale>.updateState(idempotencyKey: String, newState: SaleState): List<Sale> =
    map { if (it.idempotencyKey == idempotencyKey) it.copy(state = newState) else it }