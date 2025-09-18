package com.example.tpkotlin1

import android.app.Application
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
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tpkotlin1.ViewModel.ArticleViewModel
import com.example.tpkotlin1.ViewModel.UserViewModel


class ListArticle : ComponentActivity() {
    lateinit var viewModel: ArticleViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        viewModel = ArticleViewModel(application = application)
        setContent {
            PageList(viewModel)
        }
    }
}

@Composable
fun PageList(viewModel: ArticleViewModel){

    Template() {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(40.dp)
                .padding(top = 10.dp)
        ) {
            Spacer(modifier = Modifier.height(140.dp))
            EniTitle(stringResource(R.string.label_article_list))
            Spacer(modifier = Modifier.height(40.dp))
            EniButtonClick("display List",onClick = {
                viewModel.callArticlesApi()
            })
            EniButton(
                label = stringResource(R.string.btn_article_create),
                context =  LocalContext.current,
                target = ArticleForm::class,
            )
            ArticleListView(viewModel)

        }






    }
}


@Composable
fun ArticleListView(viewModel: ArticleViewModel)
{
    val articles by viewModel.articles.collectAsState()

    LazyColumn {
        items(articles) { article ->
            WrapPadding {
                ArticleCard(article)
            }
            EniButton(
                label = "Détails",
                context =  LocalContext.current,
                target = DetailArticle::class,
                int = article.id
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PageListPreview() {
    PageList(viewModel = ArticleViewModel(Application()))
}

