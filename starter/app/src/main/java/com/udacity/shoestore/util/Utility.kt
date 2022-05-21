package com.udacity.shoestore.util

import android.text.TextUtils
import androidx.databinding.InverseMethod
import java.lang.String.format
import java.text.DecimalFormat
import java.text.MessageFormat.format

class DataBindingConverter {
    companion object {
        @InverseMethod("convertDoubleToString")
        @JvmStatic
        fun convertStringToDouble(value: String) :  Double{
            fun String.fullTrim() = trim().replace("\uFEFF", "")

            val number = value.fullTrim().toDouble()
            return number
        }

        @JvmStatic
        fun convertDoubleToString(value: Double?) :  String{
            return value.toString()
        }



    }

}