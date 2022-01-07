# Android_Kotlin_Compose_tutorial
안드로이드 컴포즈 튜토리얼


### tutorial(1) Row, Column, Box, BoxWithConstraints

 > arrangement 요소를 어떤식으로 배열할지  
 > arrangement 는 Row, Column 같은 요소들이 들어가는  
 > 컨테이너성격의 컴포저블에서 요소들의 아이템을 정렬할 때 사용  
 >  
 > horizontal Arrangement 는 Start, End, Center만 존재  
 > + .SpaceBetween : 공간 모두 차지
 > + .Start : 왼쪽으로
 > + .End : 오른쪽으로
 > + .SpaceAround : 빈 공간을 남겨두기
 > + .Center : 가운데
 > + .SpaceEvenly : 요소들 사이에 공간을 똑같이 하기  
 >  
 > alignment 는 해당 컨테이너 안에 들어있는 요쇼들의 위치 설정  
 > linearLayout 에서 gravity 와 동일  
 > + .Bottom : 아래에
 > + .Top : 위에
 > + .CenterVertically : 컨테이너의 수직 방향으로 중앙에
 > + Row 컴포저블 안에서 align 이 들어가기 때문에 CenterVertically  
 >  
 > 박스는 겹칠 수 있음  
 > 기존 relative, constraint, frame 레이아웃에서 뷰를 겹치는 것과 비슷  
 > 아래로 내려갈수록 위에 뷰를 올리는 방식  
 > alignment 는 row, column 보다 다양하게 지원  
 > + .BottomCenter : 컨테이너의 중앙 아래
 > + .BottomEnd : 컨테이너 아래 오른쪽
 > + .BottomStart : 컨테이너의 아래 왼쪽
 > + .Center : 컨테이너 정중앙
 > + .CenterStart : 컨테이너 중앙 왼쪽
 > + .CenterEnd : 컨테이너의 중앙 오른쪽
 > + .TopCenter : 컨테이너의 위 중앙
 > + .TopStart : 컨테이너의 위 왼쪽
 > + .TopEnd : 컨테이너의 위 오른쪽  
  
  
### tutorial(2) Text  
  
> Text 메소드  
> + text: String : 문구  
> + modifier: Modifier = Modifier : 모디파이어  
> + color: Color = Color.Unspecified : 색깔  
> + fontSize: TextUnit = TextUnit.Unspecified : 크기   
> + fontWeight: FontWeight? = null : 폰트 굵기  
> + fontFamily: FontFamily? = null : 글씨 체  
> + textDecoration: TextDecoration? = null : 글자 꾸미기(밑줄, 선긋기 등)    
> + textAlign: TextAlign? = null : 글씨 위치  
> + lineHeight: TextUnit = TextUnit.Unspecified : 줄 간격  
> + overflow: TextOverflow = TextOverflow.Clip : 잘렸을 경우 표시  
> + maxLines: Int = Int.MAX_VALUE : 최대 라인 수  
> + style: TextStyle = LocalTextStyle.current : TextStyle 설정   
