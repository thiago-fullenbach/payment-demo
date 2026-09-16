package br.com.thiago.paymentdemo.view.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.thiago.paymentdemo.utils.toBrl
import br.com.thiago.paymentdemo.view.ui.theme.AmountDisplay
import br.com.thiago.paymentdemo.view.ui.theme.PaymentDemoTheme
import br.com.thiago.paymentdemo.view.ui.theme.s2
import br.com.thiago.paymentdemo.view.ui.theme.s5

@Composable
fun AmountDisplay(
    modifier: Modifier = Modifier,
    amount: Long
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Text("Valor da venda", style = MaterialTheme.typography.titleMedium)
        Spacer(Modifier.height(s5))
        Text(amount.toBrl(), modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.End, style = AmountDisplay)
        Spacer(Modifier.height(s2))
        Box(Modifier.fillMaxWidth().height(2.dp).background(MaterialTheme.colorScheme.onSurface))
    }
}

@Composable
@Preview(showBackground = true)
fun AmountDisplayPreview() {
    PaymentDemoTheme {
        AmountDisplay(amount = 0L)
    }
}