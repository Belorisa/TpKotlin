package com.example.tpkotlin1.API

import com.example.tpkotlin1.API.RetrofitTools.Companion.retrofit
import com.squareup.moshi.JsonClass
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST


@JsonClass(generateAdapter = true)
data class LoginRequest(
    val email: String,
    val password: String
)
{}
interface UserService {

    @POST("login")
    fun loginUser(@Body request: LoginRequest) : Call<LoginResponse>



    object UserServiceApi {
        val userService : UserService by lazy { retrofit.create(UserService::class.java) }
    }
}