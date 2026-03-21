package com.example.counterapp

import app.cash.turbine.test
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class CounterViewModelTest {
    
    private val testDispatcher = StandardTestDispatcher()
    private lateinit var repository: FakeCounterRepository
    private lateinit var viewModel: CounterViewModel
    
    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        repository = FakeCounterRepository()
        viewModel = CounterViewModel(repository)
    }
    
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
    
    @Test
    fun `counter starts at zero`() = runTest(testDispatcher) {
        viewModel.counter.test {
            assertEquals(0, awaitItem())
        }
    }
    
    @Test
    fun `incrementing counter increases value by one`() = runTest(testDispatcher) {
        viewModel.counter.test {
            assertEquals(0, awaitItem())
            
            viewModel.incrementCounter()
            testScheduler.advanceUntilIdle()
            assertEquals(1, awaitItem())
            
            viewModel.incrementCounter()
            testScheduler.advanceUntilIdle()
            assertEquals(2, awaitItem())
        }
    }
    
    @Test
    fun `reset counter returns to zero`() = runTest(testDispatcher) {
        viewModel.counter.test {
            assertEquals(0, awaitItem())
            
            viewModel.incrementCounter()
            testScheduler.advanceUntilIdle()
            assertEquals(1, awaitItem())
            
            viewModel.incrementCounter()
            testScheduler.advanceUntilIdle()
            assertEquals(2, awaitItem())
            
            viewModel.resetCounter()
            testScheduler.advanceUntilIdle()
            assertEquals(0, awaitItem())
        }
    }
    
    @Test
    fun `multiple increments work correctly`() = runTest(testDispatcher) {
        viewModel.counter.test {
            assertEquals(0, awaitItem())
            
            repeat(10) {
                viewModel.incrementCounter()
                testScheduler.advanceUntilIdle()
                assertEquals(it + 1, awaitItem())
            }
        }
    }
}
