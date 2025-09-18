package com.example.tpkotlin1.ViewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tpkotlin1.API.AuthContext
import com.example.tpkotlin1.Service.LoginRequest
import com.example.tpkotlin1.Service.UserService
import com.example.tpkotlin1.ListArticle
import com.example.tpkotlin1.Service.ArticleService
import com.example.tpkotlin1.helper.AppAlertHelper
import com.example.tpkotlin1.helper.AppContextHelper
import com.example.tpkotlin1.helper.AppProgressHelper
import com.example.tpkotlin1.helper.commonCallApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.seconds

data class UserViewModel(var email : String = "", var password : String = "") : ViewModel() {


    fun login(onLoginSucces : () -> Unit = {})
    {

            commonCallApi<String>("Tentative de connexion",viewModelScope, doAction = {
                val request = LoginRequest(email = email, password = password)
                val apiResponse = UserService.UserServiceApi.userService.loginUser(request)
                if (apiResponse.code.equals("200")){
                    AuthContext.Companion.get().setAuthToken(apiResponse.data!!);
                    onLoginSucces()
                }
                apiResponse
            })

    }


}
