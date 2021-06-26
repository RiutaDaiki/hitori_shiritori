package com.riuta.hitorisiritori

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {
    val query = mutableStateOf("キョン")

    fun onQueryChanged(query: String){
        this.query.value = query
    }
}