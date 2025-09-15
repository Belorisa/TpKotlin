package com.example.tpkotlin1.Service

import com.example.tpkotlin1.API.ApiResponse
import com.example.tpkotlin1.API.RetrofitTools.Companion.retrofit
import com.example.tpkotlin1.Article
import retrofit2.http.GET
import retrofit2.http.Path

interface ArticleService {
    @GET("articles",)
    suspend fun getArticles() : ApiResponse<List<Article>>

    @GET("articles/{id}")
    suspend fun getArticle(@Path("id") id: String) : ApiResponse<Article>

    object ArticleServiceApi {
        val articleService : ArticleService by lazy { retrofit.create(ArticleService::class.java) }
    }
}