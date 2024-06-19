package com.example.ui_components_day4

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.ui_components_day4.ui.theme.Ui_components_day4Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Ui_components_day4Theme {
                ProgressIndicatorExample()
            }
        }
    }
}

@Composable
fun ProgressIndicatorExample() {
    var isLoading by remember { mutableStateOf(false) }
    var progress by remember { mutableStateOf(0.1f) }
    val animatedProgress by animateFloatAsState(
        targetValue = progress,
        animationSpec = ProgressIndicatorDefaults.ProgressAnimationSpec
    )

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        if (isLoading) {
            CircularProgressIndicator(progress = animatedProgress)
        }

        Button(onClick = { isLoading = !isLoading }) {
            Text(text = if (isLoading) "Stop Loading" else "Start Loading")
        }

        // Button to increase progress
        Button(onClick = {
            if (progress < 1f) {
                progress += 0.1f
            }
        }) {
            Text(text = "Increase Progress")
        }

        // Linear Progress Indicator
        Linearexample()
    }
}

@Composable
fun Linearexample() {
    var isLoading by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        if (isLoading) {
            LinearProgressIndicator()
        }

        Button(onClick = { isLoading = !isLoading }) {
            Text(text = if (isLoading) "Stop Loading" else "Start Loading Linear Progress")
        }
    }
}
