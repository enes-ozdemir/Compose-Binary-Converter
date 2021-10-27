package com.enesozdemir.binaryconverter.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.enesozdemir.binaryconverter.presentation.theme.BackgroundColor
import com.enesozdemir.binaryconverter.util.BinaryConverter
import com.enesozdemir.binaryconverter.util.Constant

@Preview(showBackground = true)
@Composable
fun Preview() {
    BinaryScreen()
}

@Composable
fun BinaryScreen() {
    var text by rememberSaveable { mutableStateOf("") }
    var inputText by rememberSaveable { mutableStateOf("") }
    Column(
        modifier = Modifier
            .background(
                Color.White
            )
    ) {
        TextField(
            value = text,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentSize(),
            textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            onValueChange = { it ->
                text = it.filter { it == '0' || it == '1' }
            },
            label = { Text(Constant.Enter_Binary) },
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentSize()
                .padding(10.dp),
        ) {
            Button(
                colors = ButtonDefaults.buttonColors(backgroundColor = BackgroundColor),
                onClick = {
                    inputText = BinaryConverter().getBinaryToText(text)
                }) {
                Text(text = Constant.Convert, color = Color.White)
            }
        }
        Text(
            text = inputText,
            fontWeight = FontWeight.Light,
            color = Color.Black,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentSize(),
            textAlign = TextAlign.Center,
            fontSize = 25.sp
        )
    }
}
