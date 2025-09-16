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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tpkotlin1.ViewModel.ArticleViewModel

class DetailArticle : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        val article = ArticleViewModel()
        val id = intent.getStringExtra("id")

        article.callArticle(id!!)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DetailArticles(viewModel = article,id)
        }

    }
}

@Composable
fun DetailArticles(viewModel: ArticleViewModel, id : String){
    val articleId by viewModel.article.collectAsState()
    val context = LocalContext.current

    Template {
        Column(
            horizontalAlignment = Alignment.Companion.CenterHorizontally,
            modifier = Modifier.Companion
                .fillMaxSize()
                .padding(40.dp)
                .padding(top = 10.dp)
        ) {
            Spacer(modifier = Modifier.Companion.height(140.dp))
            EniTitle("Detail d'Article")
            Spacer(modifier = Modifier.Companion.height(40.dp))
            ArticleCard(articleId)

            EniButton(
                label = "Modifier",
                context = LocalContext.current,
                target = ArticleForm::class,
                int = id
            )
            EniButtonLogin (
                label = "Supprimer", onClick = {
                    viewModel.deleteArticle(context)
                }
            )
            EniButton(
                label = "Retour Liste",
                context = LocalContext.current,
                target = ListArticle::class
            )

        }


    }
}

@Preview(showBackground = true)
@Composable
fun DetailPreview() {
    DetailArticles(viewModel = ArticleViewModel(),"test")
}