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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tpkotlin1.Service.SubscribeRequest
import com.example.tpkotlin1.ViewModel.ArticleViewModel
import com.example.tpkotlin1.ViewModel.UserSubscribeViewModel
import com.example.tpkotlin1.helper.AppContextHelper
import kotlinx.coroutines.flow.MutableStateFlow

class LoginForm : ComponentActivity() {
    lateinit var subscribeview : MutableStateFlow<UserSubscribeViewModel>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        subscribeview = MutableStateFlow(UserSubscribeViewModel(_application = application))
        setContent {
            LoginPage(subscribeview)
        }
    }
}

fun updateSubscribeField(
    viewModel: MutableStateFlow<UserSubscribeViewModel>,
    update : (SubscribeRequest) -> SubscribeRequest
)
{
    val copy = update(viewModel.value.subscribeRequest)
    viewModel.value = viewModel.value.copy(
        subscribeRequest =  copy
    )
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

            EniTitle(stringResource(R.string.title_login))
            EniTextField(stringResource(R.string.label_pseudo),
                value = subscribeState.subscribeRequest.pseudo,
                onValueChange = {
                        value -> updateSubscribeField(subscribeModel, update = {it -> it.copy(pseudo = value)})
                }
                )
            EniTextField(stringResource(R.string.label_mail),
                value = subscribeState.subscribeRequest.email,
                onValueChange = {
                        value -> updateSubscribeField(subscribeModel, update = {it -> it.copy(email = value)})
                }
            )
            EniTextField(stringResource(R.string.label_password),
                value = subscribeState.subscribeRequest.password,
                onValueChange = {
                        value -> updateSubscribeField(subscribeModel, update = {it -> it.copy(password = value)})
                }
            )
            EniTextField(stringResource(R.string.label_password_confirm),
                value = subscribeState.subscribeRequest.passwordConfirm,
                onValueChange = {
                        value -> updateSubscribeField(subscribeModel, update = {it -> it.copy(passwordConfirm = value)})
                }
            )
            EniTextField(stringResource(R.string.label_city_code),
                value = subscribeState.subscribeRequest.cityCode,
                onValueChange = {
                        value -> updateSubscribeField(subscribeModel, update = {it -> it.copy(cityCode = value)})
                }
            )
            EniTextField(stringResource(R.string.label_city),
                value = subscribeState.subscribeRequest.city,
                onValueChange = {
                        value -> updateSubscribeField(subscribeModel, update = {it -> it.copy(city = value)})
                }
            )
            EniTextField(stringResource(R.string.label_phone),
                value = subscribeState.subscribeRequest.phone,
                onValueChange = {
                        value -> updateSubscribeField(subscribeModel, update = {it -> it.copy(phone = value)})
                }
            )
            EniButtonClick(stringResource(R.string.btn_login), onClick = {
                subscribeState.subscribe(onSubscribeRequestSuccess = {
                    AppContextHelper.openActivity(context, MainActivity::class)
                })
            })
        }
    }
}


@Preview(showBackground = true)
@Composable
fun LoginPreview() {
    LoginPage(subscribeModel = MutableStateFlow(UserSubscribeViewModel(_application = Application())))
}