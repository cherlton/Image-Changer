package com.example.lemonade
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonade.ui.theme.LemonadeTheme
import kotlin.random.Random
class MainActivity : ComponentActivity() {
 override fun onCreate(savedInstanceState: Bundle?) {
 super.onCreate(savedInstanceState)
 setContent {
 LemonadeTheme {
 Surface(
 modifier = Modifier.fillMaxSize(),
 color = MaterialTheme.colorScheme.background
 ) {
 LemonadeApp()
 }
 }
 }
 }
}
@Composable
fun LemonadeApp() {
 val (currentScreen, setCurrentScreen) = remember { mutableStateOf("LemonTree") }
 val (squeezesLeft, setSqueezesLeft) = remember { mutableStateOf(Random.nextInt(2, 5)) }
 Column() {
 Title()
 when (currentScreen) {
 "LemonTree" -> LemonTree {
 setCurrentScreen("SqueezeLamon")
 setSqueezesLeft(Random.nextInt(2, 5))
 }
 "SqueezeLamon" -> SqueezeLamon(squeezesLeft) { squeezes ->
 if (squeezes <= 0) {
 setCurrentScreen("DrinkLemon")
 } else {
 setSqueezesLeft(squeezes)
 }
 }
 "DrinkLemon" -> DrinkLemon { setCurrentScreen("EmptyGlass") }
 "EmptyGlass" -> EmptyGlass { setCurrentScreen("LemonTree")}
 }
 }
}
@Composable
fun LemonTree(onClick: () -> Unit) {
 Column(
 modifier = Modifier
 .fillMaxSize()
 .wrapContentSize(Alignment.Center),
 horizontalAlignment = Alignment.CenterHorizontally
 ) {
 Button(
 onClick = onClick,
 shape = RoundedCornerShape(15.dp)
 ) {
 Image(
 painter = painterResource(id = R.drawable.lemon_tree),
 contentDescription = "1"
 )
 }
 Spacer(modifier = Modifier.height(16.dp))
 Text(text = stringResource(id = R.string.select_lemon),
 fontSize = 18.sp)
 }
}
@Composable
fun SqueezeLamon(squeezesLeft: Int, onSqueeze: (Int) -> Unit) {
 Column(
 modifier = Modifier
 .fillMaxSize()
 .wrapContentSize(Alignment.Center),
 horizontalAlignment = Alignment.CenterHorizontally
 ) {
 Button(
 onClick = { onSqueeze(squeezesLeft - 1) },
 shape = RoundedCornerShape(15.dp)
 ) {
 Image(
 painter = painterResource(id = R.drawable.lemon_squeeze),
 contentDescription = "Lemon"
 )
 }
 Spacer(modifier = Modifier.height(16.dp))
 Text(text = stringResource(id = R.string.squeeze_lemon) + " ($squeezesLeft squeezes left)")
 }
}
@Composable
fun DrinkLemon(onClick: () -> Unit) {
 Column(
 modifier = Modifier
 .fillMaxSize()
 .wrapContentSize(Alignment.Center),
 horizontalAlignment = Alignment.CenterHorizontally
 ) {
 Button(
 onClick = onClick,
 shape = RoundedCornerShape(15.dp)
 ) {
 Image(
 painter = painterResource(id = R.drawable.lemon_drink),
 contentDescription = "3"
 )
 }
 Spacer(modifier = Modifier.height(16.dp))
 Text(text = stringResource(id = R.string.drink_lemonade),
 fontSize = 18.sp)
 }
}
@Composable
fun EmptyGlass(onClick: () -> Unit) {
 Column(
 modifier = Modifier
 .fillMaxSize()
 .wrapContentSize(Alignment.Center),
 horizontalAlignment = Alignment.CenterHorizontally
 ) {
 Button(
 onClick = onClick,
 shape = RoundedCornerShape(15.dp)
 ) {
 Image(
 painter = painterResource(id = R.drawable.lemon_restart),
 contentDescription = "4"
 )
 }
 Spacer(modifier = Modifier.height(16.dp)) //adding space
 Text(text = stringResource(id = R.string.empty_glass),
 fontSize = 18.sp)
 }
}
//Title Function
@Composable
fun Title(modifier: Modifier = Modifier){
 Surface (
 modifier = modifier.
 fillMaxWidth(),
 color = Color.Yellow
 ){
 Text(
 modifier = modifier
 .fillMaxWidth()
 .wrapContentSize(Alignment.TopCenter)
 .padding(15.dp),
 text = "Lemonade",
 style = MaterialTheme.typography.headlineLarge
 )
 }
}
//Preview
@Preview(showBackground = true)
@Composable
fun LemonadeAppPreview(){
 LemonadeTheme{
 LemonadeApp()
 }
}
