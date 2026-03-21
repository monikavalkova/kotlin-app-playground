package com.example.counterapp

import kotlinx.coroutines.flow.Flow

interface CounterRepository {
    fun getCounter(): Flow<Int>
    suspend fun incrementCounter()
    suspend fun resetCounter()
}
