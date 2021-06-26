package com.riuta.hitorisiritori

import android.media.MediaPlayer
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.riuta.hitorisiritori.ui.theme.HITORISIRITORITheme


class MainActivity : ComponentActivity() {
    val correct = R.raw.correct
    val not_hiragana = R.raw.not_hiragana
    val incorrect = R.raw.incorrect
    private val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContent {
            HITORISIRITORITheme {
                Surface(color = MaterialTheme.colors.background) {
                    SimpleFilledTextFieldSample()
                    button()
                    text()
                }
            }
        }

    }

    fun effectSound(is_correct: Int){
        val player = MediaPlayer.create(this, is_correct)
        player.start()
    }

    @Composable
    fun text(){
        Column(
            Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(viewModel.lastWord.value, fontSize = 35.sp)
        }
    }

    @Composable
    fun SimpleFilledTextFieldSample() {
        val query = viewModel.query.value

        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 70.dp),
            horizontalAlignment = Alignment.CenterHorizontally,

                ){
            TextField(
                value = query,
                onValueChange = { newValue ->
                    viewModel.onTextFieldChanged(newValue)
                },
                //keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Ascii)
            )
        }

    }

    @Composable
    fun button(){
        val textFieldValue = viewModel.query.value
        val lastWord = viewModel.lastWord.value
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = {
                    if (textFieldValue.takeLast(1) != "ん" && textFieldValue.length > 1){
                        viewModel.updateLastWord(textFieldValue)
                        viewModel.onTextFieldChanged(lastWord.takeLast(1))
                        effectSound(correct)
                    } else if(textFieldValue.length < 2) effectSound(not_hiragana)
                    else effectSound(incorrect)
                          },

            ){
                Text(text = "Button")
            }
        }

    }

    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        HITORISIRITORITheme {
            SimpleFilledTextFieldSample()
            button()
            text()
        }
    }
}