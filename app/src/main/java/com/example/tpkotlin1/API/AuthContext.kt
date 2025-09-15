package com.example.tpkotlin1.API

    class AuthContext(var token: String = "") {
    // Singleton
        companion object {
         val instance : AuthContext by lazy { AuthContext() }

            fun get() : AuthContext {
              return instance
            }
        }

        fun setAuthToken(newToken : String)
        {
            println("stocker le token en cache : ${token}")
        }
    }
