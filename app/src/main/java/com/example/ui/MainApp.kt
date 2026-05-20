package com.example.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.ui.screens.flows.FlowsScreen
import com.example.ui.screens.mocking.MockingScreen
import com.example.ui.screens.premium.PremiumScreen
import com.example.ui.screens.tester.TesterScreen

sealed class Screen(val route: String, val title: String, val icon: ImageVector) {
    object Tester : Screen("tester", "Testeur", Icons.Default.PlayArrow)
    object Flows : Screen("flows", "Flows", Icons.Default.List)
    object Mocking : Screen("mocking", "Mocks", Icons.Default.Settings)
    object Premium : Screen("premium", "Marketplace", Icons.Default.ShoppingCart)
}

val items = listOf(Screen.Tester, Screen.Flows, Screen.Mocking, Screen.Premium)

@Composable
fun MainApp(viewModel: MainViewModel) {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            NavigationBar {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination

                items.forEach { screen ->
                    NavigationBarItem(
                        icon = { Icon(screen.icon, contentDescription = screen.title) },
                        label = { Text(screen.title) },
                        selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                        onClick = {
                            navController.navigate(screen.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Tester.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Tester.route) {
                TesterScreen(viewModel)
            }
            composable(Screen.Flows.route) {
                FlowsScreen(viewModel)
            }
            composable(Screen.Mocking.route) {
                MockingScreen(viewModel)
            }
            composable(Screen.Premium.route) {
                PremiumScreen(viewModel)
            }
        }
    }
}
