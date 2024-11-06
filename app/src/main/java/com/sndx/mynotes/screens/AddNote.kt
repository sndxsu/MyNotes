package com.sndx.mynotes.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import com.sndx.mynotes.db.DbManager
import com.sndx.mynotes.db.Note

@Composable
fun AddNote(dbManager: DbManager){
    var labelText by remember{ mutableStateOf("")}
    var descriptionText by remember{ mutableStateOf("")}
    val paddingVal = 5.dp
    Column(modifier = Modifier.fillMaxSize().padding(paddingVal + 15.dp), verticalArrangement = Arrangement.Center){
        OutlinedTextField(modifier = Modifier.run { fillMaxWidth().padding(paddingVal) }, value = labelText, onValueChange = {labelText = it}, label = {
            Text("Note label")
        })
        TextField(modifier = Modifier.fillMaxWidth().padding(paddingVal).height(200.dp), value = descriptionText, onValueChange = {descriptionText = it},
            label = {Text("Description")})
        Button(onClick = { buttonClick(labelText, descriptionText, dbManager) }, modifier = Modifier.align(androidx.compose.ui.Alignment.End).padding(paddingVal), shape = RoundedCornerShape(paddingVal)) {
            Text("Add note...")
        }
    }
}

fun buttonClick(label:String, description:String, dbManager: DbManager){
    dbManager.openDB()
    dbManager.addToDB(Note(label, description, false))
    dbManager.closeDb()
}