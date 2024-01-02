import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Tab
import androidx.compose.material.TabPosition
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.zIndex


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun LoginSignupScreen(viewModel: AuthViewModel) {

    val viewStates = viewModel.viewStates.collectAsState()
    var selectedForm by remember {
        mutableStateOf(FormType.LOGIN)
    }

    var selectedTabIndex by remember {
        mutableStateOf(0)
    }

    val pagerState = rememberPagerState(
        initialPage = 0,
        initialPageOffsetFraction = 0f
    ) { 2 }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(Modifier.fillMaxWidth(0.9f)) {
            Card(modifier = Modifier.fillMaxSize(), elevation = 10.dp) {
                Row(modifier = Modifier.fillMaxWidth()) {
                    TabRow(
                        indicator = @Composable {
                            CustomIndicator(tabPositions = it, selectedTabIndex = selectedTabIndex)
                        },
                        divider = @Composable {
                            Spacer(Modifier.weight(1f))
                        },
                        backgroundColor = Color.Transparent,
                        selectedTabIndex = selectedTabIndex
                    ) {
                        val isLoginTabSelected = selectedForm == FormType.LOGIN
                        Tab(selected = isLoginTabSelected, onClick = {
                            selectedTabIndex = 0
                            selectedForm = FormType.LOGIN
                        }) {
                            Text(
                                text = "Login",
                                fontSize = 18.sp,
                                modifier = Modifier.padding(10.dp),
                                color = if (isLoginTabSelected) Color.Red else Color.Black
                            )
                        }

                        Tab(selected = !isLoginTabSelected, onClick = {
                            selectedTabIndex = 1
                            selectedForm = FormType.SIGNUP
                        }) {
                            Text(
                                text = "Signup",
                                fontSize = 18.sp,
                                modifier = Modifier.padding(10.dp),
                                color = if (!isLoginTabSelected) Color.Red else Color.Black
                            )
                        }
                    }
                }

                HorizontalPager(state = pagerState) {
                    for (i in 0 until 2) {
                        if (i == 0) {
                            LoginPage(viewStates.value, viewModel::setStateEvents)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun LoginPage(loginViewStates: AuthViewStates, setStateEvents: (AuthStateEvents) -> Unit) {
    Column {
        TextField(value = loginViewStates.loginEmail ?: "", onValueChange = {
            setStateEvents(
                AuthStateEvents.UpdateViewStates(
                    authViewStates = AuthViewStates(
                        loginEmail = it
                    )
                )
            )
        })
    }
}


enum class FormType {
    LOGIN, SIGNUP
}

@Composable
private fun CustomIndicator(tabPositions: List<TabPosition>, selectedTabIndex: Int) {
    val transition = updateTransition(selectedTabIndex)
    val indicatorStart by transition.animateDp(
        transitionSpec = {
            if (initialState < targetState) {
                spring(dampingRatio = 1f, stiffness = 50f)
            } else {
                spring(dampingRatio = 1f, stiffness = 800f)
            }
        }, label = ""
    ) {
        tabPositions[selectedTabIndex].left
    }

    val indicatorEnd by transition.animateDp(
        transitionSpec = {
            if (initialState < targetState) {
                spring(dampingRatio = 1f, stiffness = 800f)
            } else {
                spring(dampingRatio = 1f, stiffness = 50f)
            }
        }, label = ""
    ) {
        tabPositions[selectedTabIndex].right
    }

    Box(
        Modifier
            .offset(x = indicatorStart)
            .wrapContentSize(align = Alignment.BottomStart)
            .width(indicatorEnd - indicatorStart)
            .padding(2.dp)
            .fillMaxSize()
            .background(color = Color.White, RoundedCornerShape(50))
            .border(BorderStroke(2.dp, Color(0xFFC13D25)), RoundedCornerShape(50))
            .zIndex(-1f)

    )
}