package com.example.chips

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.chips.ui.theme.ChipsTheme
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.InputChip
import androidx.compose.material3.InputChipDefaults
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SuggestionChip
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight.Companion.SemiBold
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ChipsTheme {
                Surface(
                    color = MaterialTheme.colorScheme.background
                ) {
                    FilterChipExample()
                }
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun FilterChipExample() {
        var selected by remember { mutableStateOf(false) }
        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 100.dp),


            //verticalArrangement = Arrangement.Center,
           horizontalAlignment = Alignment.CenterHorizontally
        ){
            FilterChip(
                onClick = { selected = !selected },
                label = {
                    Text("Filter chip",
                        style = TextStyle(
                            color = Color.Black,
                            fontSize = 24.sp,
                            fontStyle = FontStyle.Normal
                        )
                    )
                },
                selected = selected,
                leadingIcon = if (selected) {
                    {
                        Icon(
                            imageVector = Icons.Filled.Done,
                            contentDescription = "Done icon",
                            modifier = Modifier.size(FilterChipDefaults.IconSize)
                        )
                    }
                } else {
                    null
                },
            )
            AssistChipExample()
            ExampleInputChip(text =" hello world") {

            }
            SuggestionChipExample()

        }

    }



    @Composable
    fun AssistChipExample()
    {
        AssistChip(
            modifier = Modifier
                .padding(top = 100.dp),
            onClick = { Log.d("Assist chip", "hello world") },
            label = { Text("Assist chip",
                style = TextStyle(
                    color = Color.Black,
                    fontSize = 24.sp,
                    fontStyle = FontStyle.Normal
                ) ) },
            leadingIcon = {
                Icon(
                    Icons.Filled.Settings,
                    contentDescription = "Localized description",
                    Modifier.size(AssistChipDefaults.IconSize)
                )
            }
        )
    }
    @Composable
    fun ExampleInputChip(
        text: String,
        onDismiss: () -> Unit,
    ) {
        var enabled by remember { mutableStateOf(true) }
        if (!enabled) return

        InputChip(
            modifier = Modifier
                .padding(top = 100.dp),
            onClick = {
                onDismiss()
                enabled = !enabled
            },
            label = { Text(text="Input chip",
                style = TextStyle(
                    color = Color.Black,
                    fontSize = 24.sp,
                    fontStyle = FontStyle.Normal)) },
            selected = enabled,
            avatar = {
                Icon(
                    Icons.Filled.Person,
                    contentDescription = "Localized description",
                    Modifier.size(InputChipDefaults.AvatarSize)
                )
            },
            trailingIcon = {
                Icon(
                    Icons.Default.Close,
                    contentDescription = "Localized description",
                    Modifier.size(InputChipDefaults.AvatarSize)
                )
            },
        )
    }
    @Composable
    fun SuggestionChipExample()
    {
        SuggestionChip(
            modifier = Modifier
                .padding(top = 100.dp),
            onClick = { Log.d("Suggestion chip", "hello world") },
            label = { Text("Suggestion chip",
                style = TextStyle(
                    color = Color.Black,
                    fontSize = 24.sp,
                    fontStyle = FontStyle.Normal)) }
        )
    }


}
