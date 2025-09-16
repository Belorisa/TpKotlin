package com.example.tpkotlin1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tpkotlin1.ViewModel.ArticleViewModel
import com.example.tpkotlin1.ui.theme.TpKotlin1Theme

class ArticleForm : ComponentActivity() {

    val articleView = ArticleViewModel()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val id = intent?.getStringExtra("id")
        enableEdgeToEdge()
        setContent {
            ArticleFormulaire(articleView,id)
        }
    }
}

@Composable
fun ArticleFormulaire(viewModel: ArticleViewModel,id : String?) {

    val article by viewModel.article.collectAsState()

    LaunchedEffect(id) {
        if(id != null)
        {
         viewModel.callArticle(id)
        }
    }

    val context = LocalContext.current

    Template {
        Column(modifier = Modifier.padding(60.dp).padding(top = 50.dp)) {

            Image(
                painter = painterResource(R.drawable.logo_eni_round)
                ,contentDescription = "Logo"
                ,modifier = Modifier.fillMaxWidth().padding(bottom = 20.dp)
            )

            EniTitle("Sign in")
            article.let {
                EniTextField("Titre",
                    value = it.title,
                    onValueChange = { newTitle -> viewModel.article.value = viewModel.article.value.copy(title = newTitle)
                    }
                )
            }
            article.let {
                EniTextField("Description",
                    value = it.desc,
                    onValueChange = { newDesc -> viewModel.article.value = viewModel.article.value.copy(desc = newDesc)
                    }
                )
            }
            article.let {
                EniTextField("Auteur",
                    value = it.author,
                    onValueChange = { newAuthor -> viewModel.article.value = viewModel.article.value.copy(author = newAuthor)
                    }
                )
            }
            article.let {
                EniTextField("Lien Img",
                    value = it.imgPath,
                    onValueChange = { newImg -> viewModel.article.value = viewModel.article.value.copy(imgPath = newImg)
                    }
                )
            }

            EniButtonLogin("Connexion", onClick = {viewModel.ajouterArticle(context)
            })
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    TpKotlin1Theme {
        ArticleFormulaire(viewModel = ArticleViewModel(),"test")
    }
}