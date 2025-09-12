package com.example.tpkotlin1.API

import com.example.tpkotlin1.API.RetrofitTools.Companion.retrofit
import com.example.tpkotlin1.Article
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ArticleService {
    @GET("articles",)
    suspend fun getArticles() : ApiResponse

    @GET("articles/{id}")
    suspend fun getArticle(@Path("id") id: String) : SingleApiResponse

    object ArticleServiceApi {
        val articleService : ArticleService by lazy { retrofit.create(ArticleService::class.java) }
    }
}