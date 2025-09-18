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
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tpkotlin1.ViewModel.UserViewModel
import com.example.tpkotlin1.helper.AppContextHelper
import kotlinx.coroutines.flow.MutableStateFlow


class MainActivity : ComponentActivity() {
    lateinit var userView : MutableStateFlow<UserViewModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        userView = MutableStateFlow(UserViewModel(email = "isaac@gmail.com", password = "password"))

        setContent {
            Mainpage(userView)
        }
    }
}

@Composable
fun Mainpage(userModel: MutableStateFlow<UserViewModel>){
    val userState by userModel.collectAsState()

    val context = LocalContext.current
    Template {
        Column(modifier = Modifier.padding(60.dp).padding(top = 50.dp)) {
            Image(
                painter = painterResource(R.drawable.logo_eni_round)
                ,contentDescription = "Logo"
                ,modifier = Modifier.fillMaxWidth().padding(bottom = 20.dp)
            )

            EniTitle(stringResource(R.string.title_login))
            EniTextField(
                stringResource(R.string.label_mail),
                value = userState.email,
                onValueChange = {
                    newMail -> userModel.value = userModel.value.copy(email = newMail)
                }
                )
            EniTextField(
                stringResource(R.string.label_password),
                value = userState.password,
                onValueChange = {
                        newMail -> userModel.value = userModel.value.copy(password = newMail)
                }
                )
            WrapPadding{
                EniClickText(stringResource(R.string.label_forgot_pass), onClick = {AppContextHelper.openActivity(context, ForgottenForm::class)})
            }
            WrapPadding {
                EniClickText(stringResource(R.string.label_not_sub_yet), onClick = {AppContextHelper.openActivity(context, LoginForm::class)} )
            }
            EniButtonClick(stringResource(R.string.btn_login), onClick = {
                    userState.login(onLoginSucces = {
                        AppContextHelper.openActivity(context, ListArticle::class)
                    })
                })
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Mainpage(userModel = MutableStateFlow(UserViewModel()))
}