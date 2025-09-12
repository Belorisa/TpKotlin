package com.example.tpkotlin1

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Article( val id: String,
                    val title: String,
                    val desc: String,
                    val author: String,
                    val imgPath: String
) {
}