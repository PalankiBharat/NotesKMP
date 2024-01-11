package presentation.Auth

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import expect_actuals.KMPToast
import org.koin.compose.koinInject
import presentation.Notes.NotesScreen
import theme.darkColorBackground
import theme.themeYellow
import theme.unHighlightedTextColor


class AuthScreen:Screen{
    @Composable
    override fun Content() {

        LoginSignupScreen()
    }

}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun LoginSignupScreen() {
    val viewModel = koinInject<AuthViewModel>()
    val viewStates = viewModel.viewStates.collectAsState()
    var selectedForm by remember {
        mutableStateOf(FormType.LOGIN)
    }
    val navigator = LocalNavigator.currentOrThrow
    LaunchedEffect(viewStates.value) {
        viewStates.value.message?.let {
            KMPToast().showToast(it)
            viewModel.resetErrorState()
        }
        viewStates.value.loginResponseToken?.let {
            navigator.push(NotesScreen())
        }
    }

    val (selectedTabIndex, onSelectedTabchange) = remember {
        mutableStateOf(0)
    }

    val pagerState = rememberPagerState(
        initialPage = 0,
        initialPageOffsetFraction = 0f,
        pageCount = { 2 }
    )

    LaunchedEffect(selectedTabIndex) {
        when (selectedTabIndex) {
            1 -> {
                selectedForm = FormType.SIGNUP
            }
            0 -> {
                selectedForm = FormType.LOGIN
            }
        }
        pagerState.animateScrollToPage(selectedTabIndex)
    }

    Box(
        modifier = Modifier.fillMaxSize().background(darkColorBackground)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(modifier = Modifier.fillMaxWidth()) {
                TabRow(
                    modifier = Modifier.padding(bottom = 40.dp),
                    indicator = @Composable {
                        Box(
                            modifier = Modifier
                                .tabIndicatorOffset(it[selectedTabIndex])
                                .height(4.dp)
                                .padding(horizontal = 40.dp)
                                .background(
                                    color = themeYellow,
                                    shape = RoundedCornerShape(8.dp)
                                )
                        )
                    },
                    divider = @Composable {
                        Spacer(Modifier.weight(1f))
                    },
                    backgroundColor = Color.Transparent,
                    selectedTabIndex = selectedTabIndex
                ) {
                    val isLoginTabSelected = selectedForm == FormType.LOGIN
                    Tab(
                        modifier = Modifier,
                        selected = isLoginTabSelected,
                        onClick = {
                            onSelectedTabchange(0)
                        }) {
                        Text(
                            text = "Login",
                            fontSize = 18.sp,
                            modifier = Modifier.padding(10.dp),
                            color = if (isLoginTabSelected) themeYellow else unHighlightedTextColor
                        )
                    }

                    Tab(
                        selected = !isLoginTabSelected,
                        onClick = {
                            onSelectedTabchange(1)
                        }
                    ) {
                        Text(
                            text = "Signup",
                            fontSize = 18.sp,
                            modifier = Modifier.padding(10.dp),
                            color = if (!isLoginTabSelected) themeYellow else unHighlightedTextColor
                        )
                    }
                }
            }
            HorizontalPager(
                state = pagerState,
                userScrollEnabled = false,
                beyondBoundsPageCount = 0,
                verticalAlignment = Alignment.Top
            ) { page ->
                when (page) {
                    0 -> {
                        LoginPage(viewStates.value, viewModel::setStateEvents)
                    }

                    1 -> {
                        SignUpPage(viewStates.value, viewModel::setStateEvents)
                    }
                }
            }
        }
    }
}


enum class FormType {
    LOGIN, SIGNUP
}

