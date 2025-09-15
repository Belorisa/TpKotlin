package com.example.tpkotlin1.helper

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import kotlinx.coroutines.flow.MutableStateFlow


data class AlertDialogueModelData(var isShow : Boolean = false, var message :String = "") {

}

class AppAlertHelper {


    companion object {
        val instance : AppAlertHelper by lazy { AppAlertHelper() }

        fun get() : AppAlertHelper {
            return instance;
        }
    }

    // Sert à savoir en temps réel si il faut afficher ou pas la dialog
    var alertsDialogueModelData = MutableStateFlow(AlertDialogueModelData());

    fun show(message: String, onClose: () -> Unit){
        alertsDialogueModelData.value = alertsDialogueModelData.value.copy(isShow = true, message = message,onClose = onClose)

    }

    fun close(){
        alertsDialogueModelData.value = alertsDialogueModelData.value.copy(isShow = false)


    }
}




@Composable
fun AlertDialog()
{
    val modelData by AppAlertHelper.get().alertsDialogueModelData.collectAsState()

    if(modelData.isShow) {
        Dialog(onDismissRequest = {AppAlertHelper.get().close()}) {
            Box(
                modifier = Modifier.background(
                    color = Color(0xFFFFFFFF),
                    shape = RoundedCornerShape(30.dp)
                )
                    .padding(20.dp)
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = modelData.message)
                }
            }
        }
    }
}