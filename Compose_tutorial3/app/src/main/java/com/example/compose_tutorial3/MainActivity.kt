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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose_tutorial3.ui.theme.Compose_tutorial3Theme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Compose_tutorial3Theme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    TextFieldTest()
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
//
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
                    indication = rememberRipple(  // Ripple 표시
                        radius = 30.dp,     // 퍼지는 범위
                        bounded = false,    // true 면 범위내 클릭된 곳중심으로 퍼짐, false 면 중앙에서 퍼짐
                        color = Color.Blue   // 색
                    ),
                    interactionSource = remember { MutableInteractionSource() }
                ) {
                    setIsChecked.invoke(!isChecked)
                    Log.d("TAG", "클릭이 됨")
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

@Composable
fun MySnackbar(){

    var snackbarHostState = remember{SnackbarHostState()}

    var coroutineScope = rememberCoroutineScope()

    val buttonTitle : (SnackbarData?) -> String = {snackbarData -> // 스낵바 데이터 상태(띄워졌는지 아닌지)에 따라 String값 설정
        if (snackbarData != null){
            "스낵바 숨기기"
        }else{
            "스낵바 띄우기"
        }
    }

    val buttonColor : (SnackbarData?) -> Color = {snackbarData -> // 스낵바 데이터 상태(띄워졌는지 아닌지)에 따라 Color 설정
        if (snackbarData != null){
            Color.Black
        }else{
            Color.Blue
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Button(
            colors = ButtonDefaults.buttonColors(
                backgroundColor = buttonColor(snackbarHostState.currentSnackbarData),  // 현재 스낵바 데이터 상태에 따라 색깔 바뀜
                contentColor = Color.White
            ),
            onClick = {
            Log.d("TAG","스낵바 버튼 클릭")
            if(snackbarHostState.currentSnackbarData != null){ // 스낵바가 이미 존재하면
                Log.d("TAG","스낵바 이미 존재")
                snackbarHostState.currentSnackbarData?.dismiss() // 스낵바 내려줌
                return@Button
            }
            coroutineScope.launch {
                var result = snackbarHostState.showSnackbar( //snackbarHostState 가 Toast 메세지 띄우듯이 처리해줌
                    "오늘도 공부조지자",
                    "확인",
                    SnackbarDuration.Short
                ).let{
                    when(it){
                        SnackbarResult.Dismissed ->  Log.d("TAG","스낵바 닫혀짐")
                        SnackbarResult.ActionPerformed ->  Log.d("TAG","스냅가 확인 버튼 클릭")
                    }
                }
            }
        }) {
            Text(buttonTitle(snackbarHostState.currentSnackbarData)) //스낵바 데이터를 매개변수로 넘겨줌
        }

        // 스낵바가 보여지는 부분
        SnackbarHost(hostState = snackbarHostState, modifier = Modifier.align(Alignment.BottomCenter))
    }
}

@Composable
fun TextFieldTest(){

    var userInput by remember { mutableStateOf(TextFieldValue())} //userInput을 활용하는 TextFieldValue

    var phoneNumberInput by remember { mutableStateOf(TextFieldValue())}

    var emailInput by remember { mutableStateOf(TextFieldValue())}

    var passwordInput by remember { mutableStateOf(TextFieldValue())}

    var shouldShowPassword = remember { mutableStateOf(false)}

    val passwordResource: (Boolean) -> Int = {
        if(it){
            R.drawable.ic_baseline_visibility
        }else{
            R.drawable.ic_baseline_visibility_off
        }
    }

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
    ){
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = userInput,
            singleLine = false,
            maxLines = 2,
            onValueChange = { newValue -> userInput = newValue},
            label = {Text("사용자 입력")},
            placeholder = {Text("작성하세요")}

        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = phoneNumberInput,
            singleLine = true, //한줄만
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number), //키보드를 넘버로
            onValueChange = { newValue -> phoneNumberInput = newValue},
            label = {Text("전화 번호")}, //textField 라벨
            placeholder = {Text("010-1234-1234")}  //hint 와 비슷
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = emailInput,
            singleLine = true, //한줄만
            leadingIcon = {Icon(imageVector = Icons.Default.Email, contentDescription = null)}, //왼쪽에 Icon
            //trailingIcon = {Icon(imageVector = Icons.Default.CheckCircle, contentDescription = null)}, //오른쪽에 Icon
            trailingIcon = { IconButton(onClick = { Log.d("TAG","체크버튼 클릭")}) // 클릭이 가능한 Icon
            {
                Icon(imageVector = Icons.Default.CheckCircle, contentDescription = null)  // 컴포저블
            } },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email), // 이메일 형식
            onValueChange = { newValue -> emailInput = newValue},
            label = {Text("e-mail")}, //textField 라벨
            placeholder = {Text("이메일 주소를 입력해 주세요")}  //hint 와 비슷
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = passwordInput,
            singleLine = true, //한줄만
            leadingIcon = {Icon(imageVector = Icons.Default.Person, contentDescription = null)}, //왼쪽에 Icon
            trailingIcon = { IconButton(onClick = {
                Log.d("TAG","체크버튼 클릭")
                shouldShowPassword.value = !shouldShowPassword.value // 누를 때마다 값 반전
            }) // 클릭이 가능한 Icon
            {
                Icon(painter = painterResource(id = passwordResource(shouldShowPassword.value)),contentDescription = null)  // 컴포저블
            } },
            visualTransformation = if(shouldShowPassword.value) VisualTransformation.None else PasswordVisualTransformation(), //showShouldPassword 에 따라 보여주기 여부 결정
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password), // 패스워드 형식
            onValueChange = { newValue -> passwordInput = newValue},
            label = {Text("비밀번호")}, //textField 라벨
            placeholder = {Text("비밀번호를 입력해주세요")}  //hint 와 비슷
        )

    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Compose_tutorial3Theme {
        TextFieldTest()
    }
}