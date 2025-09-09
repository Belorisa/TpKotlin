package com.example.tpkotlin1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Mainpage()
        }
    }
}

@Composable
fun Mainpage(){
    Template {
        Column(modifier = Modifier.padding(60.dp).padding(top = 50.dp)) {
            Image(
                painter = painterResource(R.drawable.logo_eni_round)
                ,contentDescription = "Logo"
                ,modifier = Modifier.fillMaxWidth().padding(bottom = 20.dp)
            )

            EniTitle("Login")
            EniTextField("Email")
            EniTextField("Password")
            WrapPadding{
                Text("Coucou j'ai oublié mon mots de passe !", color = Color(0xFFeeeee4), modifier = Modifier)
            }
            EniButton("Connexion")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Mainpage()
}