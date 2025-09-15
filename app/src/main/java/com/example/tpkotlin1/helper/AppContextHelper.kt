package com.example.tpkotlin1.helper

import android.content.Context
import android.content.Intent
import kotlin.reflect.KClass

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
    }

}