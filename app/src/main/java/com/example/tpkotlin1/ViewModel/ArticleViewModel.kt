package com.example.tpkotlin1.ViewModel

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tpkotlin1.ListArticle
import com.example.tpkotlin1.R
import com.example.tpkotlin1.Service.ArticleService
import com.example.tpkotlin1.Service.UserService
import com.example.tpkotlin1.helper.AppAlertHelper
import com.example.tpkotlin1.helper.AppContextHelper
import com.example.tpkotlin1.helper.AppProgressHelper
import com.example.tpkotlin1.helper.commonCallApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.seconds
data class Article(val id: String? = null,
                   val title: String = "",
                   val desc: String = "",
                   val author: String = "",
                   val imgPath: String = ""
)
{}

class ArticleViewModel(application : Application) : EniViewModel(application){

    val articles = MutableStateFlow<List<Article>>(emptyList())
    val article = MutableStateFlow<Article>(Article())


    fun ajouterArticle(context: Context)
    {
        AppProgressHelper.Companion.get().show(getString(R.string.helper_add_article))

        viewModelScope.launch {
            delay( duration = 2.seconds)


            var request = Article(
                title = article.value.title,
                desc = article.value.desc,
                author = article.value.author,
                imgPath = article.value.imgPath
            )

            if(article.value.id != null)
            {
                 request = Article(
                    id = article.value.id,
                    title = article.value.title,
                    desc = article.value.desc,
                    author = article.value.author,
                    imgPath = article.value.imgPath
                )
            }



            val response = ArticleService.ArticleServiceApi.articleService.addArticle(request)

            AppProgressHelper.Companion.get().close()

            AppAlertHelper.Companion.get().show(response.message,onClose = {
                if(response.code.equals("200"))
                {
                    AppContextHelper.Companion.openActivity(context, ListArticle::class)
                }
            })
        }
    }

    fun deleteArticle(context : Context){
        AppProgressHelper.Companion.get().show(getString(R.string.helper_del_article))

        viewModelScope.launch {
            delay( duration = 2.seconds)


            val response = ArticleService.ArticleServiceApi.articleService.deleteArticle(article.value.id!!)

            AppProgressHelper.Companion.get().close()

            AppAlertHelper.Companion.get().show(response.message,onClose = {
                if(response.code.equals("200"))
                {
                    AppContextHelper.Companion.openActivity(context, ListArticle::class)
                }
            })
        }
    }


    fun callArticlesApi(){

        commonCallApi<List<Article>>(getString(R.string.helper_load_articles),viewModelScope, doAction = {
            val apiResponse = ArticleService.ArticleServiceApi.articleService.getArticles()
            articles.value = apiResponse.data!!

            apiResponse
        })

    }

    fun callArticle(id : String){
        commonCallApi<Article>(getString(R.string.helper_load_articles),viewModelScope, doAction = {
            val apiResponse = ArticleService.ArticleServiceApi.articleService.getArticle(id)
            article.value = apiResponse.data!!

            apiResponse
        })
    }

}