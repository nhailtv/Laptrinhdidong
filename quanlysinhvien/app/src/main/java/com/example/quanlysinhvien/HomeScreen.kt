package com.example.quanlysinhvien

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
import androidx.compose.foundation.layout.width
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
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.quanlysinhvien.ui.theme.QuanlysinhvienTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            QuanlysinhvienTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    QuanLySinhVien()
                    }
                }
            }
        }
    }


fun createEmail(msv: String, hovaten: String): String {
    val msvParts = msv.split(Regex("\\D+")) // Tách phần không phải số từ mã sinh viên
    val msvNumber = msv.filter { it.isDigit() } // Lấy phần số từ mã sinh viên
    val hovatenParts = hovaten.trim().split("\\s+".toRegex()) // Tách phần từ của họ và tên
    val hovatenInitials = hovatenParts.joinToString("") { it.firstOrNull()?.toString() ?: "" } // Lấy chữ cái đầu tiên của từ và ghép lại
    return "${hovatenInitials.toLowerCase()}${msvNumber}@vku.udn.vn" // Tạo địa chỉ email
}

data class Student(
    val msv: String,
    val hovaten: String,
    val email: String
)




@Composable
fun QuanLySinhVien() {

    var msv by remember {
        mutableStateOf("")
    }

    var hovaten by remember {
        mutableStateOf("")
    }

    var kiemtra by remember {
        mutableStateOf(false)
    }

    var email by remember {
        mutableStateOf("")
    }

    var students by remember {
        mutableStateOf(mutableStateListOf<Student>())
    }


    Column(modifier = Modifier.padding(24.dp)) {
        Row(modifier = Modifier.padding(24.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,){
            Text(text = "Quan ly sinh vien", modifier = Modifier.weight(2f))
            Image(painterResource(R.drawable.gam), contentDescription = null, modifier = Modifier.weight(2f))
        }
        CommonSpace()
        Text(text = "MSV", modifier = Modifier.align(Alignment.CenterHorizontally))
        CommonSpace()
        TextField(value = msv, onValueChange = { msv = it }, modifier = Modifier.fillMaxWidth())
        CommonSpace()
        Text(text = "Ho va ten", modifier = Modifier.align(Alignment.CenterHorizontally))
        CommonSpace()
        TextField(value = hovaten, onValueChange = { hovaten = it}, modifier = Modifier.fillMaxWidth())
        Row(modifier = Modifier.padding(24.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,) {
            Button(onClick = {
                if(msv.isEmpty() || hovaten.isEmpty()){
                    kiemtra = true
                } else {
                    kiemtra = false
                    email = createEmail(msv, hovaten)
                    students.add(Student(msv,hovaten,email))
                    msv = ""
                    hovaten = ""

                }
            }, modifier = Modifier.size(125.dp,50.dp)) {
                Text(text = "Tao email")
            }
            Spacer(modifier = Modifier.width(40.dp))
            Button(onClick = {  }, modifier = Modifier.size(125.dp,50.dp)) {
                Text(text = "xem")
            }
        }
        if (kiemtra){
            Text(text = "CAN NHAP DU THONG TIN",
                modifier = Modifier.padding(top = 8.dp),
                color = Color.Red)
        }
        CommonSpace()
        Text(text = "Email vua nhap " + email)
        CommonSpace()

        if (students.isNotEmpty()) {
            LazyColumn(){
                items(students.size) { index ->
                    val student = students[index]
                    Text(text = "MSV: ${student.msv}, Ho va ten: ${student.hovaten}, Email: ${student.email}")
                }
            }
        }
    }
}
@Composable
fun CommonSpace(){
    Spacer(modifier = Modifier.height(16.dp))
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    QuanlysinhvienTheme {
        QuanLySinhVien()
    }
}