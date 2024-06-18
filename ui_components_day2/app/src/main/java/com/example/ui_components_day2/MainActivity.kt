@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.ui_components_day2

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContent {
            ButtonLayout()
        }
    }
}

@Composable
fun ButtonLayout() {
    val context = LocalContext.current
    var isDialog by remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = {
                    isDialog = true
                    Toast.makeText(context, "Button", Toast.LENGTH_SHORT).show()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp),
                shape = RoundedCornerShape(10.dp),
                border = BorderStroke(5.dp, Color.Black),
                colors = ButtonDefaults.buttonColors(
                    contentColor = Color.Red,
                    containerColor = Color.Yellow
                ),
                contentPadding = PaddingValues(
                    vertical = 15.dp
                )
            ) {
                Text(
                    text = "Simple Button",
                    style = TextStyle(
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold,
                    )
                )
            }
            OutlinedButton(
                onClick = {},
                modifier = Modifier.padding(15.dp)
            ) {
                Text(
                    text = "Outlined Button",
                    style = TextStyle(
                        color = Color.Red,
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
            }
            TextButton(
                onClick = {},
                modifier = Modifier.padding(15.dp)
            ) {
                Text(
                    text = "Text Button",
                    style = TextStyle(
                        color = Color.Red,
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
            }
        }

        if (isDialog) {
            Dialog(onDismissRequest = {
                isDialog = false
            }) {
                Box(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }

            AlertDialog(
                onDismissRequest = {
                    isDialog = false
                },
                title = {
                    Text(text = "Dialog Box")
                },
                text = {
                    Text(text = "This is a dialog box")
                },
                confirmButton = {
                    Button(onClick = {
                        isDialog = false
                    }) {
                        Text("OK")
                    }
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ButtonLayout()
}
