package com.example.mainactivity22it076

import android.os.Bundle
import android.provider.MediaStore.Audio.Radio
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
import androidx.compose.material3.RadioButton
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
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mainactivity22it076.ui.theme.MainActivity22IT076Theme
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainActivity22IT076Theme {
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
}
private val DanhSachXe = mutableStateListOf<Xe>()

data class Xe(
    val DataTenXe: String,
    val DataMauXe: String,
)

@Composable
fun Spacing(){
    Spacer(modifier = Modifier.height(20.dp))
}


@Composable
fun HomeScreen(navController: NavHostController) {

    var nhaptenxe by remember {
        mutableStateOf("")
    }
    var nhapmauxe by remember {
        mutableStateOf("")
    }

    var TenXe by remember {
        mutableStateOf("")
    }

    var MauXe by remember {
        mutableStateOf("")
    }

    var Xanh by remember {
        mutableStateOf(false)
    }
    var Do by remember {
        mutableStateOf(false)
    }
    var Trang by remember {
        mutableStateOf(false)
    }

    var Error by remember {
        mutableStateOf("")
    }

    Column(modifier = Modifier.padding(24.dp)) {
        Text(text = "Chọn Xe Oto",
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            fontSize = 30.sp)

        Spacing()

        Row(modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically) {
            Text(text = "Tên Xe", modifier = Modifier.weight(1f))
            TextField(value = nhaptenxe, onValueChange = { nhaptenxe = it },
                modifier = Modifier.weight(2f))
        }

        Spacing()

        Row (modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically){
            RadioButton(selected = Xanh, onClick = {
                nhapmauxe ="Màu Xanh";
                Xanh=!Xanh;
                Do=false;
                Trang=false


                                                   }, enabled = true)
            Text(text = "Màu Xanh")
        }
        Row (modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically){
            RadioButton(selected = Do, onClick = {
                nhapmauxe  ="Màu Đỏ"
                Xanh=false;
                Do=!Do;
                Trang=false
                                                    },enabled = true)
            Text(text = "Màu Đỏ")
        }
        Row (modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically){
            RadioButton(selected = Trang, onClick = {
                nhapmauxe  ="Màu Trắng"
                Xanh=false;
                Do=false;
                Trang=!Trang

                                                    },enabled = true)
            Text(text = "Màu Trắng")
        }

        if (!Xanh && !Do && !Trang){
            nhapmauxe = ""
        }

        Spacing()

        Button(onClick = {

            if (nhaptenxe.isBlank() || nhapmauxe.isBlank()) {
                Error = "Tên xe hoặc màu xe không được để trống!"
            } else {
                Error = "Thanh Cong"
                TenXe=nhaptenxe
                MauXe=nhapmauxe
                DanhSachXe.add(Xe(DataTenXe = TenXe,DataMauXe= MauXe))
                TenXe=""
                MauXe=""


            }
        },
            modifier = Modifier.size(150.dp,50.dp)) {
            Text(text = "Xem", fontSize = 25.sp)
        }

        Spacing()

        Button(
            onClick = { navController.navigate("newPage") },
            modifier = Modifier.size(200.dp, 50.dp)
        ) {
            Text(text = "ManHinh2", fontSize = 25.sp)
        }

        if (Error.isNotBlank()) {
            Text(
                text = Error,
                color = Color.Red,
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }




    }
}

@Composable
fun NewPageContent(navController: NavController){
    Column {


    Text(text = "Thông Tin Xe")
    Spacing()
    if (DanhSachXe.isNotEmpty()) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .border(BorderStroke(1.dp, color = Color.Black))
        ) {
            items(DanhSachXe.size) { index ->
                val danhsachXe = DanhSachXe[index]
                Text(text = "Tên SP: ${danhsachXe.DataTenXe} ,Màu: ${danhsachXe.DataMauXe}")
                if (danhsachXe.DataMauXe == "Màu Trắng"){
                    Image(painterResource(id = R.drawable.xetrang), contentDescription = "")
                }else if(danhsachXe.DataMauXe == "Màu Đỏ"){
                    Image(painterResource(id = R.drawable.xedo), contentDescription = "")
                }else if(danhsachXe.DataMauXe == "Màu Xanh"){
                    Image(painterResource(id = R.drawable.xexanh), contentDescription ="" )
                }
            }

        }
    }
        Button(onClick = { navController.navigateUp() }) {
            Text(text = "Back")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Main() {
    MainActivity22IT076Theme {

    }
}