package com.example.tpkotlin1

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tpkotlin1.API.ArticleService
import com.example.tpkotlin1.helper.AppProgressHelper
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.seconds

class ArticleViewModel : ViewModel(){

    val _articles = MutableStateFlow<List<Article>>(emptyList())
    val articles : StateFlow<List<Article>> = _articles
    val article = MutableStateFlow<Article?>(null)

    var isLoading = MutableStateFlow<Boolean>(false)

    fun ajouterArticle(article: Article)
    {
        _articles.value = _articles.value + article
    }

    fun callArticlesApi(){
        AppProgressHelper.get().show("Chargement des articles")
        viewModelScope.launch {

            delay(duration = 2.seconds)
            _articles.value = ArticleService.ArticleServiceApi.articleService.getArticles().data

            AppProgressHelper.get().close()
        }
    }

    fun callArticle(id : String){
        viewModelScope.launch {
            article.value = ArticleService.ArticleServiceApi.articleService.getArticle(id).data
        }
    }

}