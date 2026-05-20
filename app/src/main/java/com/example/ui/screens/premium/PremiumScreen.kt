package com.example.ui.screens.premium

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CloudDone
import androidx.compose.material.icons.filled.Code
import androidx.compose.material.icons.filled.Store
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.ui.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PremiumScreen(viewModel: MainViewModel) {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("ApiFlow Premium", style = MaterialTheme.typography.headlineLarge, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
        Spacer(modifier = Modifier.height(8.dp))
        Text("Passez à la vitesse supérieure !", style = MaterialTheme.typography.bodyLarge)
        
        Spacer(modifier = Modifier.height(24.dp))

        PremiumCard(
            title = "Collaboration Cloud",
            desc = "Synchronisez vos scénarios de test et collaborez en temps réel avec votre équipe.",
            icon = Icons.Filled.CloudDone
        )
        
        PremiumCard(
            title = "Marketplace Templates",
            desc = "Achetez des flux pré-configurés : OAuth2, Stripe, Gestion doc, etc.",
            icon = Icons.Filled.Store
        )

        PremiumCard(
            title = "Export de Code",
            desc = "Générez du code Node.js, PHP, ou Python correspondant à votre architecture.",
            icon = Icons.Filled.Code
        )

        Spacer(modifier = Modifier.weight(1f))
        
        Button(
            onClick = { /* TODO */ },
            modifier = Modifier.fillMaxWidth().height(56.dp)
        ) {
            Text("Voir les abonnements", style = MaterialTheme.typography.titleMedium)
        }
    }
}

@Composable
fun PremiumCard(title: String, desc: String, icon: ImageVector) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
    ) {
        Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
            Icon(icon, contentDescription = title, modifier = Modifier.size(40.dp), tint = MaterialTheme.colorScheme.onPrimaryContainer)
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(title, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                Text(desc, style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}
