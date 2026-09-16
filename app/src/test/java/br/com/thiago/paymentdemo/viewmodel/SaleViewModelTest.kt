package br.com.thiago.paymentdemo.viewmodel

import app.cash.turbine.test
import br.com.thiago.paymentdemo.fake.ApprovingAcquirer
import br.com.thiago.paymentdemo.fake.ThrowingAcquirer
import br.com.thiago.paymentdemo.model.SaleState
import br.com.thiago.paymentdemo.rule.MainDispatcherRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class SaleViewModelTest {

    @get:Rule
    val mainRule = MainDispatcherRule()

    @Test
    fun `venda e gravada como PENDING antes de enviar`() = runTest {
        val vm = SaleViewModel(ApprovingAcquirer())

        vm.sales.test {
            assertTrue(awaitItem().isEmpty())

            vm.onIntent(SaleIntent.NewSale(100))
            assertEquals(SaleState.PENDING, awaitItem().single().state)

            advanceUntilIdle()
            assertEquals(SaleState.CONFIRMED, awaitItem().single().state)
        }
    }

    @Test
    fun `falha de rede vira UNKNOWN e nao DECLINED`() = runTest {
        val vm = SaleViewModel(ThrowingAcquirer())

        vm.onIntent(SaleIntent.NewSale(100))
        advanceUntilIdle()

        assertEquals(SaleState.UNKNOWN, vm.sales.value.first().state)
    }
}