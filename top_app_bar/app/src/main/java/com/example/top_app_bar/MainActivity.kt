package com.example.top_app_bar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.top_app_bar.ui.theme.Top_app_barTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContent {
            TopAppBarExample()
        }
    }
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun TopAppBarExample(){
        Scaffold (
            topBar = {
                TopAppBar(title = { Text(text = " Top App Bar", color= Color.Blue) },
                    navigationIcon = { IconButton(onClick = { /*TODO*/ }){
                    Icon(imageVector = Icons.Filled.Menu, contentDescription = " ")}
                    },
                    actions = {
                        IconButton(onClick = { /*TODO*/ }) {
                        Icon(imageVector = Icons.Filled.Favorite, contentDescription = " ")}
                        IconButton(onClick = { /*TODO*/ }){
                            Icon(imageVector = Icons.Filled.Settings, contentDescription = "")}

                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color.LightGray
                    )
                    
                )
            },
            bottomBar = {
                BottomAppBar(
                    actions = {
                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(imageVector = Icons.Filled.Home, contentDescription = " ", modifier=Modifier.size(30.dp))
                        }
                        Spacer(modifier = Modifier.width(60.dp))
                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(imageVector = Icons.Filled.Share, contentDescription = " ", modifier = Modifier.size(30.dp))
                        }
                        Spacer(modifier = Modifier.width(65.dp))
                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(imageVector = Icons.Filled.Search, contentDescription = " ", modifier = Modifier.size(30.dp))
                        }
                        Spacer(modifier = Modifier.width(65.dp))
                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(imageVector = Icons.Filled.Notifications, contentDescription = " ", modifier = Modifier.size(30.dp))
                        }

                    }
                )
            },
            floatingActionButton = {
                FloatingActionButton(onClick = { /*TODO*/ }){
                    Icon(imageVector = Icons.Filled.Add, contentDescription =" ", modifier = Modifier.size(30.dp) )
                }

            }


        ){
                innerPadding ->
            // Content to display in the Scaffold
            Text(
                text = " ",
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
            )
        }
        }

    }

