package com.example.gocphantu

import android.R
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.VerticalAlignmentLine
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gocphantu.ui.theme.GocPhanTUTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GocPhanTUTheme {
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
fun Greeting(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .background(Color(0xFFEADDFF)).size(300.dp,350.dp).wrapContentSize(Alignment.Center)
            ) {
                Text(text = "Text composable", modifier = Modifier.padding(16.dp),fontWeight = FontWeight.Bold)
                Text(
                    text = "Displays text and follows the recommended Material Design guidelines.",
                    modifier = Modifier.padding(start = 16.dp)
                )
            }



            Column(
                modifier = Modifier
                    .weight(1f)
                    .background(Color(0xFFD0BCFF))
                    .padding(16.dp).size(300.dp,320.dp).wrapContentSize(Alignment.Center)
            ) {
                Text(text = "Image composable",fontWeight = FontWeight.Bold)
                Text(
                    text = "Creates a composable that lays out and draws a given Painter class object.",
                    modifier = Modifier.padding(start = 16.dp)
                )
            }
        }



        Row(

        ) {
            Column(
                modifier = Modifier
                    .weight(1f)

                    .background(Color(0xFFB69DF8)).size(300.dp,400.dp).wrapContentSize(Alignment.Center)
            ) {
                Text(text = "Row composable", modifier = Modifier.padding(16.dp),fontWeight = FontWeight.Bold)
                Text(
                    text = "A layout composable that places its children in a horizontal sequence.",
                    modifier = Modifier.padding(start = 16.dp)
                )
            }
            Column(
                modifier = Modifier
                    .weight(1f)
                    .background(Color(0xFFF6EDFF))
                    .padding(16.dp).size(300.dp,400.dp).wrapContentSize(Alignment.Center)

            ) {
                Text(text = "Column composable", fontWeight = FontWeight.Bold)
                Text(
                    text = "A layout composable that places its children in a vertical sequence.",
                    modifier = Modifier.padding(start = 16.dp)
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    GocPhanTUTheme {
        Greeting()
    }
}