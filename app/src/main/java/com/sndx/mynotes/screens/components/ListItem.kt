package com.sndx.mynotes.screens.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable

fun ListItem(name: String, prof:String){
    Card (
        Modifier
            .fillMaxWidth()
            .padding(10.dp), RoundedCornerShape(10.dp)
    ){
        Row (Modifier.fillMaxSize(), verticalAlignment = Alignment.CenterVertically){
            Column (horizontalAlignment = Alignment.Start,
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .padding(start = 10.dp, top = 5.dp, end = 5.dp)
                ){
                Text(text = name)
                Text(text = prof)
            }

            IconButton(
                onClick = { /*todo*/ }
            ) {
                Icon(
                    imageVector = Icons.Default.FavoriteBorder,
                    contentDescription = "Important"
                )
            }
        }
    }
}