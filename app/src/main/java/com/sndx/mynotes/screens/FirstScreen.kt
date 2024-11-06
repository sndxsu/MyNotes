package com.sndx.mynotes.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.sndx.mynotes.db.DbManager
import com.sndx.mynotes.screens.components.ListItem

@Composable
fun FirstScreen(scrollState:LazyListState, dbManager: DbManager) {
    ColumnsTest(scrollState, dbManager)
}


@Composable
fun ColumnsTest(scrollState:LazyListState, dbManager: DbManager){
    dbManager.openDB()
    val values = dbManager.readDbData()
    dbManager.closeDb()
    Scaffold {innerPadding->
        Column(modifier = Modifier.padding(innerPadding)){
        LazyColumn(userScrollEnabled = true, state = scrollState){
            items(values.count()){ index ->
                ListItem(name = values[index], prof = "test2")
            }
        }
        }
    }
}