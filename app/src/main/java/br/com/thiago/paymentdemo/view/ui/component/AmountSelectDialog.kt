package br.com.thiago.paymentdemo.view.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import br.com.thiago.paymentdemo.view.ui.theme.PaymentDemoTheme
import br.com.thiago.paymentdemo.view.ui.theme.s2
import br.com.thiago.paymentdemo.view.ui.theme.s4

@Composable
fun AmountSelectDialog(
    onConfirm: (amount: Long) -> Unit,
    onDismiss: () -> Unit
) {
    var cents by rememberSaveable { mutableLongStateOf(0L) }

    Dialog(onDismissRequest = onDismiss) {
        Surface(shape = RoundedCornerShape(10.dp)) {
            Column(modifier = Modifier.padding(s4)) {
                AmountDisplay(amount = cents)
                Spacer(Modifier.height(s4))
                Keypad(
                    onDigit = { digit -> cents = cents * 10 + digit },
                    onErase = { cents /= 10 }
                )
                Spacer(Modifier.height(s4))
                Row {
                    SecondaryButton(modifier = Modifier.weight(1f), onClick = onDismiss) {
                        Text("Cancelar")
                    }
                    Spacer(Modifier.width(s2))
                    PrimaryButton(modifier = Modifier.weight(1f), onClick = { onConfirm(cents); onDismiss() }) {
                        Text("Cobrar")
                    }
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun SelectValueMenuPreview() {
    PaymentDemoTheme {
        AmountSelectDialog(
            onConfirm = {},
            onDismiss = {}
        )
    }
}