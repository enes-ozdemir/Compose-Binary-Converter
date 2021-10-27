package com.enesozdemir.binaryconverter

import androidx.compose.runtime.Composable

typealias ComposableFun = @Composable () -> Unit

sealed class TabItem(var title: String, var screen: ComposableFun) {
    object BinaryToText : TabItem( "Binary to Text", { BinaryScreen() })
    object TextToBinary : TabItem( "Text to Binary", { TextScreen() })

}