package com.example.navigation_bar

import android.os.Bundle
import android.window.SplashScreen
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode.Companion.Screen
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.example.navigation_bar.ui.theme.Navigation_barTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // enableEdgeToEdge()
        setContent {
             NestedNavigation()
        }
    }
    /*@Composable
    fun MainNavigation()
    {
        val navHostController= rememberNavController()
        NavHost(navController =navHostController ,
            startDestination = Screen.First.route){
            composable(Screen.First.route){
                FirstScreen(navHostController)
            }
            composable(Screen.Second.route){
                SecondScreen(navHostController)
            }
        }
    }*/
    @Composable
    fun NestedNavigation(){
        val navHostController= rememberNavController()
        NavHost(navController =navHostController ,
            startDestination = NestedScreen.Spalsh.route){
            composable(NestedScreen.Spalsh.route){
                SplashScreen(navHostController)
            }
            navigation(
                route=NestedScreen.Register.route,
                startDestination = NestedScreen.Register.SignUp.route
            ){
                composable(NestedScreen.Register.SignUp.route){
                    SignUp(navHostController)
                }
                composable(NestedScreen.Register.SignIn.route){
                   SignIn(navHostController)
                }

            }
            composable(NestedScreen.Profile.route){
                Profile(navHostController)
            }


        }

    }




    sealed class NestedScreen(val route:String){
        object Spalsh:NestedScreen("splash")
        object Register:NestedScreen("register"){
            object SignUp:NestedScreen
                ("signup")
            object SignIn:NestedScreen("signin")
        }
        object Profile:NestedScreen("profile")
    }

    /*@Composable
   fun FirstScreen(navHostController: NavHostController){
        Box(modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center){
            Button(onClick = { navHostController.currentBackStackEntry?.savedStateHandle?.set("name", "Tina")
                navHostController.navigate(Screen.Second.route)}) {
                Text(text = "Send data to first screen")
                
            }
        }
    }
    @Composable
    fun SecondScreen(navHostController: NavHostController){
        val name=navHostController.previousBackStackEntry?.savedStateHandle?.get<String>("name")?:""
        Box(modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center) {
            Button(onClick = {navHostController.navigateUp()

            }) {
                Text(text = "My name is $name")

            }
        }
    }*/
    @Composable
    fun SplashScreen(navHostController: NavHostController){
        Box(modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center){
            Button(onClick = { navHostController.navigate(NestedScreen.Register.SignUp.route) {
                    popUpTo(NestedScreen.Spalsh.route) { inclusive = true }
                }
            }) {

                Text(text = "This is Splash Screen")

            }
        }

    }
    @Composable
    fun SignUp(navHostController: NavHostController){
        Box(modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center){
            Button(onClick = { navHostController.navigate(NestedScreen.Register.SignIn.route) {
                popUpTo(NestedScreen.Register.SignUp.route) { inclusive = true }
            }
            }) {

                Text(text = "This is SignUp Screen")

            }
        }

    }
    @Composable
    fun SignIn(navHostController: NavHostController){
        Box(modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center){
            Button(onClick = { navHostController.navigate(NestedScreen.Profile.route) {
                popUpTo(NestedScreen.Register.SignIn.route) { inclusive = true }
            }
            }) {

                Text(text = "This is SignIn Screen")

            }
        }

    }
    @Composable
    fun Profile(navHostController: NavHostController){
        Box(modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center){
            Button(onClick = { navHostController.navigateUp() }) {

                Text(text = "This is Profile Screen")

            }
        }

    }
}
