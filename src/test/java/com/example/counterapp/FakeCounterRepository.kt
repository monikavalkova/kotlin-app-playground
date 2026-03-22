package com.example.counterapp

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class FakeCounterRepository : CounterRepository {
    
    private val counterFlow = MutableStateFlow(0)
    
    override fun getCounter(): Flow<Int> = counterFlow
    
    override suspend fun incrementCounter() {
        counterFlow.value += 1
    }
    
    override suspend fun resetCounter() {
        counterFlow.value = 0
    }
}
