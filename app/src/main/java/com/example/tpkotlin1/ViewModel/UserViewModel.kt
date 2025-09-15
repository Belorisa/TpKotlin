package com.example.tpkotlin1.ViewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tpkotlin1.API.AuthContext
import com.example.tpkotlin1.Service.LoginRequest
import com.example.tpkotlin1.Service.UserService
import com.example.tpkotlin1.ListArticle
import com.example.tpkotlin1.helper.AppAlertHelper
import com.example.tpkotlin1.helper.AppContextHelper
import com.example.tpkotlin1.helper.AppProgressHelper
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.seconds

data class UserViewModel(var email : String = "", var password : String = "") : ViewModel() {


    fun login(context: Context)
    {


        viewModelScope.launch {

            AppProgressHelper.Companion.get().show("Login")


            delay(duration = 1.seconds)

            val request = LoginRequest(email = email, password = password)

            val response = UserService.UserServiceApi.userService.loginUser(request)

            AppProgressHelper.Companion.get().close()

            // Stocker le token
            if (response.code.equals("200")){
                AuthContext.Companion.get().setAuthToken(response.data!!);
            }

                AppAlertHelper.Companion.get().show(response.code,onClose = {
                    if(response.code.equals("200"))
                    {
                        AppContextHelper.Companion.openActivity(context, ListArticle::class)
                    }
                })




        }


    }
}