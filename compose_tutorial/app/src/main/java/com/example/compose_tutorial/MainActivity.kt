package com.example.compose_tutorial

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.*
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.example.compose_tutorial.ui.theme.Compose_tutorialTheme
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Compose_tutorialTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    //BoxWithConstraint()
                //Container()
                    //ShapeContainer()
                    ButtonContainer()
                }
            }
        }
    }
}

// arrangement 요소를 어떤식으로 배열할지
// arrangement 는 Row, Column 같은 요소들이 들어가는
// 컨테이너성격의 컴포저블에서 요소들의 아이템을 정렬할 때 사용
//
// horizontal Arrangement 는 Start, End, Center만 존재
// .SpaceBetween : 공간 모두 차지
// .Start : 왼쪽으로
// .End : 오른쪽으로
// .SpaceAround : 빈 공간을 남겨두기
// .Center : 가운데
// .SpaceEvenly : 요소들 사이에 공간을 똑같이 하기
//
// alignment 는 해당 컨테이너 안에 들어있는 요쇼들의 위치 설정
// linearLayout 에서 gravity 와 동일
// .Bottom : 아래에
// .Top : 위에
// .CenterVertically : 컨테이너의 수직 방향으로 중앙에
//
// Row 컴포저블 안에서 align 이 들어가기 때문에 CenterVertically
//
//
// 박스는 겹칠 수 있음
// 기존 relative, constraint, frame 레이아웃에서 뷰를 겹치는 것과 비슷
// 아래로 내려갈수록 위에 뷰를 올리는 방식
// alignment 는 row, column 보다 다양하게 지원
//
// .BottomCenter : 컨테이너의 중앙 아래
// .BottomEnd : 컨테이너 아래 오른쪽
// .BottomStart : 컨테이너의 아래 왼쪽
//
// .Center : 컨테이너 정중앙
// .CenterStart : 컨테이너 중앙 왼쪽
// .CenterEnd : 컨테이너의 중앙 오른쪽
//
// .TopCenter : 컨테이너의 위 중앙
// .TopStart : 컨테이너의 위 왼쪽
// .TopEnd : 컨테이너의 위 오른쪽
//
// propagateMinConstraints 해당 옵션을 true로 하면
// 박스 안에 있는 제일 작은 크기의 뷰를 컨테이너 박스의 크기만큼 constraint 를


@Composable
fun myBox(){
    Box(
        modifier = Modifier
            .background(Color.White),
        contentAlignment = Alignment.CenterStart
    ){
        DummyBox(modifier = Modifier.size(60.dp))
        DummyBox(modifier = Modifier.size(40.dp), color = Color.Yellow)
        DummyBox(modifier = Modifier.size(20.dp))
    }
}

// Box의 크기에 따라 수행 변화 가능
@Composable
fun BoxWithConstraint(){
    BoxWithConstraints(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize(),
        contentAlignment = Alignment.Center,
        propagateMinConstraints = false
    ) {
        // 내부에서 조건에 따라 ( 예 : 가로화면, 세로화면에 따른 변화)
        if(this.maxHeight> 500.dp){
            DummyBox(modifier = Modifier.size(200.dp), color = Color.Green)
        }else{
            DummyBox(modifier = Modifier.size(200.dp), color = Color.Blue)
        }
        Text(text= "minHeight : ${this.minHeight}")

        // BoxWithConstraintItem 안에서의 조건에 따라
//        Column {
//            BoxWithConstraintItem(modifier = Modifier
//                .size(200.dp)
//                .background(Color.Yellow))
//            BoxWithConstraintItem(modifier = Modifier
//                .size(300.dp)
//                .background(Color.Green))
//        }

    }
}

@Composable
fun BoxWithConstraintItem(modifier: Modifier = Modifier){
    BoxWithConstraints(
        modifier = modifier,
        contentAlignment = Alignment.Center,
        propagateMinConstraints = false
    ) {
        if(this.minWidth> 200.dp){
            Text(text = "큰 상자")
        }else{
            Text(text = "작은 상자자")
       }
    }
}


@Composable
fun myColumn(){
    Column(
        modifier = Modifier
            .background(Color.White)
            .fillMaxHeight(),
        verticalArrangement = Arrangement.SpaceEvenly,
        Alignment.CenterHorizontally
    ) {
        myBox()
        myBox()
        myBox()
    }
}

@Composable
fun Container(){

    Row(  // item 을 가로로 나열 , Row 이기 때문에 horizontal, Column 이면 vertical
        modifier= Modifier
            .background(Color.White)
            .fillMaxSize(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        Alignment.CenterVertically
    ){
        myColumn()
        myColumn()
        myColumn()
    }

}

// Button
// enable : 클릭 여부
// interactionSource : 사용자 인터랙션 처리
// elevation : 그림자 효과
// 커스텀
// shape, border, colors 등 사용 가능

@Composable
fun ButtonContainer(){

    val buttonBorderGradient = Brush.horizontalGradient(listOf(Color.Yellow,Color.Red))

    val interactionSource = remember { MutableInteractionSource()}

    val isPressed by interactionSource.collectIsPressedAsState()

    val pressStatusTitle = if (isPressed) "버튼 누름" else "버튼에서 손 땜"

    Column(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Button(
            elevation = ButtonDefaults.elevation(
                defaultElevation = 10.dp,  //초기
                pressedElevation = 0.dp,     //눌렀을 떄
                disabledElevation = 0.dp   //enabled 가 false 일 때
            ),
            enabled = true,
            onClick={
            Log.d("TAG","버튼 1 클릭")
        }){
            Text(text= "버튼 1")
        }
        Button(
            elevation = ButtonDefaults.elevation(
                defaultElevation = 10.dp,  //초기
                pressedElevation = 0.dp,     //눌렀을 떄
                disabledElevation = 0.dp   //enabled 가 false 일 때
            ),
            enabled = true,
            shape = CircleShape,
            border = BorderStroke(4.dp,Color.Black),
            onClick={
                Log.d("TAG","버튼 2 클릭")
            }){
            Text(text= "버튼 2")
        }
        Button(
            elevation = ButtonDefaults.elevation(
                defaultElevation = 10.dp,  //초기
                pressedElevation = 0.dp,     //눌렀을 떄
                disabledElevation = 0.dp   //enabled 가 false 일 때
            ),
            enabled = true,
            shape = CircleShape,
            border = BorderStroke(4.dp,buttonBorderGradient),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.Black,
                disabledBackgroundColor = Color.LightGray
            ),
            interactionSource = interactionSource,
            contentPadding = PaddingValues(horizontal = 30.dp,vertical = 10.dp),
            onClick={
                Log.d("TAG","버튼 3 클릭")
            }){
            Text(text= "버튼 3",
                color = Color.White
            )
        }
//        if(isPressed){
//            Text(text="버튼 누르고 있음")
//        }else {
//            Text(text = " 버튼에서 손 땜")
//        }

        Text(text = "$pressStatusTitle")
    }
}

@Composable
fun DummyBox(modifier : Modifier = Modifier, color : Color? =null){
    val red = Random.nextInt(256)
    val green = Random.nextInt(256)
    val blue = Random.nextInt(256)

    // color 가 값이 있으면 해당값, 없으면 랜덤 값
    val randomColor = color?.let{ it } ?: Color(red,green,blue)
    Box(modifier = modifier
        .size(100.dp)
        .background(randomColor)
    )
}

@Composable
fun ShapeContainer(){
    Column(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        Alignment.CenterHorizontally
    ) {
        DummyBox(modifier = Modifier.clip(RectangleShape))  // 네모
        DummyBox(modifier = Modifier.clip(CircleShape))   // 동그라미
        DummyBox(modifier = Modifier.clip(RoundedCornerShape(10.dp)))  // 둥근 모서리
        DummyBox(modifier = Modifier.clip(CutCornerShape(30.dp)))  // 잘린 모서리
        DummyBox(modifier = Modifier.clip(TriangleShape()))
    }
}

class TriangleShape() : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        val path = Path().apply {      // 파이썬의 터틀 그래픽과 비슷
            moveTo(size.width / 2f, 0f)
            lineTo(size.width, size.height)
            lineTo(0f, size.height)
            close()
        }
        return Outline.Generic(path = path)
    }

}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Compose_tutorialTheme {
        //BoxWithConstraint()
    //Container()
       // ShapeContainer()
        ButtonContainer()
    }
}