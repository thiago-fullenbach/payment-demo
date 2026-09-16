package br.com.thiago.paymentdemo.viewmodel

sealed interface SaleEvent {
    class Created(val amountCents: Long): SaleEvent
    class Verify: SaleEvent
    class Verified(val pendingCount: Int): SaleEvent
}