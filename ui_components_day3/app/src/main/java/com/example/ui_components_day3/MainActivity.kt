package com.example.ui_components_day3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                ScaffoldExample()
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun ScaffoldExample() {
        var presses by remember { mutableStateOf(0) }

        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = "Top app bar",
                            color = Color.White,
                            textAlign = TextAlign.Justify
                        )
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color.Blue,
                        titleContentColor = Color.White,
                        actionIconContentColor = Color.Black,
                        navigationIconContentColor = Color.Black
                    ),
                    navigationIcon = {
                        Icon(
                            imageVector = Icons.Default.Menu,
                            contentDescription = "Menu",
                            tint = Color.Black
                        )
                    },
                    actions = {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = "Add",
                            tint = Color.Black
                        )
                        Icon(
                            imageVector = Icons.Default.MoreVert,
                            contentDescription = "More options",
                            tint = Color.Black
                        )
                    }
                )
            },
            bottomBar = {
                BottomAppBar(
                    containerColor = Color.LightGray,
                    contentColor = Color.Red
                ) {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                        textAlign = TextAlign.Center,
                        text = "Bottom app bar",
                    )
                    ShowSwitch()
                }
            },
            floatingActionButton = {
                FloatingActionButton(onClick = { presses++ }) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = "Increment")
                }
            }
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                Text(
                    text = "This is an example of a scaffold. It uses the Scaffold composable's parameters to create a screen with a simple top app bar, bottom app bar, and floating action button.\n" +
                            "It also contains some basic inner content, such as this text.\n" +
                            "You have pressed the floating action button $presses times.",
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.Black,
                    modifier = Modifier.padding(16.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))

                ShowSwitch()
                SliderExample()
                BottomSheetExample()
            }
        }
    }

    @Composable
    fun ShowSwitch() {
        val isChecked = remember { mutableStateOf(true) }
        Switch(
            checked = isChecked.value,
            onCheckedChange = {
                isChecked.value = it
            },
            modifier = Modifier
                .padding(start = 16.dp)
        )
    }

    @Composable
    fun SliderExample() {
        var sliderPosition by remember { mutableStateOf(0f) }
        Column {
            Slider(
                value = sliderPosition,
                onValueChange = {
                    sliderPosition = it
                },
                colors = SliderDefaults.colors(
                    thumbColor = Color.Red,
                    activeTrackColor = Color.Red,
                    inactiveTrackColor = Color.Black
                ),
                valueRange = 0f..5f,
                steps = 4
            )
            Text(
                text = sliderPosition.toString(),
                modifier = Modifier.padding(start = 5.dp)
            )
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun BottomSheetExample() {
        var openBottomSheet by remember { mutableStateOf(false) }
        val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = false)

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Button(onClick = { openBottomSheet = true }) {
                Text(text = "Show Bottom Sheet")
            }
        }

        if (openBottomSheet) {
            ModalBottomSheet(
                onDismissRequest = { openBottomSheet = false },
                sheetState = sheetState
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxHeight(0.75f)
                        .padding(16.dp)
                ) {
                    LazyColumn(
                        modifier = Modifier.weight(1f)
                    ) {
                        items(50) { index ->
                            Text(
                                text = "Swipe up to scroll. Swipe down to dismiss.",
                                modifier = Modifier.padding(vertical = 8.dp)
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(
                        onClick = { openBottomSheet = false },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(text = "Cancel")
                    }
                }
            }
        }
    }
}
