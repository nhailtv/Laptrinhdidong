package com.example.quanlysach3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
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
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.quanlysach3.ui.theme.QuanLySach3Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            QuanLySach3Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "home") {
                        composable("home") {
                            HomeScreen(navController = navController)
                        }
                        composable("newPage") {
                            NewPageContent(navController = navController)
                        }
                    }
                }
            }
        }
    }

    private val DanhSachSanPham = mutableStateListOf<SanPham>()

    data class SanPham(
        val TenSanPham: String,
        val SoLuongSanPham: String,
    )

    @Composable
    fun HomeScreen(navController: NavHostController) {

        var nhaptensanpham by rememberSaveable {
            mutableStateOf("")
        }

        var nhapsoluongsanpham by rememberSaveable {
            mutableStateOf("")
        }

        var kiemtra by rememberSaveable {
            mutableStateOf(false)
        }

        var tensanpham by rememberSaveable {
            mutableStateOf("")
        }

        var tongsoluongsanpham by rememberSaveable {
            mutableStateOf(0)
        }

        var TenSanPham by rememberSaveable {
            mutableStateOf("")
        }

        var SoLuongSanPham by rememberSaveable {
            mutableStateOf("")
        }




        Column(modifier = Modifier.padding(24.dp)) {
            Image(
                painterResource(R.drawable.gam), contentDescription = "",
                modifier = Modifier.fillMaxWidth()
            )
            CommonSpace()
            Text(
                text = "QUẢN LÝ SẢN PHẨM",
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(),
                fontSize = 30.sp
            )
            CommonSpace()
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Tên SP",
                    modifier = Modifier.weight(1f)
                )
                TextField(
                    value = nhaptensanpham, onValueChange = { nhaptensanpham = it },
                    modifier = Modifier.weight(2f)
                )
            }
            CommonSpace()
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Số lượng",
                    modifier = Modifier.weight(1f)
                )
                TextField(
                    value = nhapsoluongsanpham, onValueChange = { nhapsoluongsanpham = it },
                    modifier = Modifier.weight(2f)
                )
            }
            CommonSpace()
            Row(
                modifier = Modifier.fillMaxWidth(),
                Arrangement.SpaceEvenly
            ) {
                Button(
                    onClick = {
                        if (nhaptensanpham.isEmpty() || nhapsoluongsanpham.isEmpty()) {
                            kiemtra = true
                        } else {
                            kiemtra = false
                            tensanpham = nhaptensanpham
                            TenSanPham = tensanpham
                            SoLuongSanPham = nhapsoluongsanpham
                            DanhSachSanPham.add(SanPham(TenSanPham, SoLuongSanPham))
                            tongsoluongsanpham += nhapsoluongsanpham.toInt()
                            nhapsoluongsanpham = ""
                            nhaptensanpham = ""
                        }
                    },
                    modifier = Modifier.size(150.dp, 50.dp)
                ) {
                    Text(text = "Thêm", fontSize = 30.sp)
                }
                Button(
                    onClick = { navController.navigate("newPage") },
                    modifier = Modifier.size(150.dp, 50.dp)
                ) {
                    Text(text = "Xem", fontSize = 30.sp)
                }
            }
            if (kiemtra) {
                Text(text = "Bạn cần nhập đủ thông tin", color = Color.Red)
            }
            CommonSpace()
            Text(text = "Tên SP vừa nhập: " + tensanpham)
            CommonSpace()
            Text(text = "Tổng số lượng: " + tongsoluongsanpham)
            CommonSpace()
            if (DanhSachSanPham.isNotEmpty()) {
                LazyColumn(
                    modifier = Modifier.fillMaxWidth()
                        .border(BorderStroke(1.dp, color = Color.Black))
                ) {
                    items(DanhSachSanPham.size) { index ->
                        val danhsachsanpham = DanhSachSanPham[index]
                        Text(text = "Tên SP: ${danhsachsanpham.TenSanPham} Số lượng ${danhsachsanpham.SoLuongSanPham}")
                    }
                }
            }
        }
    }

    @Composable
    fun NewPageContent(navController: NavController){

        if (DanhSachSanPham.isNotEmpty()) {
            LazyColumn(
                modifier = Modifier.fillMaxWidth()
                    .border(BorderStroke(1.dp, color = Color.Black))
            ) {
                items(DanhSachSanPham.size) { index ->
                    val danhsachsanpham = DanhSachSanPham[index]
                    Text(text = "Tên SP: ${danhsachsanpham.TenSanPham} Số lượng ${danhsachsanpham.SoLuongSanPham}")
                }
            }
        }
    }

    @Composable
    fun CommonSpace() {
        Spacer(modifier = Modifier.height(20.dp))
    }


    @Preview(showBackground = true)
    @Composable
    fun GreetingPreview() {
        QuanLySach3Theme {

        }
    }
}