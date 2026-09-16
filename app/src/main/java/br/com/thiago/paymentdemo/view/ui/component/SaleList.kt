package br.com.thiago.paymentdemo.view.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import br.com.thiago.paymentdemo.model.Sale
import br.com.thiago.paymentdemo.model.SaleState
import br.com.thiago.paymentdemo.view.ui.theme.PaymentDemoTheme

@Composable
fun SaleList(
    modifier: Modifier = Modifier,
    sales: List<Sale>
) {
    if(sales.isEmpty()) {
        Box(
            modifier = modifier,
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Nenhuma venda ainda. Toque em Vender para começar.",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    } else {
        LazyColumn(modifier = modifier) {
            items(items = sales, key = { it.idempotencyKey }) {
                SaleRow(sale = it)
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun SaleListWithoutContentPreview() {
    PaymentDemoTheme {
        SaleList(sales = emptyList())
    }
}

@Composable
@Preview(showBackground = true)
fun SaleListWithContentPreview() {
    PaymentDemoTheme {
        SaleList(sales = listOf(
            Sale("abcd1234", 100L, "abcd1234"),
            Sale("bcde1234", 100L, "bcde1234", SaleState.CONFIRMED),
            Sale("cdef1234", 100L, "cdef1234", SaleState.DECLINED),
            Sale("defg1234", 100L, "defg1234", SaleState.UNKNOWN)
        ))
    }
}