package com.licoding.oceanpulse.presentation.register.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.licoding.oceanpulse.presentation.register.RegisterUIEvent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterPage(
    onEvent: (RegisterUIEvent) -> Unit,
    navController: NavController
) {
    var email by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }
    var setIsPasswordVisible by remember {
        mutableStateOf(false)
    }
    var name by remember {
        mutableStateOf("")
    }
    val registerAnnotatedString = returnbuildAnnotatedString()

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Text(
            "Create an Account",
            fontSize = 30.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        )
        Divider(thickness = 1.dp, color = Color.LightGray, modifier = Modifier.fillMaxWidth())
        Column(
            modifier = Modifier
                .padding(16.dp)
                .weight(1f)
        ) {
            Text(
                text = "Sign up",
                fontSize = 20.sp,
                modifier = Modifier
                    .fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                "Sign up to start sharing memories",
                fontSize = 15.sp,
                modifier = Modifier
                    .fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(20.dp))
            OutlinedTextField(
                value = email,
                onValueChange = {
                    email = it
                    onEvent(RegisterUIEvent.OnRegisterEmailChange(email))
                },
                placeholder = {
                    Text(
                        text = "Email",
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = name,
                onValueChange = {
                    name = it
                    onEvent(RegisterUIEvent.OnNameChange(name))
                },
                placeholder = {
                    Text(
                        text = "Name",
                    )
                },
                keyboardOptions = KeyboardOptions(
                    KeyboardCapitalization.Words
                ),
                modifier = Modifier
                    .fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = password,
                onValueChange = {
                    password = it
                    onEvent(RegisterUIEvent.OnRegisterPasswordChange(password))
                },
                placeholder = {
                    Text(
                        text = "Password",
                    )
                },
                modifier = Modifier
                    .fillMaxWidth(),
                trailingIcon = {
                    IconButton(
                        onClick = {
                            setIsPasswordVisible = !setIsPasswordVisible
                        }
                    ) {
                        Icon(
                            imageVector = if (setIsPasswordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                            contentDescription = null
                        )
                    }
                },
                visualTransformation = if(setIsPasswordVisible) VisualTransformation.None else PasswordVisualTransformation()
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    onEvent(RegisterUIEvent.OnRegisterButtonClicked)
                },
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text("Register")
            }
            Spacer(modifier = Modifier.height(20.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Divider(
                    modifier = Modifier
                        .weight(1f),
                    thickness = 1.dp,
                    color = Color.LightGray
                )
                Text(
                    "Or continue with",
                    modifier = Modifier.padding(horizontal = 5.dp)
                )
                Divider(
                    modifier = Modifier
                        .weight(1f),
                    thickness = 1.dp,
                    color = Color.LightGray
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
            Button(
                onClick = {

                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Filled.Home,
                        contentDescription = null
                    )
                    Spacer(modifier = Modifier.width(20.dp))
                    Text(
                        text = "Sign up with Google"
                    )
                }
            }
            Spacer(modifier = Modifier.height(20.dp))
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            ClickableText(
                text = registerAnnotatedString,
                onClick = { offset ->
                    val annotation = registerAnnotatedString.getStringAnnotations("ClickableText", offset, offset)
                    if (annotation.isNotEmpty()) {
                        navController.navigate("register")
                    }
                },
                modifier = Modifier
                    .padding(bottom = 10.dp),
            )
        }
    }
}

fun returnbuildAnnotatedString() : AnnotatedString {
    val registerAnnotatedString =  buildAnnotatedString {
        withStyle(style = SpanStyle(color = Color.Gray)){
            append("Already have an account? ")
        }
        withStyle(style = SpanStyle(color = Color.White)) {
            append("Sign in.")
            addStringAnnotation(
                tag = "ClickableText",
                annotation = "ClickAction",
                start = 23,
                end = 30
            )
        }
    }
    return registerAnnotatedString
}