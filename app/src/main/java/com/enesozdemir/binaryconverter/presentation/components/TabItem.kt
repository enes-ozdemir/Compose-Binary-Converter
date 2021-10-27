package com.enesozdemir.binaryconverter.presentation.components

import androidx.compose.runtime.Composable
import com.enesozdemir.binaryconverter.presentation.ui.BinaryScreen
import com.enesozdemir.binaryconverter.presentation.ui.TextScreen
import com.enesozdemir.binaryconverter.util.Constant

typealias ComposableFun = @Composable () -> Unit

sealed class TabItem(var title: String, var screen: ComposableFun) {
    object BinaryToDecimal : TabItem(Constant.BinaryToDecimal, { BinaryScreen() })
    object TextToBinary : TabItem(Constant.TextToBinary, { TextScreen() })

}