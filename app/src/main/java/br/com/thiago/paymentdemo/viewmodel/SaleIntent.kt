package br.com.thiago.paymentdemo.viewmodel

sealed interface SaleIntent {
    class NewSale(val amountCents: Long): SaleIntent
}