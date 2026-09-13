package br.com.thiago.paymentdemo.view.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp

val Typography = Typography(
    headlineSmall = TextStyle(fontSize = 19.sp, fontWeight = FontWeight.SemiBold),
    titleMedium   = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.SemiBold),
    bodyMedium    = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.SemiBold),
    bodySmall     = TextStyle(fontSize = 13.sp, fontWeight = FontWeight.Medium),
    labelSmall    = TextStyle(fontSize = 12.sp),
)

val AmountDisplay = TextStyle(
    fontFamily = FontFamily.Monospace,
    fontSize = 38.sp,
    fontWeight = FontWeight.SemiBold,
    letterSpacing = (-0.02).em,
)

val AmountRow = TextStyle(fontFamily = FontFamily.Monospace, fontSize = 17.sp, fontWeight = FontWeight.Medium)

val KeyLabel  = TextStyle(fontFamily = FontFamily.Monospace, fontSize = 11.sp)

val DigitKey = TextStyle(
    fontFamily = FontFamily.Monospace,
    fontSize = 20.sp,
    fontWeight = FontWeight.Medium
)

val FunctionKey = TextStyle(fontSize = 15.sp)