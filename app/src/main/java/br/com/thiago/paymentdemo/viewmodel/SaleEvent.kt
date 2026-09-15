package br.com.thiago.paymentdemo.viewmodel

sealed interface SaleEvent {
    class Created(val amountCents: Long): SaleEvent
}