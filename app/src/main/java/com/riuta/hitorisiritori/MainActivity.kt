package com.riuta.hitorisiritori

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.media.MediaPlayer
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.res.ResourcesCompat
import com.riuta.hitorisiritori.ui.theme.HITORISIRITORITheme
import org.intellij.lang.annotations.JdkConstants


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
                    SimpleFilledTextField()
                    button()
                    text()
//                    image(resource = 1)
                }
            }
        }

    }

    fun effectSound(is_correct: Int){
        val player = MediaPlayer.create(this, is_correct)
        player.start()
        //音鳴らすとき、対応するイラストを表示する
    }

    fun convertToBitmap(resource: Int): Bitmap{
        return BitmapFactory.decodeResource(resources, resource)
    }

    @Composable
    fun text(){
        Column(
            Modifier.fillMaxSize().padding(top = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) { Text(viewModel.lastWord, fontSize = 25.sp) }
    }

    @Composable
    fun SimpleFilledTextField() {
        val query = viewModel.textFieldValue

        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 70.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
                ){
            TextField(
                value = query,
                onValueChange = { viewModel.updateTextField(it) },
            )
        }
    }

    @Composable
    fun image(resource: Int){
        Column(
            Modifier.padding(top = 120.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(convertToBitmap(R.drawable.angry_fukureru_boy).asImageBitmap(), "", Modifier.padding(60.dp))
        }
    }

    @Composable
    fun button(){
        val textFieldValue = viewModel.textFieldValue
        val lastWord = viewModel.lastWord
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(top = 250.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Button(
                onClick = {
                    if(textFieldValue.takeLast(1) != "ん" && textFieldValue.length > 1){
                        viewModel.updateTextField(viewModel.updateLastWord(textFieldValue).takeLast(1))
                        effectSound(correct)
                    } else if(textFieldValue.length < 2){
                        effectSound(not_hiragana)
                    }
                    else effectSound(incorrect)
                          },
                Modifier
                    .width(160.dp)
                    .padding(8.dp),
                shape = RoundedCornerShape(20.dp),


            ){
                Text(text = "Button", fontSize = 20.sp)
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        HITORISIRITORITheme {
            SimpleFilledTextField()
            button()
            text()
        }
    }
}