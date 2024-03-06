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
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.example.compose.OceanPulseTheme
import com.google.firebase.auth.FirebaseAuth
import com.licoding.oceanpulse.domain.models.BottomNavigationItem
import com.licoding.oceanpulse.domain.utils.AlarmManager
import com.licoding.oceanpulse.presentation.Main.MainViewmodel
import com.licoding.oceanpulse.presentation.Main.blog.Blog
import com.licoding.oceanpulse.presentation.Main.components.*
import com.licoding.oceanpulse.presentation.Main.quiz.MarineConservation
import com.licoding.oceanpulse.presentation.Main.quiz.MarineMythology
import com.licoding.oceanpulse.presentation.Main.quiz.MarinePollution
import com.licoding.oceanpulse.presentation.Main.quiz.MarineTechnology
import com.licoding.oceanpulse.presentation.Main.search.Search
import com.licoding.oceanpulse.presentation.common.Congratulations

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
        val alarmManager = AlarmManager(this)
        alarmManager.startTimer()
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
                    route = "quizes",
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
            fun startCameraActivity() {
                startActivity(Intent(this@MainActivity, CameraActivity::class.java))
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
                                    context = application,
                                    navController = navController,
                                    articles = viewModel.articles
                                )
                            }
                            composable("search") {
                                Search(navController = navController)
                            }
                            composable("profile") {
                                Profile(
                                    navigate = { navigate() },
                                    state = state,
                                    onEvent = viewModel::onEvent,
                                    onNavigate = {
                                        startCameraActivity()
                                    }
                                )
                            }

                            navigation(startDestination = "quiz", route = "quizes") {
                                composable("quiz") {
                                    Quiz(navController)
                                }
                                composable("marinetech") {
                                    MarineTechnology()
                                }
                                composable("marinepoll") {
                                    MarinePollution(
                                        navController = navController,
                                        onEvent = viewModel::onEvent,
                                        state = state
                                    )
                                }
                                composable("marinemyth") {
                                    MarineMythology()
                                }
                                composable("marinecons") {
                                    MarineConservation()
                                }
                                composable("congrats") {
                                    Congratulations()
                                }
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

