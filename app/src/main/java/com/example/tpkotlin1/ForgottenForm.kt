package com.example.tpkotlin1

import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tpkotlin1.ViewModel.UserPasswordViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class ForgottenForm : ComponentActivity() {

    lateinit var passwordView : MutableStateFlow<UserPasswordViewModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        passwordView = MutableStateFlow(UserPasswordViewModel(_application = application))

        setContent {
            ForgottenPage(passwordView)
        }
    }
}

@Composable
fun ForgottenPage(passwordModel : MutableStateFlow<UserPasswordViewModel>){

    val passwordState by passwordModel.collectAsState()
    val context = LocalContext.current

    Template {
        Column(modifier = Modifier.padding(60.dp).padding(top = 50.dp)) {
            Image(
                painter = painterResource(R.drawable.logo_eni_round)
                ,contentDescription = "Logo"
                ,modifier = Modifier.fillMaxWidth().padding(bottom = 20.dp)
            )
            EniTitle("Récupération de Mots de Passe")
            EniTextField("Email",
                value = passwordState.email,
                onValueChange = {
                    newMail -> passwordModel.value = passwordModel.value.copy(email = newMail)
                }
                )
            EniButtonClick("Envoyer le Lien", onClick = {passwordState.resetPassword(context)})
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ForgottenPreview() {
    ForgottenPage(passwordModel = MutableStateFlow((UserPasswordViewModel(_application =  Application()))))
}