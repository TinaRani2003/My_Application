package com.example.ui_screenshot

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui_screenshot.ui.theme.Ui_screenshotTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Ui_screenshotTheme {
                UIReplica()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UIReplica(){

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
       // verticalArrangement = Arrangement.Center,
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),

            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xfff0f5fb) // Light gray background
            )
        ) {
            Column(
                modifier = Modifier.padding(18.dp)
                    .padding(bottom = 20.dp),


                horizontalAlignment = CenterHorizontally
            ) {
                Text(
                    text = "Jetpack Compose",
                    style = TextStyle(
                        fontSize = 39.sp,
                        fontWeight = FontWeight.Normal,
                        fontFamily = FontFamily.SansSerif,
                        color = Color(0xff00639a) // Blue color
                    ),
                    modifier = Modifier.padding(bottom = 30.dp)
                )
                Image(
                    painter = painterResource(id = R.drawable.jetpack_compose_image), // Replace with actual drawable resource
                    contentDescription = null,
                    modifier = Modifier.size(90.dp),
                )
                Spacer(modifier = Modifier.height(30.dp))
                Text(
                    text = "Login",
                    style = TextStyle(
                        fontSize = 45.sp,
                        fontWeight = FontWeight.Normal,
                        fontFamily = FontFamily.SansSerif,
                        color =Color(0xFF3CB371)

                    ),
                    modifier = Modifier
                        .padding(bottom = 30.dp)
                        .align(Alignment.Start)
                )
                OutlinedTextField(
                    value = "",
                    onValueChange = {},
                    placeholder = { Text(text = "Email ID or Mobile Number") },
                    modifier = Modifier
                        .padding(bottom = 30.dp)

                        .fillMaxWidth(),

                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color.Black
                    )
                )


                OutlinedTextField(
                    value = "",
                    onValueChange = {},
                    placeholder = { Text(text = "Password") },

                    modifier = Modifier
                        .padding(bottom = 8.dp)
                        .fillMaxWidth(),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color.Black
                    )
                )
                Text(
                    text = "Forgot Password?",
                    modifier = Modifier
                        .align(Alignment.End)
                        .padding(bottom = 25.dp),
                    style = TextStyle(
                        fontSize = 15.sp,
                        fontFamily = FontFamily.SansSerif,
                        color = Color(0xFF3CB371)
                    )
                )
                Button(
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .height(50.dp)
                        .align(Alignment.Start),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Blue)
                ) {
                    Text(text = "Login", fontSize =24.sp, color = Color.White)
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier.padding(start = 70.dp)
        ) {
            Text("Don't have an account?",
                style = TextStyle(fontFamily = FontFamily.SansSerif,
                    fontSize = 15.sp))
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = "Register",
                style = TextStyle(
                    color = Color(0xff00639a), // Blue color
                    fontWeight = FontWeight.Normal,
                    fontFamily = FontFamily.SansSerif,
                    fontSize = 15.sp

                ),


            )
        }
    }
}

@Preview
@Composable
fun DefaultPreview() {
    Ui_screenshotTheme {
        UIReplica()
    }
}