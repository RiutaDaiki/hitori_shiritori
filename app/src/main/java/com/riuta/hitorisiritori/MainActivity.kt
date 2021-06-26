package com.riuta.hitorisiritori

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import com.riuta.hitorisiritori.ui.theme.HITORISIRITORITheme

class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HITORISIRITORITheme {
                Surface(color = MaterialTheme.colors.background) {
                    SimpleFilledTextFieldSample()
                }
            }
        }
    }


    @Composable
    fun SimpleFilledTextFieldSample() {
        val query = viewModel.query.value

            Column {
                TextField(value = query,
                    onValueChange = { newValue ->
                        viewModel.onQueryChanged(newValue)
                    },
//                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Ascii)
                )
            }
    }

    @Composable
    fun button(){
        Button(
            onClick = {},

        )
    }

    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        HITORISIRITORITheme {
            SimpleFilledTextFieldSample()
        }
    }
}