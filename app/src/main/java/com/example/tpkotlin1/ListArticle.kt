package com.example.tpkotlin1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tpkotlin1.Article


class ListArticle : ComponentActivity() {
    val articlesList = ArticleViewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PageList(articlesList)
        }
    }
}

@Composable
fun PageList(viewModel: ArticleViewModel){


    val articles by viewModel.articles.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.ajouterArticle(Article("Chips","Des chips !","https://www.valgourmand.com/36711-superlarge_default/chips-nature-lays-45g.webp"))
        viewModel.ajouterArticle(Article("Glace au Chocolat", "De la glace !",  "https://www.benjerry.fr/files/live/sites/systemsite/files/EU%20Specific%20Assets/Flavors/Product%20Assets/Chocolate%20Fudge%20Brownie%20Non-Dairy/Oat/39989_AT-BE-CH-DE-FR-NL_IC_ND-Chocolate-Fudge-Brownie_465ml_Open_Open_Brand-1920px_8711327627086%20(1).png"))
    }


    Template() {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(40.dp)
                .padding(top = 10.dp)
        ) {
            Spacer(modifier = Modifier.height(140.dp))
            EniTitle("Liste d'Article")
            Spacer(modifier = Modifier.height(40.dp))
            LazyColumn {
                items(articles) { article ->
                    WrapPadding {
                        ArticleCard(article)
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PageListPreview() {
    PageList(viewModel = ArticleViewModel())
}

