package com.example.tpkotlin1.API

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tpkotlin1.helper.AppAlertHelper
import com.example.tpkotlin1.helper.AppProgressHelper
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.time.Duration.Companion.seconds



@JsonClass(generateAdapter = true)
data class LoginResponse(
    @Json(name = "code") val code:String,
    @Json(name = "message") val message: String,
    @Json(name = "data") val token: String
)
{}

data class UserViewModel(var email : String = "", var password : String = "") : ViewModel() {


    fun login(context: Context)
    {
//        if(_email.value.isBlank() || _password.value.isBlank())
//        {
//
//        }
        viewModelScope.launch {

            AppProgressHelper.get().show("Login")
            delay(duration = 2.seconds)

            AppProgressHelper.get().close()
            withContext(Dispatchers.IO) {
                val request = LoginRequest(email = email, password = password)
                val response = UserService.UserServiceApi.userService.loginUser(request).execute()
                AppAlertHelper.get().show(response.message(),onClose = {
                    if(response.code() == 200)
                    {
                        AppAlertHelper.get()
                    }
                })
            }



        }


    }
}