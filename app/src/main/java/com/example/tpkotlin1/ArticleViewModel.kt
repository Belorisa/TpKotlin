package com.example.tpkotlin1

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tpkotlin1.API.ArticleService
import com.example.tpkotlin1.helper.AppAlertHelper
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
        AppProgressHelper.get().show("Chargement des articles")
        viewModelScope.launch {

            delay(duration = 2.seconds)
            val apiResponse = ArticleService.ArticleServiceApi.articleService.getArticles()

            articles.value = apiResponse.data

            AppProgressHelper.get().close()

            //afficher le message du back
            AppAlertHelper.get().show(apiResponse.message,)
        }
    }

    fun callArticle(id : String){
        viewModelScope.launch {
            article.value = ArticleService.ArticleServiceApi.articleService.getArticle(id).data
        }
    }

}