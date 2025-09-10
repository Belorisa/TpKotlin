package com.example.tpkotlin1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class LoginForm : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LoginPage()
        }
    }
}

@Composable
fun LoginPage(){
    Template {
        Column(modifier = Modifier.padding(60.dp).padding(top = 50.dp)) {

            Image(
                painter = painterResource(R.drawable.logo_eni_round)
                ,contentDescription = "Logo"
                ,modifier = Modifier.fillMaxWidth().padding(bottom = 20.dp)
            )

            EniTitle("Sign in")
            EniTextField("Pseudo")
            EniTextField("Email")
            EniTextField("Password")
            EniTextField("Password Confirmation")
            EniTextField("Code Postal")
            EniTextField("Ville")
            EniTextField("Telephone")
            EniButton("Sign In",context = LocalContext.current,target = ListArticle::class)
        }
    }
}


@Preview(showBackground = true)
@Composable
fun LoginPreview() {
    LoginPage()
}