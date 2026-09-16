package br.com.thiago.paymentdemo.view.ui.model

import androidx.compose.ui.graphics.Color
import br.com.thiago.paymentdemo.model.SaleState
import br.com.thiago.paymentdemo.view.ui.theme.Confirmed
import br.com.thiago.paymentdemo.view.ui.theme.Declined
import br.com.thiago.paymentdemo.view.ui.theme.Pending
import br.com.thiago.paymentdemo.view.ui.theme.Unknown

fun SaleState.label(): String = when (this) {
    SaleState.PENDING   ->  "Enviando"
    SaleState.CONFIRMED ->  "Aprovada"
    SaleState.DECLINED  ->  "Recusada"
    SaleState.UNKNOWN   ->  "Sem resposta"
}

fun SaleState.markColor(): Color = when (this) {
    SaleState.PENDING   ->  Pending
    SaleState.CONFIRMED ->  Confirmed
    SaleState.DECLINED  ->  Declined
    SaleState.UNKNOWN   ->  Unknown
}