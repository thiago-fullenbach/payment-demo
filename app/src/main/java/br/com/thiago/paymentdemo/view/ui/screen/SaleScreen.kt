package br.com.thiago.paymentdemo.view.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import br.com.thiago.paymentdemo.view.ui.component.AmountSelectDialog
import br.com.thiago.paymentdemo.view.ui.component.PrimaryButton
import br.com.thiago.paymentdemo.view.ui.component.SecondaryButton
import br.com.thiago.paymentdemo.view.ui.theme.PaymentDemoTheme
import br.com.thiago.paymentdemo.view.ui.theme.s1
import br.com.thiago.paymentdemo.view.ui.theme.s2
import br.com.thiago.paymentdemo.view.ui.theme.s4
import br.com.thiago.paymentdemo.viewmodel.SaleViewModel

@Composable
fun SaleScreen(viewModel: SaleViewModel = viewModel(factory = SaleViewModel.Factory)) {
    var showDialog by rememberSaveable { mutableStateOf(false) }

    if (showDialog) {
        AmountSelectDialog(
            onConfirm = { amount ->
                // TODO: Add confirmation behaviour here
            },
            onDismiss = { showDialog = false }
        )
    }

    Column(modifier = Modifier
        .safeDrawingPadding()
        .fillMaxSize()) {
        Column(
            modifier = Modifier
                .padding(s4)
        ) {
            Text(text = "Vendas", style = MaterialTheme.typography.headlineSmall)
            Spacer(Modifier.height(s1))
            Text(
                text = "Terminal 1234",
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        // TODO: Extract component and add LazyColumn with values
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Nenhuma venda ainda. Toque em Vender para começar.",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        Column(modifier = Modifier.padding(s4)) {
            PrimaryButton(modifier = Modifier.fillMaxWidth(), onClick = { showDialog = true }) {
                Text("Vender", style = MaterialTheme.typography.titleMedium)
            }
            Spacer(Modifier.height(s2))
            SecondaryButton(modifier = Modifier.fillMaxWidth(), onClick = {}) {
                Text("Verificar Pendentes", style = MaterialTheme.typography.titleMedium)
            }
        }
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun SaleScreenPreview() {
    PaymentDemoTheme {
        SaleScreen()
    }
}