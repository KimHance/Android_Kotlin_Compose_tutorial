package com.example.compose_tutorial

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.ContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
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
                    BoxWithConstraint()
                //Container()
                }
            }
        }
    }
}

// arrangement 요소를 어떤식으로 배열할지
// arrangement 는 Row, Column 같은 요소들이 들어가는
// 컨테이너성격의 컴포저블에서 요소들의 아이템을 정렬할 때 사용

// horizontal Arrangement 는 Start, End, Center만 존재
// .SpaceBetween : 공간 모두 차지
// .Start : 왼쪽으로
// .End : 오른쪽으로
// .SpaceAround : 빈 공간을 남겨두기
// .Center : 가운데
// .SpaceEvenly : 요소들 사이에 공간을 똑같이 하기

// alignment 는 해당 컨테이너 안에 들어있는 요쇼들의 위치 설정
// linearLayout 에서 gravity 와 동일
// .Bottom : 아래에
// .Top : 위에
// .CenterVertically : 컨테이너의 수직 방향으로 중앙에

// Row 컴포저블 안에서 align 이 들어가기 때문에 CenterVertically


// 박스는 겹칠 수 있음
// 기존 relative, constraint, frame 레이아웃에서 뷰를 겹치는 것과 비슷
// 아래로 내려갈수록 위에 뷰를 올리는 방식
// alignment 는 row, column 보다 다양하게 지원

// .BottomCenter : 컨테이너의 중앙 아래
// .BottomEnd : 컨테이너 아래 오른쪽
// .BottomStart : 컨테이너의 아래 왼쪽

// .Center : 컨테이너 정중앙
// .CenterStart : 컨테이너 중앙 왼쪽
// .CenterEnd : 컨테이너의 중앙 오른쪽

// .TopCenter : 컨테이너의 위 중앙
// .TopStart : 컨테이너의 위 왼쪽
// .TopEnd : 컨테이너의 위 오른쪽

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

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Compose_tutorialTheme {
        BoxWithConstraint()
    //Container()
    }
}