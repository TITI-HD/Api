package com.example.ui.screens.mocking

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.ui.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MockingScreen(viewModel: MainViewModel) {
    Column(modifier = Modifier.fillMaxSize()) {
        TopAppBar(title = { Text("Serveurs Mocks") })
        
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(16.dp)) {
                Text(
                    "Démarrez un serveur local pour fournir de fausses données aux développeurs Front-end.",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurface,
                    textAlign = androidx.compose.ui.text.style.TextAlign.Center
                )
                Spacer(modifier = Modifier.height(24.dp))
                Button(onClick = { /* TODO */ }) {
                    Text("Nouveau Serveur Local")
                }
            }
        }
    }
}
