package com.sndx.mynotes.screens

import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.sndx.mynotes.screens.components.ListItem

@Composable
fun SecondScreen() {
    Row {
        ListItem("huy pizda", "ne actor")
    }
}