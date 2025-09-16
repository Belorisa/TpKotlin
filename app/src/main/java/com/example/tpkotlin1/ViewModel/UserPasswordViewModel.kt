package com.example.tpkotlin1.ViewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tpkotlin1.MainActivity
import com.example.tpkotlin1.Service.PasswordRequest
import com.example.tpkotlin1.Service.UserService
import com.example.tpkotlin1.helper.AppAlertHelper
import com.example.tpkotlin1.helper.AppContextHelper
import com.example.tpkotlin1.helper.AppProgressHelper
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.seconds

data class UserPasswordViewModel(var email : String = "") : ViewModel() {

    fun resetPassword(context: Context)
    {
        viewModelScope.launch {
            AppProgressHelper.Companion.get().show("Resetting Password")

            delay(duration = 1.seconds)

            val request = PasswordRequest(email = email)

            val response = UserService.UserServiceApi.userService.passwordReset(request)

            AppProgressHelper.Companion.get().close()

            if(response.code.equals("200"))
            {
                AppAlertHelper.Companion.get().show("${response.message} le nouveau mots de passe est ${response.data}", onClose = {

                    AppContextHelper.Companion.openActivity(context, MainActivity::class)

                })
            }
            else{
                AppAlertHelper.Companion.get().show(response.message)
            }


        }
    }
}