package com.example.counterapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class CounterViewModel(
    private val repository: CounterRepository
) : ViewModel() {
    
    val counter: StateFlow<Int> = repository.getCounter()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = 0
        )
    
    fun incrementCounter() {
        viewModelScope.launch {
            repository.incrementCounter()
        }
    }
    
    fun resetCounter() {
        viewModelScope.launch {
            repository.resetCounter()
        }
    }
}
