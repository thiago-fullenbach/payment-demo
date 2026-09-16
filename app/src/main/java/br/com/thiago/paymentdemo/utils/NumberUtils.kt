package br.com.thiago.paymentdemo.utils

import java.text.NumberFormat
import java.util.Locale

private val brl = NumberFormat.getCurrencyInstance(Locale.forLanguageTag("pt-BR"))

fun Long.toBrl(): String = brl.format(this / 100.0)