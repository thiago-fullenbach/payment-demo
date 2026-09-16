package br.com.thiago.paymentdemo.view.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import br.com.thiago.paymentdemo.view.ui.theme.PaymentDemoTheme
import br.com.thiago.paymentdemo.view.ui.theme.s2

@Composable
fun Keypad(
    modifier: Modifier = Modifier,
    onDigit: (digit: Int) -> Unit,
    onErase: () -> Unit,
) {
    Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(s2)) {
        listOf(
            listOf("1","2","3"),
            listOf("4","5","6"),
            listOf("7","8","9"),
        ).forEach { row ->
            Row(horizontalArrangement = Arrangement.spacedBy(s2)) {
                row.forEach { label ->
                    KeyButton(
                        modifier = Modifier.weight(1f),
                        label = label,
                        onClick = { onDigit(label.toInt()) })
                }
            }
        }

        Row(horizontalArrangement = Arrangement.spacedBy(s2)) {
            KeyButton(modifier = Modifier.weight(1f), label = "Apagar", onClick = onErase, isFunction = true)
            KeyButton(modifier = Modifier.weight(1f), label = "0", onClick = { onDigit(0) })
            KeyButton(modifier = Modifier.weight(1f), label = "00", onClick = { onDigit(0); onDigit(0) })
        }
    }
}

@Composable
@Preview(showBackground = true)
fun KeypadPreview() {
    PaymentDemoTheme {
        Keypad(
            onDigit = {},
            onErase = {}
        )
    }
}