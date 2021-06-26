package com.riuta.hitorisiritori

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {
    val query = mutableStateOf("")

    val lastWord = mutableStateOf("")

    fun onTextFieldChanged(query: String){
        this.query.value = query
    }

    fun updateLastWord(query: String){
//        if (query.takeLast(1) != "ん") lastWord.value = query
        lastWord.value = query
    }
}