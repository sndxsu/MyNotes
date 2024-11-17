package com.sndx.mynotes.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun AddNote(onclick: (label: String, description: String) -> Unit){
    var labelText by remember{ mutableStateOf("")}
    var descriptionText by remember{ mutableStateOf("")}
    val paddingVal = 5.dp
    Box(Modifier.fillMaxSize().imePadding()){ //todo: fix ime padding
    Column(modifier = Modifier
        .fillMaxHeight()
        .padding(paddingVal + 15.dp)) {
        Spacer(Modifier.height(250.dp))
        OutlinedTextField(modifier = Modifier.run { fillMaxWidth()}, value = labelText, onValueChange = {labelText = it}, label = {
            Text("Note label")
        })
        TextField(modifier = Modifier.fillMaxWidth().height(200.dp), value = descriptionText, onValueChange = {descriptionText = it},
            label = {Text("Description")})
        Button(onClick = {
            onclick(labelText, descriptionText)
        }, modifier = Modifier.padding(paddingVal), shape = RoundedCornerShape(paddingVal)) {
            Text("Add note...")
        }}
    }}