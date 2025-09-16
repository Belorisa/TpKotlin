package com.example.tpkotlin1.Service

import com.example.tpkotlin1.API.ApiResponse
import com.example.tpkotlin1.API.RetrofitTools.Companion.retrofit
import com.example.tpkotlin1.ViewModel.Article
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ArticleService {
    @GET("articles",)
    suspend fun getArticles() : ApiResponse<List<Article>>

    @GET("articles/{id}")
    suspend fun getArticle(@Path("id") id: String) : ApiResponse<Article>

    @POST("articles/save")
    suspend fun addArticle(@Body request: Article) : ApiResponse<Article>

    @DELETE("articles/{id}")
    suspend fun deleteArticle(@Path("id")id: String) : ApiResponse<String>

    object ArticleServiceApi {
        val articleService : ArticleService by lazy { retrofit.create(ArticleService::class.java) }
    }
}