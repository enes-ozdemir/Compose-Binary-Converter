package com.enesozdemir.binaryconverter.util

import android.util.Log
import kotlin.math.pow

class BinaryConverter {

    fun getBinaryToText(binary: String): String {
        try {
            return convertBinaryToDecimal(binary.toLong()).toString()
        } catch (ex: NumberFormatException) {
            Log.i("Binary Converter", "Error$ex")
        }
        return "Not a binary value"
    }


    private fun convertBinaryToDecimal(num: Long): Int {
        var num = num
        var decimalNumber = 0
        var i = 0
        var remainder: Long

        while (num.toInt() != 0) {
            remainder = num % 10
            num /= 10
            decimalNumber += (remainder * 2.0.pow(i.toDouble())).toInt()
            ++i
        }
        return decimalNumber
    }

    fun getTextToBinary(text: String): String {
        return stringToBinary(text)
    }

    private fun stringToBinary(s: String): String {
        val n = s.length
        var bin = ""
        for (i in 0 until n) {
            // convert each char to
            // ASCII value
            var `val` = Integer.valueOf(s[i].toInt())

            // Convert ASCII value to binary
            while (`val` > 0) {
                bin += if (`val` % 2 == 1) {
                    '1'
                } else '0'
                `val` /= 2
            }
            bin = reverse(bin).toString()
        }
        return bin
    }

    private fun reverse(input: String): String? {
        val a = input.toCharArray()
        var l: Int
        var r = 0
        r = a.size - 1
        l = 0
        while (l < r) {

            // Swap values of l and r
            val temp = a[l]
            a[l] = a[r]
            a[r] = temp
            l++
            r--
        }
        return String(a)
    }


}