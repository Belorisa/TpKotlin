package com.example.tpkotlin1.helper

import android.content.Context
import android.content.Intent
import com.example.tpkotlin1.API.ApiResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.reflect.KClass
import kotlin.time.Duration.Companion.seconds

class AppContextHelper {

    // Tout ce qui est à l'intérieur est statique
    companion object {

        /**
         * activityClass: Typage en parametre (ex: MainActivity::class)
         */
        fun openActivity(context: Context, activityClass: KClass<*>){
            val intent = Intent(context, activityClass.java)
            context.startActivity(intent)
        }

        fun openActivityWithString(context: Context, activityClass: KClass<*>,key: String,value: String)
        {
            val intent = Intent(context,activityClass.java)

            intent.putExtra(key,value)

            context.startActivity(intent)
        }

    }
    }

    fun <T> commonCallApi(loadingMsg: String = "Chargement",coroutineScope: CoroutineScope,doAction: suspend () -> ApiResponse<T>)
    {
        AppProgressHelper.get().show(loadingMsg)

        coroutineScope.launch {
            delay(duration = 2.seconds)

            val apiResponse = doAction()

            AppProgressHelper.get().close()

            AppAlertHelper.get().show(apiResponse.message)
        }
    }
