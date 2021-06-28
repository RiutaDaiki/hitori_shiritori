package com.riuta.hitorisiritori

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {

    var textFieldValue : String by mutableStateOf("")
    private set

    var lastWord: String by mutableStateOf("")
    private set

    fun updateLastWord(word: String): String{
        lastWord = word
        return word
    }

    fun updateTextField(word: String){
        textFieldValue = word
    }
}