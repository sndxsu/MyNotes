package com.sndx.mynotes

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.List
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sndx.mynotes.db.DbManager
import com.sndx.mynotes.screens.AddNote
import com.sndx.mynotes.screens.FirstScreen
import com.sndx.mynotes.screens.SecondScreen
import com.sndx.mynotes.screens.ThirdScreen
import com.sndx.mynotes.screens.components.NavBarItem
import com.sndx.mynotes.ui.theme.MyNotesTheme
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val db = DbManager(this)
        setContent {
            MyNotesTheme {
                //test comment to push
                val view = LocalView.current
                val colorScheme = MaterialTheme.colorScheme
                var selectedItemIndex by rememberSaveable{  mutableStateOf(0) }
                var showFAB by remember { mutableStateOf(true) }
                val scrollState = rememberLazyListState()
                var isNavbarVisible by remember { mutableStateOf(true) }
                var previousIndex = 1
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    // items in navigation bar
                    val items = listOf(
                        NavBarItem(
                            title = "Notes",
                            selectedIcon = Icons.Filled.Favorite,
                            unselectedIcon = Icons.Outlined.Favorite,
                            hasNews = false
                        ),
                        NavBarItem(
                            title = "Important",
                            selectedIcon = Icons.Filled.List,
                            unselectedIcon = Icons.Outlined.List,
                            hasNews = false
                        ),
                        NavBarItem(
                            title = "Settings",
                            selectedIcon = Icons.Filled.Settings,
                            unselectedIcon = Icons.Outlined.Settings,
                            hasNews = false
                        )
                    )
                    val navController = rememberNavController()
                    Scaffold(bottomBar = {
                            AnimatedVisibility(
                                visible = isNavbarVisible,
                                enter = slideInVertically(initialOffsetY = { it }),
                                exit = slideOutVertically(targetOffsetY = { it
                                })
                            ){
                        NavigationBar(containerColor = colorScheme.surface){
                                items.forEachIndexed{index, item ->
                                    NavigationBarItem(
                                        label = {
                                            Text(item.title)
                                        },
                                        selected = selectedItemIndex == index,
                                        onClick = {
                                            selectedItemIndex = index
                                            navController.navigate(item.title) },
                                        icon = {
                                            Icon(
                                                if(index == selectedItemIndex) item.selectedIcon
                                                else item.unselectedIcon,
                                                item.title
                                            )
                                        })
                                }

                        }

                    }},
                        floatingActionButton = {
                            AnimatedVisibility(visible = showFAB,
                                enter = fadeIn(),
                                exit = fadeOut()
                            ) {
                                FloatingActionButton(onClick = {
                                    navController.navigate("add_note")
                                    showFAB = false
                                    isNavbarVisible = false
                                }){Icon(Icons.Filled.Add, "Add note")}
                            }
                        },
                        floatingActionButtonPosition = FabPosition.End
                    ){innerPadding ->
                        LaunchedEffect(scrollState) {
                            snapshotFlow { scrollState.firstVisibleItemIndex }
                                .map { scrollState.firstVisibleItemScrollOffset }
                                .distinctUntilChanged()
                                .collect {
                                    isNavbarVisible = scrollState.firstVisibleItemIndex < previousIndex
                                    previousIndex = scrollState.firstVisibleItemIndex
                                }
                        }
                        val window = (view.context as Activity).window
                        val windowInsetsController = remember {
                            WindowCompat.getInsetsController(window, view)
                        }
                        SideEffect {
                            val isLight = colorScheme.background.luminance() > 0.5
                            if (isLight)
                                window.navigationBarColor = colorScheme.surfaceVariant.toArgb()
                            else window.navigationBarColor = colorScheme.surface.toArgb()
                            windowInsetsController.isAppearanceLightStatusBars = isLight
                            window.statusBarColor = colorScheme.background.toArgb() //при зміні світлого на темний режим - збивається колір статусбару
                        }
                        NavHost(navController = navController, startDestination = items[0].title){
                            composable(route = items[0].title){
                                FirstScreen(scrollState, db)
                                BackHandler(enabled = true) {}
                                showFAB = true
                            }
                            composable(route = items[1].title){
                                SecondScreen()
                                BackHandler(enabled = true) {}
                                showFAB = false
                            }
                            composable(route = items[2].title){
                                ThirdScreen()
                                BackHandler(enabled = true) {}
                                showFAB = false
                            }
                            composable("add_note"){
                                AddNote(db)
                                showFAB = false
                                BackHandler(onBack = {
                                    isNavbarVisible = true
                                    navController.navigate("Notes")
                                })
                            }
                        }
                    }
                }
            }
        }
    }
}
