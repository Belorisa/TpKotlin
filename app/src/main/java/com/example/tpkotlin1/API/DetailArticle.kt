package com.example.tpkotlin1.API

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tpkotlin1.ArticleCard
import com.example.tpkotlin1.ArticleViewModel
import com.example.tpkotlin1.EniTitle
import com.example.tpkotlin1.Mainpage
import com.example.tpkotlin1.Template

class DetailArticle : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        val article = ArticleViewModel()
        var id = intent.getStringExtra("id")

        article.callArticle(id!!)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DetailArticles(viewModel = article)
        }

    }
}
@Composable
fun DetailArticles(viewModel: ArticleViewModel){
    val articleId by viewModel.article.collectAsState()

    Template {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(40.dp)
                .padding(top = 10.dp)
        ) {
            Spacer(modifier = Modifier.height(140.dp))
            EniTitle("Detail d'Article")
            Spacer(modifier = Modifier.height(40.dp))
            ArticleCard(articleId)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DetailPreview() {
    DetailArticles(viewModel = ArticleViewModel())
}
