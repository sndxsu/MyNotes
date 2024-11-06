package com.sndx.mynotes.screens.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.sndx.mynotes.R

@Composable

fun ListItem(name: String, prof:String){
    Card (
        Modifier
            .fillMaxWidth()
            .height(150.dp)
            .padding(10.dp), RoundedCornerShape(10.dp)
    ){
        Row (Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically){
            Image(painter = painterResource(id = R.drawable.anime_girl), contentDescription = "r",
                Modifier
                    .fillMaxHeight()
                    .padding(5.dp)
                    .clip(
                        CircleShape
                    ))
            Column (Modifier.padding(5.dp)){
                Text(text = name)
                Text(text = prof)
            }
        }
    }
}