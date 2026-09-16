package br.com.thiago.paymentdemo.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import br.com.thiago.paymentdemo.Acquirer
import br.com.thiago.paymentdemo.FakeAcquirer
import br.com.thiago.paymentdemo.model.Sale
import br.com.thiago.paymentdemo.model.SaleState
import br.com.thiago.paymentdemo.utils.UuidUtils
import br.com.thiago.paymentdemo.utils.toSaleState
import br.com.thiago.paymentdemo.utils.updateState
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SaleViewModel(private val acquirer: Acquirer) : ViewModel() {
    private val _sales = MutableStateFlow<List<Sale>>(emptyList())
    val sales = _sales.asStateFlow()

    private val _events = MutableSharedFlow<SaleEvent>(extraBufferCapacity = 1)
    val events = _events.asSharedFlow()

    fun onIntent(intent: SaleIntent) {
        when (intent) {
            is SaleIntent.NewSale -> sell(intent.amountCents)
            is SaleIntent.VerifyUnknown -> verify()
        }
    }

    private fun sell(amountCents: Long) {
        val sale = Sale.brandNewSale(
            id = UuidUtils.generateRandomUuidStr(),
            amountCents = amountCents,
            idempotencyKey = UuidUtils.generateRandomUuidStr()
        )
        _sales.update { sales -> listOf(sale) + sales }
        _events.tryEmit(SaleEvent.Created(sale.amountCents))
        viewModelScope.launch {
            val newState = try {
                acquirer.send(sale.amountCents, sale.idempotencyKey).toSaleState()
            } catch (e: CancellationException) {
                throw e
            } catch (_: Exception) {
                SaleState.UNKNOWN
            }
            _sales.update { sales ->
                sales.updateState(sale.idempotencyKey, newState)
            }
        }
    }

    private fun verify() {
        viewModelScope.launch {
            val pending = _sales.value.filter { it.state == SaleState.UNKNOWN }
            _events.emit(SaleEvent.Verify())
            pending.forEach { sale ->
                val result = acquirer.status(sale.idempotencyKey)
                val newState = result?.toSaleState() ?: SaleState.DECLINED
                _sales.update { it.updateState(sale.idempotencyKey, newState) }
            }
            _events.emit(SaleEvent.Verified(pending.size))
        }
    }

    companion object {
        val Factory = viewModelFactory {
            initializer { SaleViewModel(FakeAcquirer()) }
        }
    }
}