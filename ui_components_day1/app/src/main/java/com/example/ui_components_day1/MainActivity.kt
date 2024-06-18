package com.example.ui_components_day1



import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TextShadow()

        }
    }
}

@Composable
fun TextShadow() {
    val offset = Offset(5.0f, 10.0f)

    Text(
        text = buildAnnotatedString {
            withStyle(style =SpanStyle(color=Color.Black)) {
                append("H")
            }
            append("ello ")

            withStyle(style = SpanStyle(color=Color.Black)) {
                append("W")
            }
            append("orld!!! ")
            withStyle(style = SpanStyle(color=Color.Black)) {
                append("G")
            }
            append("ood ")
            withStyle(style = SpanStyle(color=Color.Black)) {
                append("M")
            }
            append("orning...")


        },
        style = TextStyle(
            fontSize = 60.sp,
            color = Color.Red,
            fontWeight = FontWeight.Bold,
            shadow = Shadow(
                color = Color.Blue,
                offset = offset,
                blurRadius = 6f,
            )

        )
    )
}