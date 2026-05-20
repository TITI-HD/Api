package com.example.ui.screens.tester

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.ui.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TesterScreen(viewModel: MainViewModel) {
    var url by remember { mutableStateOf("jsonplaceholder.typicode.com/posts/1") }
    var method by remember { mutableStateOf("GET") }
    var body by remember { mutableStateOf("") }
    
    val response by viewModel.requestResponse.collectAsState()
    val isRequesting by viewModel.isRequesting.collectAsState()

    var expandedMethod by remember { mutableStateOf(false) }
    val methods = listOf("GET", "POST", "PUT", "DELETE", "PATCH")

    Column(modifier = Modifier.fillMaxSize()) {
        TopAppBar(title = { Text("Requête Rapide") })
        
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)) {
            
            Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                Box {
                    Button(onClick = { expandedMethod = true }) {
                        Text(method)
                    }
                    DropdownMenu(expanded = expandedMethod, onDismissRequest = { expandedMethod = false }) {
                        methods.forEach { m ->
                            DropdownMenuItem(
                                text = { Text(m) },
                                onClick = {
                                    method = m
                                    expandedMethod = false
                                }
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.width(8.dp))
                OutlinedTextField(
                    value = url,
                    onValueChange = { url = it },
                    label = { Text("URL") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Uri),
                    modifier = Modifier.weight(1f)
                )
            }
            
            if (method == "POST" || method == "PUT" || method == "PATCH") {
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = body,
                    onValueChange = { body = it },
                    label = { Text("Body (JSON)") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp),
                    textStyle = LocalTextStyle.current.copy(fontFamily = FontFamily.Monospace)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))
            
            Button(
                onClick = { viewModel.sendRequest(url, method, body) },
                modifier = Modifier.fillMaxWidth(),
                enabled = !isRequesting
            ) {
                if (isRequesting) {
                    CircularProgressIndicator(modifier = Modifier.size(24.dp), color = MaterialTheme.colorScheme.onPrimary)
                } else {
                    Icon(Icons.Filled.Send, contentDescription = "Envoyer")
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Envoyer la requête")
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
            
            Text("Réponse", style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(8.dp))
            
            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f),
                color = MaterialTheme.colorScheme.surfaceVariant,
                shape = MaterialTheme.shapes.medium
            ) {
                LazyColumn(modifier = Modifier.fillMaxSize().padding(8.dp)) {
                    item {
                        Text(
                            text = response ?: "Aucune réponse pour le moment...",
                            fontFamily = FontFamily.Monospace,
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }
        }
    }
}
