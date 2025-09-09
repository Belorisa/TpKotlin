package com.example.tpkotlin1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.tpkotlin1.ui.theme.Article


class ListArticle : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PageList()
        }
    }
}

@Composable
fun PageList(){

    var articles = mutableListOf(
        Article("Chips","Des chips !","https://www.valgourmand.com/36711-superlarge_default/chips-nature-lays-45g.webp"),
        Article("Glace au Chocolat", "De la glace !",  "")
    )




    Template {
        Column(modifier = Modifier.padding(60.dp).padding(top = 50.dp)) {
            Image(
                painter = painterResource(R.drawable.logo_eni_round)
                ,contentDescription = "Logo"
                ,modifier = Modifier.fillMaxWidth().padding(bottom = 20.dp)
            )
            EniTitle("Récupération de Mots de Passe")
            LazyColumn {
                items(articles) {
                    article -> Text("${article.title} : ${article.desc}",color = Color(0xFFeeeee4))
                    AsyncImage(model = article.imgPath,
                                contentDescription = " "
                        )

                }

            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun PageListPreview() {
    PageList()
}