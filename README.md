# Android_Kotlin_Compose_tutorial
안드로이드 컴포즈 튜토리얼


### tutorial(1) Row, Column, Box, BoxWithConstraints  

 > arrangement 요소를 어떤식으로 배열할지  
 > arrangement 는 Row, Column 같은 요소들이 들어가는  
 > 컨테이너성격의 컴포저블에서 요소들의 아이템을 정렬할 때 사용  
 > horizontal Arrangement 는 Start, End, Center만 존재  
 > + .SpaceBetween : 공간 모두 차지
 > + .Start : 왼쪽으로
 > + .End : 오른쪽으로
 > + .SpaceAround : 빈 공간을 남겨두기
 > + .Center : 가운데
 > + .SpaceEvenly : 요소들 사이에 공간을 똑같이 하기  
     
 > alignment 는 해당 컨테이너 안에 들어있는 요쇼들의 위치 설정  
 > linearLayout 에서 gravity 와 동일  
 > + .Bottom : 아래에
 > + .Top : 위에
 > + .CenterVertically : 컨테이너의 수직 방향으로 중앙에
 > + Row 컴포저블 안에서 align 이 들어가기 때문에 CenterVertically  
   
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
  
* * *

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

* * *


### tutorial(3) Shape , Button, CheckBox  

> Shape  
>  + .clip을 이용하면 쉽다.  
>  + 파이썬의 터틀 그래픽과 비슷하게 모양을 커스텀할 수 있다.  
  
> Button  
> + enable : 클릭 여부  
> + interactionSource : 사용자 인터랙션 처리  
> + elevation : 그림자 효과  
> + 커스텀 가능  
> + shape, border, colors 등 사용 가능  
  
> CheckBox  
> + checked : 체크 상태  
> + onCheckedChange : 체크상태 변경 콜백 이벤트  
> + enabled : 체크 가능 여부  
> + colors : 체크 박스에 대한 색 변경  
> + .checkedColor: 선택 시 배경  
> + .uncheckedColor: 테두리  
> + .checkmarkColor: 체크선 색  
> + .disabledColor: enabled가 false 시  
  
> 컴포저블에서 MutableState 객체를 선언하는 세 가지 방법 (체크박스 상태를 바꾸기 위해)  
> + 'val mutableState = remember { mutableStateOf(default) }'  
> + 'var value by remember { mutableStateOf(default) }'  
> + 'val (value, setValue) = remember { mutableStateOf(default) }'  
  
> clickable 에서 indication 을 통해 Ripple(클릭한 객체 표시) 제거 및 설정 가능  
>  + radius : 퍼지는 범위
>  + bounded : true 면 범위내 클릭된 곳중심으로 퍼짐, false 면 중앙에서 퍼짐
>  + color : Ripple 색  

* * *

### tutorial(4) SnackBar, TextField  

> SnackBar  
> + SnackbarHostState의 currentSnackbarData에 의해 동작 가능  
> + rememberCoroutineScope() 의 .launch 메소드를 통해 스낵바를 보여줌  
> + SnackbarResult.Dismissed -> 스낵바가 내려간 상태  
> + SnackbarResult.ActionPerformed -> 스낵바 올라온 상태  
> + 올라온 스낵바를 dismiss()를 통해 내릴 수 있음  
  
> TextField  
> + value 필드를 통해 input 가능  
> + singleLine 필드를 통해 한줄만 보이기 가능  
> + maxLines 필드를 통해 줄 최대치 설정 가능  
> + label 필드를 통해 필드의 라벨 설정 가능  
> + placeholder 필드는 hint 와 비슷  
> + keyboardOptions 을 통해 필드 클릭시 키보드 종류 설정 가능
> + TextField 는 테두리가 없음, OutlinedTextField 는 테두리가 존재  
> + leadingIcon 을 통해 왼쪽에 이미지, trailingIcon 은 오른쪽 이미지, 둘다 Icon 혹은 IconButton으로 클릭설정 가능  
> + visualTransformation 을 통해 문자 숨기기 가능  
> + onValueChange 을 통해 필드안의 input을 유닛으로 처리  


#Compose Api 가이드라인 

### 원문

[androidx/compose-api-guidelines.md at androidx-main · androidx/androidx](https://github.com/androidx/androidx/blob/androidx-main/compose/docs/compose-api-guidelines.md#naming-unit-composable-functions-as-entities)

### ▪️파스칼 케이스의 사용

```markdown
#파스칼케이스 
PascalCase

#케피탈케이스 
CAPITALS_AND_UNDERSCORES
```

### ▪️@Composable 엔티티 - 이름은 대문자로 시작 - 명사

```kotlin
#콤포저블 은 명사로 
// This function is a descriptive PascalCased noun as a visual UI element
@Composable
fun FancyButton(text: String, onClick: () -> Unit) {}

// This function is a descriptive PascalCased noun as a non-visual element
// with presence in the composition
@Composable
fun BackButtonHandler(onBackPressed: () -> Unit) {}

// This function is PascalCased but is not a noun!
@Composable
fun MyButton(text: String, onClick: () -> Unit) {

// This function is neither PascalCased nor a noun!
@Composable
fun ProfileImage(image: ImageAsset) {
```

### ▪️@Composable 값을 반환하는 콤포저블

```kotlin

// Returns a style based on the current CompositionLocal settings
// This function qualifies where its value comes from
@Composable
fun defaultStyle(): Style {

// Returns a style based on the current CompositionLocal settings
// This function looks like it's constructing a context-free object!
@Composable
fun selectedStyle(): Style {
```

### ▪️**@Composable functions that remember {} the objects they return**

```kotlin

// Returns a CoroutineScope that will be cancelled when this call
// leaves the composition
// This function is prefixed with remember to describe its behavior
@Composable
fun rememberCoroutineScope(): CoroutineScope {

```

### ▪️콤포지션 로컬 이름

```kotlin

// "Local" is used here as an adjective, "Theme" is the noun.
val LocalTheme = staticCompositionLocalOf<Theme>()

// "Local" is used here as a noun!
val GlobalTheme = staticCompositionLocalOf<Theme>()
```

### ▪️**Emit XOR return a value**

상태는 매개변수로 콤포저블에 넣으세요 

```kotlin

// Emits a text input field element that will call into the inputState
// interface object to request changes
@Composable
fun InputField(inputState: InputState) {
// ...

// Communicating with the input field is not order-dependent
val inputState = remember { InputState() }

Button("Clear input", onClick = { inputState.clear() })

InputField(inputState)

# 이렇게 하지 마세요 
// Emits a text input field element and returns an input value holder
@Composable
fun InputField(): UserInputState {
// ...

// Communicating with the InputField is made difficult
Button("Clear input", onClick = { TODO("???") })
val inputState = InputField()

```

Communicating with a composable by passing parameters forward affords aggregation of several such parameters into types used as parameters to their callers:

```kotlin
interface DetailCardState {
    val actionRailState: ActionRailState
    // ...
}

@Composable
fun DetailCard(state: DetailCardState) {
    Surface {
        // ...
        ActionRail(state.actionRailState)
    }
}

@Composable
fun ActionRail(state: ActionRailState) {
    // ...
}
```

## **Compose UI API structure**

### **Compose UI elements**

```kotlin
@Composable
fun SimpleLabel(
    text: String,
    modifier: Modifier = Modifier
) {

## 이벤트 처리는 lambda unit 등으로 처리 권장 
@Composable
fun FancyButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {

## 이렇게 하지 마세요
interface ButtonState {
    val clicks: Flow<ClickEvent>
    val measuredSize: Size
}

@Composable
fun FancyButton(
    text: String,
    modifier: Modifier = Modifier
): ButtonState {
```

### **Elements accept and respect a Modifier parameter**

모디파이어 매개변수로 넣으세요 

```kotlin
@Composable
fun FancyButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) = Text(
    text = text,
    modifier = modifier.surface(elevation = 4.dp)
        .clickable(onClick)
        .padding(horizontal = 32.dp, vertical = 16.dp)
)

```

### **Compose UI layouts**

콤포저블 안에 콤포저블을 넣어서 레이아웃을 따로 만들어라 

```kotlin
@Composable
fun SimpleRow(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {

## example
SimpleRow(FancyButton())
```

### **Compose UI modifiers**

```kotlin
# Modifier factory functions

Modifier.preferredSize(50.dp)
    .backgroundColor(Color.Blue)
    .padding(10.dp)

fun Modifier.myModifier(
    param1: ...,
    paramN: ...
): Modifier = then(MyModifierImpl(param1, ... paramN))
```

### **Composed modifiers**

모디파이어 여러개 적용 

```kotlin
fun Modifier.myModifier(): Modifier = composed {
    val color = LocalTheme.current.specialColor
    backgroundColor(color)
}

fun Modifier.modifierWithState(): Modifier = composed {
    val elementSpecificState = remember { MyModifierState() }
    MyModifier(elementSpecificState)
}

// ...
val myModifier = someModifiers.modifierWithState()

Text("Hello", modifier = myModifier)
Text("World", modifier = myModifier)
```

### **Layout-scoped modifiers**

```kotlin
@Stable
interface WeightScope {
    fun Modifier.weight(weight: Float): Modifier
}

@Composable
fun WeightedRow(
    modifier: Modifier = Modifier,
    content: @Composable WeightScope.() -> Unit
) {
// ...

// Usage:
WeightedRow {
    Text("Hello", Modifier.weight(1f))
    Text("World", Modifier.weight(2f))
}
```

## **Compose API design patterns**

콤포즈로 UI 짤때 자주 쓰이는 패턴들 

```kotlin
@Composable
fun Checkbox(
    isChecked: Boolean,
    onToggle: () -> Unit
) {
// ...

// Usage: (caller mutates optIn and owns the source of truth)
Checkbox(
    myState.optIn,
    onToggle = { myState.optIn = !myState.optIn }
)

#Don't 이렇게 하지 마세요 
@Composable
fun Checkbox(
    initialValue: Boolean,
    onChecked: (Boolean) -> Unit
) {
    var checkedState by remember { mutableStateOf(initialValue) }
// ...

// Usage: (Checkbox owns the checked state, caller notified of changes)
// Caller cannot easily implement a validation policy.
Checkbox(false, onToggled = { callerCheckedState = it })
```

### **Separate state and events**

### **Hoisted state types**

```kotlin
# 이전 
@Composable
fun VerticalScroller(
    scrollPosition: Int,
    scrollRange: Int,
    onScrollPositionChange: (Int) -> Unit,
    onScrollRangeChange: (Int) -> Unit
) {

# 이렇게 하시오 
@Stable
interface VerticalScrollerState {
    var scrollPosition: Int
    var scrollRange: Int
}

@Composable
fun VerticalScroller(
    verticalScrollerState: VerticalScrollerState
) {
```

### **Default policies through hoisted state objects**

```kotlin
fun VerticalScrollerState(): VerticalScrollerState = 
    VerticalScrollerStateImpl()

private class VerticalScrollerStateImpl(
    scrollPosition: Int = 0,
    scrollRange: Int = 0
) : VerticalScrollerState {
    private var _scrollPosition by
        mutableStateOf(scrollPosition, structuralEqualityPolicy())

    override var scrollPosition: Int
        get() = _scrollPosition
        set(value) {
            _scrollPosition = value.coerceIn(0, scrollRange)
        }

    private var _scrollRange by
        mutableStateOf(scrollRange, structuralEqualityPolicy())

    override var scrollRange: Int
        get() = _scrollRange
        set(value) {
            require(value >= 0) { "$value must be > 0" }
            _scrollRange = value
            scrollPosition = scrollPosition
        }
}

@Composable
fun VerticalScroller(
    verticalScrollerState: VerticalScrollerState =
        remember { VerticalScrollerState() }
) {

# don't 이렇게 하지 마시오 
// Null as a default can cause unexpected behavior if the input parameter
// changes between null and non-null.
@Composable
fun VerticalScroller(
    verticalScrollerState: VerticalScrollerState? = null
) {
    val realState = verticalScrollerState ?:
        remember { VerticalScrollerState() }
```

### **Default hoisted state for modifiers**

```kotlin
fun Modifier.foo() = composed {
    FooModifierImpl(remember { FooState() }, LocalBar.current)
}

fun Modifier.foo(fooState: FooState) = composed {
    FooModifierImpl(fooState, LocalBar.current)
}

# don't 이렇게 하지마세요
// Null as a default can cause unexpected behavior if the input parameter
// changes between null and non-null.
fun Modifier.foo(
    fooState: FooState? = null
) = composed {
    FooModifierImpl(
        fooState ?: remember { FooState() },
        LocalBar.current
    )
}

// @Composable modifier factory functions cannot be used
// outside of composition.
@Composable
fun Modifier.foo(
    fooState: FooState = remember { FooState() }
) = composed {
    FooModifierImpl(fooState, LocalBar.current)
}
```

### **Extensibility of hoisted state types**

```kotlin
// Defined by another team or library
data class PersonData(val name: String, val avatarUrl: String)

class FooState {
    val currentPersonData: PersonData

    fun setPersonName(name: String)
    fun setPersonAvatarUrl(url: String)
}

// Defined by the UI layer, by yet another team
class BarState {
    var name: String
    var avatarUrl: String
}

@Composable
fun Bar(barState: BarState) {

## 이렇게 사용하세요 

@Stable
interface FooState {
    val currentPersonData: PersonData

    fun setPersonName(name: String)
    fun setPersonAvatarUrl(url: String)
}

@Stable
interface BarState {
    var name: String
    var avatarUrl: String
}

class MyState(
    name: String,
    avatarUrl: String
) : FooState, BarState {
    override var name by mutableStateOf(name)
    override var avatarUrl by mutableStateOf(avatarUrl)

    override val currentPersonData: PersonData =
        PersonData(name, avatarUrl)

    override fun setPersonName(name: String) {
        this.name = name
    }

    override fun setPersonAvatarUrl(url: String) {
        this.avatarUrl = url
    }
}

## 팩토리의 사용 
@Stable
interface FooState {
    // ...
}

fun FooState(): FooState = FooStateImpl(...)

private class FooStateImpl(...) : FooState {
    // ...
}

// Usage
val state = remember { FooState() }
```

참고: 개발하는 정대리(https://github.com/TuenTuenna)
