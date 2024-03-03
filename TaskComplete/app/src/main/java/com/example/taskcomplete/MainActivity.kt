package com.example.taskcomplete

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.taskcomplete.ui.theme.TaskCompleteTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TaskCompleteTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting()
                }
            }
        }
    }
}

@Composable
fun Greeting() {
    val image = painterResource(id = R.drawable.ic_task_completed)

    Box(
        modifier = Modifier
            .fillMaxSize() // Fill entire screen
            .background(Color.White) // Optional background color
            .padding(16.dp) // Add padding around content
        ,

        contentAlignment = Alignment.Center // Center content within the box
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(), // Occupy full width
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(

                painter = image,
                contentDescription = "Task completed",
                modifier = Modifier
                    .size(300.dp, 300.dp)
            )

            Text(
                text = "Task completed",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                modifier = Modifier.padding(top = 8.dp) // Add space above text
            )
            Text(
                text = "Nice work!",
                modifier = Modifier.padding(top = 8.dp) // Add space above text
            )
        }
    }}
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TaskCompleteTheme {
        Greeting()
    }
}