package br.com.thiago.paymentdemo.view.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import br.com.thiago.paymentdemo.model.SaleState
import br.com.thiago.paymentdemo.utils.toBrl
import br.com.thiago.paymentdemo.view.ui.component.AmountSelectDialog
import br.com.thiago.paymentdemo.view.ui.component.PrimaryButton
import br.com.thiago.paymentdemo.view.ui.component.SaleList
import br.com.thiago.paymentdemo.view.ui.component.SecondaryButton
import br.com.thiago.paymentdemo.view.ui.theme.PaymentDemoTheme
import br.com.thiago.paymentdemo.view.ui.theme.s1
import br.com.thiago.paymentdemo.view.ui.theme.s2
import br.com.thiago.paymentdemo.view.ui.theme.s4
import br.com.thiago.paymentdemo.viewmodel.SaleEvent
import br.com.thiago.paymentdemo.viewmodel.SaleIntent
import br.com.thiago.paymentdemo.viewmodel.SaleViewModel

@Composable
fun SaleScreen(viewModel: SaleViewModel = viewModel(factory = SaleViewModel.Factory)) {
    var showDialog by rememberSaveable { mutableStateOf(false) }
    val sales by viewModel.sales.collectAsStateWithLifecycle()
    val hasUnknown by remember { derivedStateOf { sales.any { it.state == SaleState.UNKNOWN } } }
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(Unit) {
        viewModel.events.collect { event ->
            val message = when (event) {
                is SaleEvent.Created -> "Venda de ${event.amountCents.toBrl()} enviada"
                is SaleEvent.Verify -> "Realizando verificação de status"
                is SaleEvent.Verified -> "Verificou ${event.pendingCount} venda(s)"
            }
            snackbarHostState.showSnackbar(message)
        }
    }

    Scaffold(snackbarHost = { SnackbarHost(snackbarHostState) }) { paddingValues ->
        if (showDialog) {
            AmountSelectDialog(
                onConfirm = { amount ->
                    viewModel.onIntent(SaleIntent.NewSale(amount))
                },
                onDismiss = { showDialog = false }
            )
        }

        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
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

            SaleList(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f), sales = sales
            )

            Column(modifier = Modifier.padding(s4)) {
                PrimaryButton(modifier = Modifier.fillMaxWidth(), onClick = { showDialog = true }) {
                    Text("Vender", style = MaterialTheme.typography.titleMedium)
                }
                Spacer(Modifier.height(s2))
                SecondaryButton(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { viewModel.onIntent(SaleIntent.VerifyUnknown()) },
                    enabled = hasUnknown
                ) {
                    Text("Verificar Pendentes", style = MaterialTheme.typography.titleMedium)
                }
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