package com.example.tpkotlin1.Service

import com.example.tpkotlin1.API.ApiResponse
import com.example.tpkotlin1.API.RetrofitTools.Companion.retrofit
import retrofit2.http.Body
import retrofit2.http.POST



data class LoginRequest(
    val email: String,
    val password: String
)
{}

data class SubscribeRequest(
    val email: String,
    val password: String,
    val passwordConfirm: String,
    val pseudo: String = "User",
    val cityCode: String ="4400",
    val city: String ="Nantes",
    val phone: String ="06660000"
)
{}


data class User(
    val email: String,
    val password: String,
    val pseudo: String ,
    val cityCode: String ,
    val city: String ,
    val phone: String
)
{}


data class PasswordRequest(
    val email: String
)
{}

interface UserService {

    @POST("login")
    suspend fun loginUser(@Body request: LoginRequest) : ApiResponse<String>


    @POST("signup")
    suspend fun signUpUser(@Body request: SubscribeRequest) : ApiResponse<User>


    @POST("reset-password")
    suspend fun passwordReset(@Body request : PasswordRequest) : ApiResponse<String>
    object UserServiceApi {
        val userService : UserService by lazy { retrofit.create(UserService::class.java) }
    }
}