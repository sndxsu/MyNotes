package com.sndx.mynotes.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.sndx.mynotes.db.DbManager
import com.sndx.mynotes.screens.components.ListItem

@Composable
fun FirstScreen(dbManager: DbManager, innerPadding: PaddingValues) {
    dbManager.openDB()
    val values = dbManager.readDbData()
    dbManager.closeDb()
    Scaffold {padding ->
        Column(modifier = Modifier.padding(innerPadding)){
            LazyColumn(userScrollEnabled = true, contentPadding = innerPadding){
                items(values.count()){ index ->
                    ListItem(values[index], onClick = {
                        dbManager.openDB()
                        dbManager.updateNote(values[index])
                        dbManager.closeDb()
                    })
                }
            }
        }
    }
}