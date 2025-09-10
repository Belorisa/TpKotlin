package com.example.tpkotlin1

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tpkotlin1.Article
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlin.collections.mutableListOf

class ArticleViewModel : ViewModel(){

    val _articles = MutableStateFlow<List<Article>>(emptyList())
    val articles : StateFlow<List<Article>> = _articles

    fun ajouterArticle(article: Article)
    {
        _articles.value = _articles.value + article
    }


}