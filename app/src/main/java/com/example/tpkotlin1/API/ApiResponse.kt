package com.example.tpkotlin1.API



data class ApiResponse<T>(
    val code: String,
    val message: String,
    val data: T?
)
{}