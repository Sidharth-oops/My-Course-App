package com.example.mycourseapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.mycourseapp.ui.theme.MyCourseAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyCourseAppTheme {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colors.background)
                ) {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "home") {
                        composable("home") {
                            HomeScreen(onDetailsClick = { title ->
                                navController.navigate("details/title=$title")
                            }, onAboutClick = {
                                navController.navigate("about")
                            })
                        }
                        composable("about") {
                            AboutScreen(
                                onNavigateUp = { navController.popBackStack() })

                        }
                        composable(
                            "details/title={title}",
                            arguments = listOf(navArgument("title") {
                                type = NavType.StringType
                                nullable = true
                            })
                        ) { backStackEntry ->
                            val arguments = requireNotNull(backStackEntry.arguments)
                            val title = arguments.getString("title")
                            if (title != null) {
                                DetailsScreen(title = title, onNavigateUp = {
                                    navController.popBackStack()
                                })
                            }
                        }
                    }
                }
            }
        }
    }
}

//Home Screen
@Composable
fun HomeScreen(onDetailsClick: (title: String) -> Unit, onAboutClick: () -> Unit) {
    Scaffold {
        LazyColumn(contentPadding = it) {
            item {
                HomeAppBar(onAboutClick)
            }
            item {
                Spacer(modifier = Modifier.height(30.dp))
            }
            items(allCourses) { item ->
                CourseCard(
                    item,
                    onClick = { onDetailsClick(item.title) }
                )
            }
        }
    }
}

@Composable
fun HomeAppBar(onAboutClick: () -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 10.dp)
    ) {
        Text(text = "My Udemy Courses", style = MaterialTheme.typography.h6)
        Spacer(modifier = Modifier.weight(1f))
        TextButton(onClick = onAboutClick) {
            Text(text = "About", fontSize = 24.sp)
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CourseCard(item: Courses, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 10.dp)
            .fillMaxWidth(), onClick = onClick

    ) {
        Column() {
            Image(
                painter = painterResource(id = item.thumbNail),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
                    .aspectRatio(16f / 9f),
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            ) {
                Text(text = item.title)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = item.body, maxLines = 1, style = MaterialTheme.typography.body1)
            }
        }
    }
}

@Composable
fun AboutScreen(onNavigateUp: () -> Unit) {
    Scaffold() {
        Column(modifier = Modifier.padding(it)) {
            AppBar(title = "About", onNavigateUp = onNavigateUp)
            Spacer(modifier = Modifier.height(20.dp))
            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = "This App is Demonstration about Navigation in Android jetpack compose")
            }
            Spacer(modifier = Modifier.height(20.dp))
            val udemy_link = LocalUriHandler.current
            Button(onClick = { udemy_link.openUri("https://www.udemy.com/course/mastering-android-app-development-with-kotlin-build-38-apps/") }) {
                Text(text = "View Our Udemy Course")
            }
        }
    }
}

@Composable
fun AppBar(title: String, onNavigateUp: () -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(vertical = 10.dp)
    ) {
        IconButton(onClick = onNavigateUp) {
            Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = null)
        }
        Spacer(modifier = Modifier.width(10.dp))
        Text(text = title, fontSize = 24.sp)
    }

}

@Composable
fun DetailsScreen(title: String, onNavigateUp: () -> Unit) {
    val course = allCourses.first { it.title == title }
    Scaffold() {
        Column(Modifier.padding(it)) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(vertical = 10.dp)
            ) {
                IconButton(onClick = onNavigateUp) {
                    Icon(imageVector = Icons.Rounded.ArrowBack, contentDescription = null)
                }
            }
            Image(
                painter = painterResource(id = course.thumbNail),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(16f / 9f),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(text = course.body, modifier = Modifier.fillMaxSize(), fontSize = 20.sp)

        }

    }
}

