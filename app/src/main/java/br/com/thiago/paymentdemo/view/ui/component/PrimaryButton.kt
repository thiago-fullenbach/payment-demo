package br.com.thiago.paymentdemo.view.ui.component

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.thiago.paymentdemo.view.ui.theme.PaymentDemoTheme

@Composable
fun PrimaryButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    content: @Composable RowScope.() -> Unit
) {
    Button(
        modifier = modifier
            .heightIn(min = 52.dp),
        shape = RoundedCornerShape(6.dp),
        onClick = onClick,
    ) {
        content()
    }
}

@Composable
@Preview(showBackground = true)
fun PrimaryButtonPreview() {
    PaymentDemoTheme {
        PrimaryButton(onClick = {}) {
            Text("Button")
        }
    }
}