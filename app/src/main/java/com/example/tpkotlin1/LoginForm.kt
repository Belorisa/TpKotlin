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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tpkotlin1.ViewModel.UserSubscribeViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class LoginForm : ComponentActivity() {
    lateinit var subscribeview : MutableStateFlow<UserSubscribeViewModel>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        subscribeview = MutableStateFlow(UserSubscribeViewModel())
        setContent {
            LoginPage(subscribeview)
        }
    }
}

@Composable
fun LoginPage(subscribeModel : MutableStateFlow<UserSubscribeViewModel>){

    val subscribeState by subscribeModel.collectAsState()

    val context = LocalContext.current

    Template {
        Column(modifier = Modifier.padding(60.dp).padding(top = 50.dp)) {

            Image(
                painter = painterResource(R.drawable.logo_eni_round)
                ,contentDescription = "Logo"
                ,modifier = Modifier.fillMaxWidth().padding(bottom = 20.dp)
            )

            EniTitle("Sign in")
            EniTextField("Pseudo",
                value = subscribeState.pseudo,
                onValueChange = {
                    newPseudo -> subscribeModel.value = subscribeModel.value.copy(pseudo = newPseudo)
                }
                )
            EniTextField("Email",
                value = subscribeState.email,
                onValueChange = {
                        newEmail-> subscribeModel.value = subscribeModel.value.copy(email = newEmail)
                }
            )
            EniTextField("Password",
                value = subscribeState.password,
                onValueChange = {
                        newPassword -> subscribeModel.value = subscribeModel.value.copy(password = newPassword)
                }
            )
            EniTextField("Password Confirmation",
                value = subscribeState.passwordConfirm,
                onValueChange = {
                        newPasswordC -> subscribeModel.value = subscribeModel.value.copy(passwordConfirm = newPasswordC)
                }
            )
            EniTextField("Code Postal",
                value = subscribeState.cityCode,
                onValueChange = {
                        newCityCode -> subscribeModel.value = subscribeModel.value.copy(cityCode = newCityCode)
                }
            )
            EniTextField("Ville",
                value = subscribeState.city,
                onValueChange = {
                        newCity -> subscribeModel.value = subscribeModel.value.copy(city = newCity)
                }
            )
            EniTextField("Telephone",
                value = subscribeState.phone,
                onValueChange = {
                        newPhone -> subscribeModel.value = subscribeModel.value.copy(phone = newPhone)
                }
            )
            EniButtonLogin("Connexion", onClick = {subscribeState.subscribe(context)
            })
        }
    }
}


@Preview(showBackground = true)
@Composable
fun LoginPreview() {
    LoginPage(subscribeModel = MutableStateFlow(UserSubscribeViewModel()))
}