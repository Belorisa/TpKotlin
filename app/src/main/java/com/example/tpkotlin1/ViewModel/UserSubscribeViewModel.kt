package com.example.tpkotlin1.ViewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tpkotlin1.ListArticle
import com.example.tpkotlin1.Service.LoginRequest
import com.example.tpkotlin1.Service.SubscribeRequest
import com.example.tpkotlin1.Service.UserService
import com.example.tpkotlin1.helper.AppAlertHelper
import com.example.tpkotlin1.helper.AppContextHelper
import com.example.tpkotlin1.helper.AppProgressHelper
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.seconds

data class UserSubscribeViewModel(val email: String="", val password: String="",val passwordConfirm: String = "", val pseudo: String = "User", val cityCode: String ="4400", val city: String ="Nantes", val phone: String ="06660000")
    : ViewModel() {

        fun subscribe(context: Context){

            viewModelScope.launch {

                AppProgressHelper.Companion.get().show("Inscription")
                delay(duration = 1.seconds)
                    val request = SubscribeRequest(
                        email = email,
                        password = password,
                        passwordConfirm = passwordConfirm,
                        pseudo = pseudo,
                        cityCode = cityCode,
                        city = city,
                        phone = phone
                    )

                    val response = UserService.UserServiceApi.userService.signUpUser(request)

                    AppProgressHelper.Companion.get().close()

                    AppAlertHelper.Companion.get().show(response.message,onClose = {
                        if(response.code.equals("200"))
                        {
                            AppContextHelper.Companion.openActivity(context, ListArticle::class)
                        }
                    })
            }





        }

    }

