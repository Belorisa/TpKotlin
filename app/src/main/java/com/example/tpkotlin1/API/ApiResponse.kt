package com.example.tpkotlin1.API

import com.example.tpkotlin1.Article
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ApiResponse(
    val code: String,
    val message: String,
    val data: List<Article>
)
{}

@JsonClass(generateAdapter = true)
data class SingleApiResponse(
    val code: String,
    val message: String,
    val data: Article
)
{}