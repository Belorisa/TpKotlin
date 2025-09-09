package com.example.tpkotlin1

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.tpkotlin1.ui.theme.Article
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

@Composable
fun EniTextField(label: String = "invalid")
{
    TextField(value = "", onValueChange = {}, label = {Text(label, color = Color(0xFFeeeee4))}, colors = TextFieldDefaults.colors( unfocusedContainerColor = Color.Transparent, focusedContainerColor = Color.Transparent,focusedIndicatorColor = Color(0xFFeeeee4)))
}



@Composable
fun WrapPadding(content: @Composable () -> Unit){
    Box(modifier = Modifier.padding(10.dp)) {
        content()
    }
}


@Composable
fun EniButton(label: String = "Invalid", onClick: () -> Unit = {})
{
    Button(
        onClick = {},
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
        , colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
    ) {
        Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxWidth().background(shape = ButtonDefaults.shape,brush = Brush.horizontalGradient(listOf(Color(0xFF2596be), Color(0xFF154c79)))).padding(vertical = 14.dp)){
            Text(label )
        }
    }
}

@Composable
fun EniTitle(label:String = "Invalide")
{
    Text(label,
        fontSize = 36.sp,
        fontWeight = FontWeight.Bold,
        color = Color(0xFFeeeee4),
        style = TextStyle(
            shadow = Shadow(
                color = Color(0xFFeeeee4)
            )
        ))
}

@Composable
fun ArticleCard(article: Article) {
    ElevatedCard(modifier = Modifier.fillMaxWidth()) {
        Column {
            Row(modifier = Modifier.padding(10.dp), verticalAlignment = Alignment.CenterVertically) {
                AsyncImage(
                    model = article.imgPath,
                    contentDescription = "",
                    placeholder = painterResource(R.drawable.logo_eni_round),
                    modifier = Modifier.size(50.dp)
                )
                Column(modifier = Modifier.padding(10.dp)) {
                    Text(article.title, fontWeight = FontWeight.Bold)
                    Text(article.desc , color = Color(0xFF555555))
                }
            }
            Box(
                modifier = Modifier
                    .background(
                        brush = Brush.linearGradient(
                            listOf(Color(0xFF0b58d8), Color(0xFF31a9ff))
                        )
                    )
                    .fillMaxWidth()
                    .height(4.dp)
            )
        }
    }
}