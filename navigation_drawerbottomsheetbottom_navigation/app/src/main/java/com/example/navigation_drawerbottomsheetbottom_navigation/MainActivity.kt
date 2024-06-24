package com.example.navigation_drawerbottomsheetbottom_navigation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.content.pm.ShortcutInfoCompat
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TopAppBarWithDrawerExample()
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun TopAppBarWithDrawerExample() {
        val drawerState = rememberDrawerState(DrawerValue.Closed)
        val scope = rememberCoroutineScope()
        val navController = rememberNavController()

        ModalNavigationDrawer(
            drawerState = drawerState,
            drawerContent = {
                Surface(
                    color = Color.Cyan,
                    shape  = MaterialTheme.shapes.medium,
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(200.dp)
                        // Adjust width as needed
                ) {
                    DrawerContent(navController) {
                        scope.launch { drawerState.close() }
                    }
                }
            }
        ){
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = { Text(text = "Top App Bar", color = Color.Blue) },
                        navigationIcon = {
                            IconButton(onClick = {
                                scope.launch { drawerState.open() }
                            }) {
                                Icon(imageVector = Icons.Filled.Menu, contentDescription = "Menu")
                            }
                        },
                        actions = {
                            IconButton(onClick = { /*TODO*/ }) {
                                Icon(imageVector = Icons.Filled.Favorite, contentDescription = "Favorite")
                            }
                            IconButton(onClick = { /*TODO*/ }) {
                                Icon(imageVector = Icons.Filled.Settings, contentDescription = "Settings")
                            }
                        },
                        colors = TopAppBarDefaults.topAppBarColors(
                            containerColor = Color.LightGray
                        )
                    )
                },
                bottomBar = {
                    BottomAppBar(
                        actions = {
                            IconButton(onClick = { navController.navigate("home") }) {
                                Icon(imageVector = Icons.Filled.Home, contentDescription = "Home", modifier = Modifier.size(30.dp))


                            }
                            Spacer(modifier = Modifier.width(60.dp))
                            IconButton(onClick = { navController.navigate("share") }) {
                                Icon(imageVector = Icons.Filled.Share, contentDescription = "Share", modifier = Modifier.size(30.dp))

                            }
                            Spacer(modifier = Modifier.width(65.dp))
                            IconButton(onClick = { navController.navigate("search") }) {
                                Icon(imageVector = Icons.Filled.Search, contentDescription = "Search", modifier = Modifier.size(30.dp))

                            }
                            Spacer(modifier = Modifier.width(65.dp))
                            IconButton(onClick = { navController.navigate("notifications") }) {
                                Icon(imageVector = Icons.Filled.Notifications, contentDescription = "Notifications", modifier = Modifier.size(30.dp))

                            }
                        }
                    )
                },
                floatingActionButton = {
                    FloatingActionButton(onClick = { navController.navigate("add") }) {
                        Icon(imageVector = Icons.Filled.Add, contentDescription = "Add", modifier = Modifier.size(30.dp))
                    }
                }
            ) { innerPadding ->
                Box(modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()) {
                    NavHost(navController = navController, startDestination = "home") {
                        composable("home") { HomeScreen() }
                        composable("profile") { ProfileScreen() }
                        composable("logout") { LogoutScreen() }
                        composable("share") { ShareScreen() }
                        composable("search") { SearchScreen() }
                        composable("notifications") { NotificationsScreen() }
                        composable("add") { AddScreen() }
                    }
                }
            }
        }
    }

    @Composable
    fun DrawerContent(navController: NavHostController, onCloseDrawer: () -> Unit) {
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)) {
            TextButton(onClick = {
                navController.navigate("home")
                onCloseDrawer()
            }) {
                Icon(imageVector = Icons.Filled.Home, contentDescription = "Home", tint=Color.Black)
                Spacer(modifier = Modifier.width(16.dp))
                Text(text = "Home", color = Color.Black)
            }
            Spacer(modifier = Modifier.height(8.dp))
            TextButton(onClick = {
                navController.navigate("profile")
                onCloseDrawer()
            }) {
                Icon(imageVector = Icons.Filled.Person, contentDescription = "Profile", tint= Color.Black)
                Spacer(modifier = Modifier.width(16.dp))
                Text(text = "Profile", color = Color.Black)
            }
            Spacer(modifier = Modifier.height(8.dp))
            TextButton(onClick = {
                navController.navigate("logout")
                onCloseDrawer()
            }) {
                Icon(imageVector = Icons.Filled.ExitToApp, contentDescription = "Log Out", tint= Color.Black)
                Spacer(modifier = Modifier.width(16.dp))
                Text(text = "Log Out", color = Color.Black)
            }
        }
    }

    @Composable
    fun HomeScreen() {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(text = "Home Screen")
        }
    }

    @Composable
    fun ProfileScreen() {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(text = "Profile Screen")
        }
    }

    @Composable
    fun LogoutScreen() {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(text = "Log Out Screen")
        }
    }

    @Composable
    fun ShareScreen() {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(text = "Share Screen")
        }
    }

    @Composable
    fun SearchScreen() {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(text = "Search Screen")
        }
    }

    @Composable
    fun NotificationsScreen() {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(text = "Notifications Screen")
        }
    }

    @Composable
    fun AddScreen() {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(text = "Add Screen")
        }
    }
}
