package br.com.thiago.paymentdemo.view.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColorScheme = lightColorScheme(
    primary = Ink,
    onPrimary = Card,
    background = Surface,
    onBackground = Ink,
    surface = Card,
    onSurface = Ink,
    onSurfaceVariant = InkSoft,
    outline = Hairline,
)

@Composable
fun PaymentDemoTheme(
    content: @Composable () -> Unit
) {
    val colorScheme = LightColorScheme
    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}