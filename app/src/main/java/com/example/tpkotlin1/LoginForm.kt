package com.example.tpkotlin1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
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
        Column(modifier = Modifier.padding(60.dp).padding(top = 250.dp)) {
            Text("Sign In", color = Color(0xFFeeeee4))
            TextField(value = "", onValueChange = {}, label = {Text("Pseudo", color = Color(0xFFeeeee4))}, colors = TextFieldDefaults.colors( unfocusedContainerColor = Color.Transparent))
            TextField(value = "", onValueChange = {},  label = {Text("Email", color = Color(0xFFeeeee4))},colors = TextFieldDefaults.colors( unfocusedContainerColor = Color.Transparent))
            TextField(value = "", onValueChange = {},  label = {Text("Password", color = Color(0xFFeeeee4))},colors = TextFieldDefaults.colors( unfocusedContainerColor = Color.Transparent))
            TextField(value = "", onValueChange = {},  label = {Text("Password Confirmation", color = Color(0xFFeeeee4))},colors = TextFieldDefaults.colors( unfocusedContainerColor = Color.Transparent))
            TextField(value = "", onValueChange = {},  label = {Text("City Code", color = Color(0xFFeeeee4))},colors = TextFieldDefaults.colors( unfocusedContainerColor = Color.Transparent))
            TextField(value = "", onValueChange = {},  label = {Text("City", color = Color(0xFFeeeee4))},colors = TextFieldDefaults.colors( unfocusedContainerColor = Color.Transparent))
            TextField(value = "", onValueChange = {},  label = {Text("Phone Number", color = Color(0xFFeeeee4))},colors = TextFieldDefaults.colors( unfocusedContainerColor = Color.Transparent))
            Button(
                onClick = {},
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth()
                    .background(shape = ButtonDefaults.shape,brush = Brush.horizontalGradient(listOf(Color(0xFF2596be), Color(0xFF154c79))))
                , colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
            ) {
                Text("sign in")
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun LoginPreview() {
    LoginPage()
}