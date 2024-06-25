package com.example.firebase_1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.firebase_1.ui.theme.Firebase1Theme
import com.google.firebase.auth.FirebaseAuth

sealed class NavRoutes(val route: String) {
    object SignUp : NavRoutes("signup")
    object SignIn : NavRoutes("signin")
    object Home : NavRoutes("home")
}

class MainActivity : ComponentActivity() {
    private val auth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Firebase1Theme {
                val navController = rememberNavController()
                NavHost(navController, startDestination = NavRoutes.SignUp.route) {
                    composable(NavRoutes.SignUp.route) { SignUpScreen(navController) }
                    composable(NavRoutes.SignIn.route) { SignInScreen(navController) }
                    composable(NavRoutes.Home.route) { HomeScreen() }
                }
            }
        }
    }

    fun signUp(email: String, password: String, navController: NavHostController) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    println("User created: $user")
                    navController.navigate(NavRoutes.SignIn.route)
                } else {
                    println("User couldn't be created")
                    println(task.exception?.message)
                }
            }
    }

    fun signIn(email: String, password: String, navController: NavHostController) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    println("User logged in: $user")
                    navController.navigate(NavRoutes.Home.route)
                } else {
                    println("User couldn't log in")
                    println("User couldn't log in ${task.exception?.message}")
                }
            }
    }

    @Composable
    fun SignUpScreen(navController: NavHostController) {
        val email = remember { mutableStateOf("") }
        val password = remember { mutableStateOf("") }

        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "SIGN UP PAGE", style = TextStyle(
                fontSize = 30.sp,
                fontWeight = FontWeight.Normal,
                color = Color.Red,
                textAlign = TextAlign.Center
                
            ))
            Spacer(modifier = Modifier.height(20.dp))
            TextField(
                value = email.value,
                onValueChange = { email.value = it },
                label = { Text(text = "Email") },
                modifier = Modifier
                    .fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(20.dp))
            TextField(
                value = password.value,
                onValueChange = { password.value = it },
                label = { Text(text = "Password") },
                modifier = Modifier
                    .fillMaxWidth(),
                visualTransformation = PasswordVisualTransformation()
            )
            Spacer(modifier = Modifier.height(20.dp))
            Row(
                horizontalArrangement = Arrangement.spacedBy(130.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(onClick = { signUp(email.value, password.value, navController) }) {
                    Text(text = "Sign Up", )
                }
                Button(onClick = { navController.navigate(NavRoutes.SignIn.route) }) {
                    Text(text = "Go to Sign In")
                }
            }

        }
    }

    @Composable
    fun SignInScreen(navController: NavHostController) {
        val email = remember { mutableStateOf("") }
        val password = remember { mutableStateOf("") }

        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "SIGN IN PAGE", style = TextStyle(
                fontSize = 30.sp,
                fontWeight = FontWeight.Normal,
                color = Color.Red,
                textAlign = TextAlign.Center

            ))
            Spacer(modifier = Modifier.height(20.dp))
            
            TextField(
                value = email.value,
                onValueChange = { email.value = it },
                label = { Text(text = "Email") },
                modifier = Modifier
                    .fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(20.dp))
            TextField(
                value = password.value,
                onValueChange = { password.value = it },
                label = { Text(text = "Password") },
                modifier = Modifier
                    .fillMaxWidth(),
                visualTransformation = PasswordVisualTransformation()
            )
            Spacer(modifier = Modifier.height(20.dp))
            Button(onClick = { signIn(email.value, password.value, navController) }) {
                Text(text = "Sign In")
            }
        }
    }

    @Composable
    fun HomeScreen() {
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "Welcome to the Home Page!", color = Color.Black, fontSize = 20.sp)
        }
    }
}
