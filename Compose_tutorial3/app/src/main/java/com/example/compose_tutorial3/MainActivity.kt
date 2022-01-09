package com.example.compose_tutorial3

import android.media.Image
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose_tutorial3.ui.theme.Compose_tutorial3Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Compose_tutorial3Theme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    CheckBoxContainer()
                }
            }
        }
    }
}

// checked : 체크 상태
// onCheckedChange : 체크상태 변경 콜백 이벤트
// enabled : 체크 가능 여부
// colors : 체크 박스에 대한 색 변경
//        checkedColor: 선택 시 배경
//        uncheckedColor: 테두리
//        checkmarkColor: 체크선 색
//        disabledColor: enabled가 false 시

// 컴포저블에서 MutableState 객체를 선언하는 세 가지 방법
// 'val mutableState = remember { mutableStateOf(default) }'
// 'var value by remember { mutableStateOf(default) }'
// 'val (value, setValue) = remember { mutableStateOf(default) }'



@Composable
fun CheckBoxContainer(){

    val checkedStatusForFirst = remember { mutableStateOf(false) }
    val checkedStatusForSecond = remember { mutableStateOf(false) }
    val checkedStatusForThird = remember { mutableStateOf(false) }


    val checkedStateArray = listOf(
        checkedStatusForFirst,
        checkedStatusForSecond,
        checkedStatusForThird,
    )



    val allBoxChecked : (Boolean) -> Unit = { isAllBoxChecked ->
        Log.d("TAG", "다 찍힘")
        checkedStateArray.forEach{ it.value = isAllBoxChecked }
    }

    val checkedStatusForForth : Boolean = checkedStateArray.all {it.value} //all 메소드 자체가 모두 true 면 true 반환

    Column(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterVertically),
        Alignment.CenterHorizontally
    ){

        CheckBoxWithTitle(title = "1번 체크박스", checkedStatusForFirst) // Row 를 이용한 체크박스 라벨 붙히기
        CheckBoxWithTitle(title = "2번 체크박스", checkedStatusForSecond)
        CheckBoxWithTitle(title = "3번 체크박스", checkedStatusForThird)
//        })
        Spacer(modifier = Modifier.height(20.dp))
        CheckAllAgree(title = "모두 동의", checkedStatusForForth,allBoxChecked)
        Spacer(modifier = Modifier.height(20.dp))
        MyCustomCheckedBox(title = "커스텁 체크박스")


    }
}

@Composable
fun CheckBoxWithTitle(title : String, isCheckedState : MutableState<Boolean>){

    Row(
        modifier = Modifier
            .padding(horizontal = 30.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ){
        Checkbox(
            checked=isCheckedState.value,
            enabled = true,
            colors = CheckboxDefaults.colors(
                checkedColor = Color.Red,  // 선택 시 배경
                uncheckedColor = Color.Black,  // 테두리
                checkmarkColor = Color.White, // 체크선 색
                disabledColor = Color.Magenta // enabled = false 시

            ),
            onCheckedChange = { isChecked ->  // 기본적인 체크박스
                Log.d("TAG","CheckBoxContainer: isChecked: $isChecked")
                isCheckedState.value = isChecked
            })
        Text(text = title)
    }
}

@Composable
fun CheckAllAgree(title : String, shouldChecked : Boolean, allBoxChecked : (Boolean) -> Unit){
    Row(
        modifier = Modifier
            .padding(horizontal = 30.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ){
        Checkbox(
            checked=shouldChecked,
            enabled = true,
            onCheckedChange = { isChecked ->  // 기본적인 체크박스
                Log.d("TAG","CheckBoxContainer: isChecked: $isChecked")
                allBoxChecked(isChecked)
            })
        Text(text = title)
    }
}

@Composable
fun MyCustomCheckedBox(title : String){

    var (isChecked,setIsChecked) = remember { mutableStateOf(false) }  // 첫번재는 get, 두번째는 set

    var togglePainter = if (isChecked) R.drawable.checked else R.drawable.unchecked

    var checkedInfoString = if (isChecked) "체크됨" else "체크 안됨"

    Row(
        modifier = Modifier
            .padding(horizontal = 30.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ){

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(60.dp)
                .clickable(
                //indication = null,  // 클릭시 객체 표시, Ripple 제거
                indication =rememberRipple(  // Ripple 표시
                    radius = 30.dp,     // 퍼지는 범위
                    bounded =  false,    // true 면 범위내 클릭된 곳중심으로 퍼짐, false 면 중앙에서 퍼짐
                    color = Color.Blue   // 색
                ),
                interactionSource = remember { MutableInteractionSource() }
            ) {
                setIsChecked.invoke(!isChecked)
                Log.d("TAG","클릭이 됨")
            }
        ){
            androidx.compose.foundation.Image(
                painter = painterResource(id = togglePainter),
                contentDescription = null,
            )
        }
        Text(text = "$title / $checkedInfoString")
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Compose_tutorial3Theme {
        CheckBoxContainer()
    }
}