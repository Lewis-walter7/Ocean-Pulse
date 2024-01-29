package com.licoding.oceanpulse

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.compose.OceanPulseTheme
import com.google.firebase.auth.FirebaseAuth
import com.licoding.oceanpulse.data.models.BottomNavigationItem
import com.licoding.oceanpulse.presentation.Main.MainViewmodel
import com.licoding.oceanpulse.presentation.Main.blog.Blog
import com.licoding.oceanpulse.presentation.Main.components.*

@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {
    private val auth = FirebaseAuth.getInstance()
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        println(auth.currentUser)
        val viewModel by viewModels<MainViewmodel>(
            factoryProducer = {
                object : ViewModelProvider.Factory{
                    override fun <T : ViewModel> create(modelClass: Class<T>): T {
                        return MainViewmodel(application) as T
                    }
                }
            }
        )
        setContent {
            var selectedIndex by remember {
                mutableIntStateOf(0)
            }
            val items = listOf(
                BottomNavigationItem(
                    route = "home",
                    icon = Icons.Default.Home,
                    title = "Home"
                ),
                BottomNavigationItem(
                    route = "blog",
                    icon = Icons.Default.Newspaper,
                    title = "Blogs"
                ),
                BottomNavigationItem(
                    icon = Icons.Default.AddBox,
                    title = "Post"
                ),
                BottomNavigationItem(
                    route = "quiz",
                    icon = Icons.Default.Quiz,
                    title = "Quiz"
                ),
                BottomNavigationItem(
                    route = "profile",
                    icon = Icons.Default.SupervisedUserCircle,
                    title = "Profile"
                )
            )
            val navigate = {
                startActivity(Intent(this@MainActivity, RegisterActivity::class.java))
                finish()
            }
            val intent = Intent(this@MainActivity, BlogActivity::class.java)
            fun startBlogActivity(url: String) {
                intent.putExtra("url", url)
                startActivity(intent)
            }
            val state by viewModel.state.collectAsState()
            OceanPulseTheme {
                val navController = rememberNavController()
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    Scaffold(
                        bottomBar = {
                            BottomAppBar{
                                items.forEachIndexed { index, item ->
                                    NavigationBarItem(
                                        icon = {
                                             Icon(
                                                 imageVector = item.icon,
                                                 contentDescription = null
                                             )
                                        },
                                        onClick = {
                                            selectedIndex = index
                                            if (item.route != null) {
                                                navController.navigate(item.route)
                                            }else {
                                                startActivity(Intent(this@MainActivity, UploadActivity::class.java))
                                            }
                                        },
                                        label = {
                                            Text(
                                                text = item.title
                                            )
                                        },
                                        selected = selectedIndex == index
                                    )
                                }
                            }
                        }
                    ) {
                        NavHost(navController = navController, startDestination = "home") {
                            composable("home") {
                                Home(
                                    context = application
                                )
                            }
                            composable("profile") {
                                Profile(
                                    navigate = { navigate() },
                                    state = state,
                                    onEvent = viewModel::onEvent
                                )
                            }
                            composable("quiz"){
                                Quiz()
                            }
                            composable("blog") {
                                Blog (
                                    onNavigate = {
                                        startBlogActivity(it)
                                    },
                                    blogs = viewModel.posts
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

