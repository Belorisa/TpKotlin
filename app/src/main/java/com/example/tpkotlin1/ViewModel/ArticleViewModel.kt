package com.example.tpkotlin1.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tpkotlin1.Service.ArticleService
import com.example.tpkotlin1.Article
import com.example.tpkotlin1.helper.AppProgressHelper
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.seconds

class ArticleViewModel : ViewModel(){

    val articles = MutableStateFlow<List<Article>>(emptyList())
    val article = MutableStateFlow<Article?>(null)

    var isLoading = MutableStateFlow<Boolean>(false)

    fun ajouterArticle(article: Article)
    {
        articles.value = articles.value + article
    }

    fun callArticlesApi(){
        AppProgressHelper.Companion.get().show("Chargement des articles")
        viewModelScope.launch {

            delay(duration = 2.seconds)
            val apiResponse = ArticleService.ArticleServiceApi.articleService.getArticles()

            articles.value = apiResponse.data!!

            AppProgressHelper.Companion.get().close()

        }
    }

    fun callArticle(id : String){
        viewModelScope.launch {
            article.value = ArticleService.ArticleServiceApi.articleService.getArticle(id).data
        }
    }

}