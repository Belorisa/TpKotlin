package com.example.tpkotlin1

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.example.tpkotlin1.ui.theme.TpKotlin1Theme

@Composable
fun BackgroundImage(){
    Image(
        painter = painterResource(R.drawable.luke_chesser_unsplash),
        contentDescription = "",
        contentScale = ContentScale.Crop,
        modifier = Modifier.fillMaxSize()
    )
}

@Composable
fun Template(content: @Composable () -> Unit){
    TpKotlin1Theme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            Box(modifier = Modifier.padding(innerPadding )){
                BackgroundImage()
                content()
            }

        }
    }
}