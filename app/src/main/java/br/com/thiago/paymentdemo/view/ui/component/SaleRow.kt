package br.com.thiago.paymentdemo.view.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.thiago.paymentdemo.model.Sale
import br.com.thiago.paymentdemo.model.SaleState
import br.com.thiago.paymentdemo.utils.toBrl
import br.com.thiago.paymentdemo.view.ui.model.label
import br.com.thiago.paymentdemo.view.ui.model.markColor
import br.com.thiago.paymentdemo.view.ui.theme.AmountRow
import br.com.thiago.paymentdemo.view.ui.theme.KeyLabel
import br.com.thiago.paymentdemo.view.ui.theme.PaymentDemoTheme
import br.com.thiago.paymentdemo.view.ui.theme.s2
import br.com.thiago.paymentdemo.view.ui.theme.s3
import br.com.thiago.paymentdemo.view.ui.theme.s4

@Composable
fun SaleRow(
    modifier: Modifier = Modifier,
    sale: Sale
) {
    val isUnknown = sale.state == SaleState.UNKNOWN
    val barWidth = if (isUnknown) 6.dp else 3.dp
    val color = sale.state.markColor()

    Column(modifier = modifier) {
        Row(modifier = Modifier.height(IntrinsicSize.Min)) {
            Box(
                Modifier
                    .width(barWidth)
                    .fillMaxHeight()
                    .background(color)
            )
            Column(modifier = Modifier.padding(start = s4 - barWidth, top = s3, end = s4, bottom = s3)) {
                Text(sale.amountCents.toBrl(), style = AmountRow)
                Spacer(Modifier.height(s3))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    StatusDot(color = color)
                    Spacer(Modifier.width(6.dp))
                    Text(
                        sale.state.label(),
                        color = color,
                        style = MaterialTheme.typography.bodySmall
                    )
                }
                Spacer(Modifier.height(s2))
                Text(
                    sale.idempotencyKey.take(8),
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    style = KeyLabel
                )
                if (isUnknown) {
                    Spacer(Modifier.height(s2))
                    Text(
                        "Pode ter sido cobrada. Verifique antes de passar de novo.",
                        color = color,
                        style = MaterialTheme.typography.labelSmall
                    )
                }
            }
        }
        HorizontalDivider(color = MaterialTheme.colorScheme.outline)
    }
}

@Composable
@Preview(showBackground = true)
fun SaleRowPreview() {
    PaymentDemoTheme {
        SaleRow(sale = Sale("abcd1234", 100L, "abcd1234"))
    }
}