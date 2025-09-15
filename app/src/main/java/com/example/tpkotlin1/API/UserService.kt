package com.example.tpkotlin1.API

import com.example.tpkotlin1.API.RetrofitTools.Companion.retrofit
import retrofit2.http.Body
import retrofit2.http.POST



data class LoginRequest(
    val email: String,
    val password: String
)
{}
interface UserService {

    @POST("login")
    suspend fun loginUser(@Body request: LoginRequest) : ApiResponse<String>



    object UserServiceApi {
        val userService : UserService by lazy { retrofit.create(UserService::class.java) }
    }
}