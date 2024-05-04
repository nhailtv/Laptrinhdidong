package com.example.quanlysach

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.quanlysach.ui.theme.QuanLySachTheme
import androidx.compose.foundation.verticalScroll
import androidx.compose.ui.text.input.KeyboardType


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            QuanLySachTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                }
            }
        }
    }
}

data class Sach(
    val tenquyensach: String,
    val soluongquyensach: String,
)

@Composable
fun HomeScreen(){

    var nhaptensach by remember {
        mutableStateOf("")
    }

    var tensach by remember {
        mutableStateOf("")
    }

    var nhapsoluong by remember {
        mutableStateOf("")
    }

    var tongsoluong by remember {
        mutableStateOf(0)
    }
    
    var  kiemtra by remember {
        mutableStateOf(false)
    }

    var danhsachloaisach by remember {
        mutableStateOf(mutableStateListOf<Sach>())
    }

    var tenquyensach by remember {
        mutableStateOf("")
    }

    var soluongquyensach by remember {
        mutableStateOf("")
    }
    var showList by remember {
        mutableStateOf(false)
    }

    Column(modifier = Modifier.padding(24.dp)) {
        Image(painterResource(R.drawable.gam), contentDescription = null,
            modifier = Modifier.fillMaxWidth())
        CommonSpace()
        Text(text = "QUẢN LÝ SÁCH",
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            fontSize = 30.sp)
        CommonSpace()
        Row(modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically) {
            Text(text = "Tên sách", modifier = Modifier.weight(1f))
            TextField(value = nhaptensach, onValueChange = { nhaptensach = it },
                modifier = Modifier.weight(2f))
        }
        CommonSpace()
        Row(modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically) {
            Text(text = "Số lượng", modifier = Modifier.weight(1f))
            TextField(value = nhapsoluong, onValueChange = { nhapsoluong = it},
                modifier = Modifier.weight(2f),
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number))
        }
        CommonSpace()
        Text(text = "Tên sách vừa nhập: "+tensach)
        CommonSpace()
        Text(text = "Tổng số lượng: "+tongsoluong)
        CommonSpace()
        Row (modifier = Modifier.fillMaxWidth(),
            Arrangement.SpaceEvenly){
            Button(
                onClick = {
                    if (nhaptensach.isEmpty() || nhapsoluong.isEmpty()) {
                        kiemtra = true
                    } else {
                        kiemtra = false
                        tenquyensach = nhaptensach
                        soluongquyensach = nhapsoluong

                        // Check if the book already exists in the list
                        val existingBookIndex = danhsachloaisach.indexOfFirst { it.tenquyensach == tenquyensach }

                        if (existingBookIndex != -1) {
                            // Book already exists, update the quantity
                            danhsachloaisach[existingBookIndex] =
                                Sach(tenquyensach, (danhsachloaisach[existingBookIndex].soluongquyensach.toInt() + soluongquyensach.toInt()).toString())
                        } else {
                            // Book doesn't exist, add a new entry
                            danhsachloaisach.add(Sach(tenquyensach, soluongquyensach))
                        }

                        tensach = nhaptensach
                        tongsoluong += nhapsoluong.toInt()
                        nhapsoluong = ""
                        nhaptensach = ""
                    }
                },
                modifier = Modifier.size(150.dp, 50.dp)
            ) {
                Text(text = "Thêm", fontSize = 25.sp)
            }
            Button(onClick = {
                showList = !showList
            },
                modifier = Modifier.size(150.dp,50.dp)) {
                Text(text = "Xem", fontSize = 25.sp)
            }
        }
        CommonSpace()
        if(kiemtra){
            Text(text = "Bạn cần nhập đủ thông tin", color = Color.Red)
        }
        CommonSpace()
        if(showList && danhsachloaisach.isNotEmpty()) {
            LazyColumn() {
                items(danhsachloaisach.size){
                    index ->
                    val danhsachsach = danhsachloaisach[index]
                    Text(text = "Tên sách: ${danhsachsach.tenquyensach} Số lượng quyển sách: ${danhsachsach.soluongquyensach}")
                }
            }
        }
    }
}

@Composable
fun CommonSpace(){
    Spacer(modifier = Modifier.height(20.dp))
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    QuanLySachTheme {
        HomeScreen()
    }
}