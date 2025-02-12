package com.sndx.mynotes.screens.components

import androidx.compose.ui.graphics.vector.ImageVector

data class NavBarItem (
    val title:String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector
)