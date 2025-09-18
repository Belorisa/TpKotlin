package com.example.tpkotlin1.ViewModel

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tpkotlin1.API.AuthContext
import com.example.tpkotlin1.ListArticle
import com.example.tpkotlin1.R
import com.example.tpkotlin1.Service.ArticleService
import com.example.tpkotlin1.Service.LoginRequest
import com.example.tpkotlin1.Service.SubscribeRequest
import com.example.tpkotlin1.Service.User
import com.example.tpkotlin1.Service.UserService
import com.example.tpkotlin1.helper.AppAlertHelper
import com.example.tpkotlin1.helper.AppContextHelper
import com.example.tpkotlin1.helper.AppProgressHelper
import com.example.tpkotlin1.helper.commonCallApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.seconds

data class UserSubscribeViewModel(var subscribeRequest : SubscribeRequest = SubscribeRequest(),val _application : Application)
    : EniViewModel(_application) {

        fun subscribe(onSubscribeRequestSuccess : () -> Unit = {}){

            commonCallApi<User>(getString(R.string.helper_user_subscribe),viewModelScope, doAction = {
                val apiResponse = UserService.UserServiceApi.userService.signUpUser(subscribeRequest)
                if (apiResponse.code.equals("200")){
                    onSubscribeRequestSuccess()
                }

                apiResponse
            })





        }

    }

