package com.enesozdemir.binaryconverter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.enesozdemir.binaryconverter.ui.theme.BackgroundColor
import com.enesozdemir.binaryconverter.util.Constant
import com.google.accompanist.pager.*
import kotlinx.coroutines.launch


class MainActivity : ComponentActivity() {

    @ExperimentalMaterialApi
    @ExperimentalPagerApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen()
        }
    }
}

@ExperimentalMaterialApi
@ExperimentalPagerApi
@Composable
fun MainScreen() {
    val tabs = listOf(TabItem.BinaryToText, TabItem.TextToBinary)
    val pagerState = rememberPagerState()
    Column(

    ) {
        Scaffold(
            topBar = { TopBar() },
        ) {
            Tabs(tabs = tabs, pagerState = pagerState)

        }
    }
    Column() {
        TabsContent(tabs = tabs, pagerState = pagerState)
    }
}


@ExperimentalMaterialApi
@ExperimentalPagerApi
@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    MainScreen()
}

@Composable
fun TopBar() {
    TopAppBar(
        title = {
            Text(
                text = Constant.Header,
                fontSize = 25.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(),
                fontFamily = FontFamily.Monospace,
                style = TextStyle(
                    shadow = Shadow(
                        color = Color.Black,
                        offset = Offset.Zero,
                        blurRadius = 1000.0f
                    )
                )
            )
        },
        backgroundColor = BackgroundColor,
        contentColor = Color.White
    )
}

@ExperimentalMaterialApi
@ExperimentalPagerApi
@Composable
fun Tabs(tabs: List<TabItem>, pagerState: PagerState) {
    val scope = rememberCoroutineScope()
    // OR ScrollableTabRow()
    TabRow(
        // Our selected tab is our current page
        selectedTabIndex = pagerState.currentPage,
        // Override the indicator, using the provided pagerTabIndicatorOffset modifier
        backgroundColor = BackgroundColor,
        contentColor = Color.White,
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                Modifier.pagerTabIndicatorOffset(pagerState, tabPositions)
            )
        }) {
        // Add tabs for all of our pages
        tabs.forEachIndexed { index, tab ->
            // OR Tab()
            LeadingIconTab(
                text = { Text(tab.title) },
                icon = {
                },
                selected = pagerState.currentPage == index,
                onClick = {
                    scope.launch {
                        pagerState.animateScrollToPage(index)
                    }
                },
            )
        }
    }
}

@ExperimentalPagerApi
@Composable
fun TabsContent(tabs: List<TabItem>, pagerState: PagerState) {
    HorizontalPager(state = pagerState, count = tabs.size) { page ->
        tabs[page].screen()
    }
}

@Composable
fun TextScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                colorResource(
                    id = R.color.white
                )
            )
            .wrapContentSize(Alignment.Center)
    ) {
        Text(
            text = "Music View",
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Start,
            fontSize = 25.sp
        )
    }
}

@Composable
fun BinaryScreen() {
    var text by rememberSaveable { mutableStateOf("") }
    Column(
    ) {
        TextField(
            value = text,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentSize(),
            textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            onValueChange = { it ->
                text = it.filter { it == '0' || it == '1' }
            },
            label = { Text("Enter Binary") },
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentSize()
                .padding(10.dp),
        ) {
            Button(onClick = {/* To execute when button is clicked */ }) {
                Text("I'm a Compose Button")
            }
        }
        Text(
            text = "Text",
            fontWeight = FontWeight.Light,
            color = Color.Black,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentSize(),
            textAlign = TextAlign.Center,
            fontSize = 25.sp
        )
    }
}
