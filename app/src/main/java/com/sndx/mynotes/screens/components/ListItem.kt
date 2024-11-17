package com.sndx.mynotes.screens.components
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sndx.mynotes.db.Note

@Composable
fun ListItem(note: Note,
             onClick: () -> Unit  //передається метод зберігання даних в дб
){
    var isFavorite by remember { mutableStateOf(note.isImportant) }
    val icon = if (isFavorite)
        Icons.Default.Favorite else Icons.Default.FavoriteBorder
    Card (
        Modifier
            .fillMaxWidth()
            .padding(10.dp), RoundedCornerShape(10.dp)
    ){
        Row (Modifier.fillMaxSize(), verticalAlignment = Alignment.CenterVertically){
            Column (horizontalAlignment = Alignment.Start,
                modifier = Modifier
                    .fillMaxWidth(0.88f)
                    .padding(start = 10.dp, top = 5.dp, end = 5.dp)
                ){
                Text(text = note.name)
                Text(text = note.content)
            }

            IconButton(
                onClick = {
                    isFavorite = !isFavorite
                    note.isImportant = isFavorite
                    println("${note.isImportant}, $isFavorite")
                    onClick()
                }
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = "Important"
                )
            }
        }
    }
}