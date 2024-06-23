package com.example.user_fetch_api

import android.os.Bundle
import android.text.Layout
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.graphics.Color.Companion.Cyan
import androidx.compose.ui.graphics.Color.Companion.DarkGray
import androidx.compose.ui.graphics.Color.Companion.Green
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.graphics.Color.Companion.Magenta
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.compose.rememberImagePainter
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


class MainActivity : ComponentActivity() {
    private val userVM: UserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val users by userVM.users.observeAsState(emptyList())
            Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                Column {
                    Header()
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        contentPadding = PaddingValues(10.dp),
                        modifier = Modifier.fillMaxSize()) {
                        items(users.size) { index ->
                            UserItem(user = users[index])
                        }
                    }
                }
            }
        }
    }

    @Composable
    fun Header() {
        Text(
            text = "Users List",
            style = TextStyle(
                fontSize = 30.sp,
                fontWeight = FontWeight.Normal,
                fontFamily = FontFamily.SansSerif,
                textAlign = TextAlign.Center,
                color = Color.Blue,
            ),
            modifier = Modifier
                .background(Color.LightGray)
                .fillMaxWidth()
                .height(50.dp)
                .padding(top = 10.dp)
        )
    }
}

@Composable
fun UserItem(user: User) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        Column(
            modifier = Modifier.padding(10.dp),
        ) {
            Row {
                HeadingText(text = "Name:")
                ValueText(text = "${user.name.firstname} ${user.name.lastname}")
            }
            Spacer(modifier = Modifier.height(8.dp))
            HeadingText(text = "Username:")
            ValueText(text = user.username)
            Spacer(modifier = Modifier.height(8.dp))
            HeadingText(text = "Email:")
            ValueText(text = user.email)
            Spacer(modifier = Modifier.height(8.dp))
            HeadingText(text = "Password:")
            VisualTransformedPassword(password = user.password)
        }
    }
}

@Composable
fun HeadingText(text: String) {
    Text(
        text = text,
        color = Color.Blue,
    )
}

@Composable
fun ValueText(text: String) {
    Text(
        text = text,
        color = Color.Black,
        modifier = Modifier.padding(start = 8.dp)
    )
}

@Composable
fun VisualTransformedPassword(password: String) {
    Text(
        text = buildAnnotatedString {
            password.forEach {
                append('●')
            }
        },
        color = Color.Black
    )
}

// Model Class
data class User(
    val id: Int,
    val email: String,
    val username: String,
    val password: String,
    val category: String,
    val name: Name
)

data class Name(
    val firstname: String,
    val lastname: String
)

interface ApiService {
    @GET("users")
    suspend fun getUser(): List<User>
}

object RetrofitClient {
    private const val BASE_URL = "https://fakestoreapi.com/"

    val apiService: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}

class UserRepository(private val apiService: ApiService) {
    suspend fun getUser(): List<User> {
        return apiService.getUser()
    }
}

class UserViewModel : ViewModel() {
    private val _users = MutableLiveData<List<User>>()
    val users: LiveData<List<User>> get() = _users

    private val repository = UserRepository(RetrofitClient.apiService)

    init {
        fetchUsers()
    }

    fun fetchUsers() {
        viewModelScope.launch {
            try {
                val productList = repository.getUser()
                _users.postValue(productList)
                println(productList)
            } catch (e: Exception) {
                // Handle the exception
            }
        }
    }
}
