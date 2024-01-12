package theme

import androidx.compose.material.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import expect_actuals.font

@Composable
fun getTypography(): Typography {
    val Montserrat = FontFamily(
        font(
            name = "Mons Black",
            res = "mons_black",
            weight = FontWeight.Black,
            style = FontStyle.Normal
        ),
        font(
            name = "Mons Bold",
            res = "mons_bold",
            weight = FontWeight.Bold,
            style = FontStyle.Normal
        ),
        font(
            name = "Mons Semibold",
            res = "mons_semibold",
            weight = FontWeight.SemiBold,
            style = FontStyle.Normal
        ),
        font(
            name = "Mons Regular",
            res = "mons_regular",
            weight = FontWeight.Normal,
            style = FontStyle.Normal
        ),
        font(
            name = "Mons Medium",
            res = "mons_medium",
            weight = FontWeight.Medium,
            style = FontStyle.Normal
        ),
        font(
            name = "Mons ExtraLight",
            res = "mons_extralight",
            weight = FontWeight.Light,
            style = FontStyle.Normal
        ),
    )

    return Typography(
        h1 = TextStyle(
            fontFamily = Montserrat,
            fontSize = 38.sp,
            fontWeight = FontWeight.Bold,
        ),
        h2 = TextStyle(
            fontFamily = Montserrat,
            fontSize = 32.sp,
            fontWeight = FontWeight.SemiBold,
        ),
        h3 = TextStyle(
            fontFamily = Montserrat,
            fontSize = 26.sp,
            fontWeight = FontWeight.SemiBold,
        ),
        h4 = TextStyle(
            fontFamily = Montserrat,
            fontSize = 20.sp,
            fontWeight = FontWeight.Medium,
        ),
        h5 = TextStyle(
            fontFamily = Montserrat,
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
        ),
        h6 = TextStyle(
            fontFamily = Montserrat,
            fontSize = 12.sp,
            fontWeight = FontWeight.Light,
        ),
        // Used
        subtitle1 = TextStyle(
            fontFamily = Montserrat,
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
        ),
        // Used
        subtitle2 = TextStyle(
            fontFamily = Montserrat,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
        ),
        //
        body1 = TextStyle(
            fontFamily = Montserrat,
            fontSize = 14.sp,
            fontWeight = FontWeight.Normal,
        ),
        // Used Do't Modify
        body2 = TextStyle(
            fontFamily = Montserrat,
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium,
        ),
        button = TextStyle(
            fontFamily = Montserrat,
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold,
        ),
        caption = TextStyle(
            fontFamily = Montserrat,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            lineHeight = 16.sp,
            letterSpacing = 0.sp,
        ),
        overline = TextStyle(
            fontFamily = Montserrat,
            fontSize = 12.sp,
            fontWeight = FontWeight.SemiBold,
            lineHeight = 16.sp,
            letterSpacing = 0.sp,
        ),
    )

}


