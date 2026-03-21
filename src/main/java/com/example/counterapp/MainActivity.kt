package com.example.counterapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

private val ComponentActivity.dataStore by preferencesDataStore(name = "counter_prefs")
private val COUNTER_KEY = intPreferencesKey("counter_value")

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                CounterApp(this)
            }
        }
    }
}

@Composable
fun CounterApp(activity: MainActivity) {
    val scope = rememberCoroutineScope()
    val counterFlow = remember {
        activity.dataStore.data.map { preferences ->
            preferences[COUNTER_KEY] ?: 0
        }
    }
    val counter by counterFlow.collectAsState(initial = 0)
    
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "$counter",
                fontSize = 48.sp,
                modifier = Modifier.padding(bottom = 32.dp)
            )
            
            Button(
                onClick = {
                    scope.launch {
                        activity.dataStore.edit { preferences ->
                            preferences[COUNTER_KEY] = (preferences[COUNTER_KEY] ?: 0) + 1
                        }
                    }
                },
                modifier = Modifier.size(200.dp, 80.dp)
            ) {
                Text(
                    text = if (counter == 0) "Click Me!" else "Click Again!",
                    fontSize = 20.sp
                )
            }
        }
    }
}
