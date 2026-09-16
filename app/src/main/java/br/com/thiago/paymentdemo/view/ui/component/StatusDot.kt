package br.com.thiago.paymentdemo.view.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.thiago.paymentdemo.view.ui.theme.Confirmed
import br.com.thiago.paymentdemo.view.ui.theme.PaymentDemoTheme

@Composable
fun StatusDot(modifier: Modifier = Modifier, color: Color) {
    Box(
        modifier = modifier
            .size(6.dp)
            .background(color, CircleShape)
    )
}

@Composable
@Preview(showBackground = true)
fun StatusDotPreview() {
    PaymentDemoTheme {
        StatusDot(color = Confirmed)
    }
}