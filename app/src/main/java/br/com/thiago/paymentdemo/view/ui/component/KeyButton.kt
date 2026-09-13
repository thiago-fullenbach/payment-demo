package br.com.thiago.paymentdemo.view.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.heightIn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.thiago.paymentdemo.view.ui.theme.DigitKey
import br.com.thiago.paymentdemo.view.ui.theme.FunctionKey
import br.com.thiago.paymentdemo.view.ui.theme.PaymentDemoTheme

@Composable
fun KeyButton(
    modifier: Modifier = Modifier,
    label: String,
    onClick: () -> Unit,
    isFunction: Boolean = false
) {
    OutlinedButton(
        onClick = onClick,
        modifier = modifier.heightIn(min = 48.dp),
        shape = MaterialTheme.shapes.small,
        contentPadding = PaddingValues(0.dp),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline)
    ) {
        Text(
            text = label,
            style = if (isFunction) FunctionKey else DigitKey
        )
    }
}

@Composable
@Preview(showBackground = true)
fun KeyButtonPreview() {
    PaymentDemoTheme {
        KeyButton(label = "0", onClick = {})
    }
}