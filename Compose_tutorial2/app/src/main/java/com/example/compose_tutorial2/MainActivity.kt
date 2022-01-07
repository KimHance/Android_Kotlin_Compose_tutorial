package com.example.compose_tutorial2

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.*
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.compose_tutorial2.ui.theme.Compose_tutorial2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Compose_tutorial2Theme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    TextContainer()
                }
            }
        }
    }
}
// Text 메소드
//text: String,
//modifier: Modifier = Modifier,
//color: Color = Color.Unspecified,
//fontSize: TextUnit = TextUnit.Unspecified,
//fontStyle: FontStyle? = null,
//fontWeight: FontWeight? = null,
//fontFamily: FontFamily? = null,
//letterSpacing: TextUnit = TextUnit.Unspecified,
//textDecoration: TextDecoration? = null,
//textAlign: TextAlign? = null,
//lineHeight: TextUnit = TextUnit.Unspecified,
//overflow: TextOverflow = TextOverflow.Clip,
//softWrap: Boolean = true,
//maxLines: Int = Int.MAX_VALUE,
//onTextLayout: (TextLayoutResult) -> Unit = {},
//style: TextStyle = LocalTextStyle.current

@Composable
fun TextContainer(){
    val name = "현수킴"
    val words = stringResource(id = R.string.dummy_string_short)
    var wordsArray = words.split(" ") // 띄어쓰기로 단어 자름

    val scrollState = rememberScrollState()

    Column(
        verticalArrangement = Arrangement.spacedBy(10.dp),
        modifier = Modifier
            .padding(10.dp)
            .fillMaxSize()
            .verticalScroll(scrollState)
    ){
        Text(text = "하이하이 제트팩 부수기 $name",
            style = TextStyle(
                textAlign = TextAlign.Start
            ),
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Yellow)
        )
        Text(text = "하이하이 제트팩 부수기 $name",
            style = TextStyle(
                textAlign = TextAlign.End
            ),
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Blue)
        )
        Text(text = stringResource(id = R.string.dummy_string_short),
            style = TextStyle(
                textAlign = TextAlign.Justify,
                //textDecoration = TextDecoration.Underline // 밑줄
                //textDecoration = TextDecoration.LineThrough // 가운데 줄
                textDecoration = TextDecoration.combine(  //밑줄과 가운데 줄 합침
                    listOf(
                        TextDecoration.LineThrough,
                        TextDecoration.Underline
                    )
                )
            ),
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.LightGray)
        )
        Text(text = stringResource(id = R.string.dummy_string_long),
            maxLines = 3, // 줄 제한
            overflow = TextOverflow.Ellipsis, // 잘리면 ...으로 표현
            style = TextStyle(
                textAlign = TextAlign.Justify,
                fontFamily = FontFamily.Serif, //글씨 체
                fontWeight = FontWeight.ExtraBold, // 굵기
                lineHeight = 40.sp // 줄 높이
            ),
            fontSize = 20.sp,
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.LightGray)
        )
        Text(text= buildAnnotatedString { //텍스트 마다 스타일 지정 가능
            append("안녕 친구들 ?")
            append("너마늘 생강해")

            withStyle(style= SpanStyle(color=Color.Blue,
                fontSize = 40.sp,
                fontWeight = FontWeight.ExtraBold
            )){
                append("구라야. 흥분하지마, 집착해")
            }

        })

        Text(text= buildAnnotatedString {
            wordsArray.forEach{
                if(it.contains("청춘")){ // 청춘이 있는 마디 빨간색으로
                    withStyle(style = SpanStyle(color = Color.Red,
                        fontSize = 30.sp,
                        fontWeight = FontWeight.ExtraBold
                        )
                    ){
                        append("$it ")
                    }
                }else{
                    append("$it ")
                }
            }
        })

        ClickableText(text = AnnotatedString("클릭 가능"), onClick ={  // 클릭 가능한 텍스트
            Log.d("TAG","클릭되었음")
        } )

        Text(text= stringResource(id = R.string.dummy_string_long),
            style = TextStyle(lineHeight = 40.sp)
            )
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Compose_tutorial2Theme {
        TextContainer()
    }
}