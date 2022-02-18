package com.example.mealz.ui.details

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.*
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.example.model.response.MealResponse
import java.lang.Float.min


@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun MealDetailsScreen(viewModel: MealsDetailsViewModel = viewModel()) {
    val meal = viewModel.mealState
//    var profilePictureState by remember {
//        mutableStateOf(MealProfilePictureState.Normal)
//    }
//    val transient = updateTransition(targetState = profilePictureState, label = "")
//    val imageSizeDp by transient.animateDp(
//        targetValueByState = { it.size },
//        label = ""
//    )
//    val color by transient.animateColor(
//        targetValueByState = { it.color },
//        label = ""
//    )
//    val widthSize by transient.animateDp(
//        targetValueByState = { it.borderWidth },
//        label = ""
//    )
    Surface(color = MaterialTheme.colors.background) {
        val scrollState = rememberLazyListState()
        val offset = min(
            1f, 1 - (scrollState.firstVisibleItemScrollOffset / 600f +
                    scrollState.firstVisibleItemIndex)
        )
        val size by animateDpAsState(targetValue = max(100.dp, (140.dp * offset)))
        Column() {
            Surface(elevation = 4.dp) {
                Row(modifier = Modifier.fillMaxWidth()) {
                    Card(
                        modifier = Modifier.padding(16.dp),
                        shape = CircleShape,
                        border = BorderStroke(width = 2.dp, color = Color.Blue)
                    ) {
                        Image(
                            painter = rememberImagePainter(
                                data = meal?.imageUrl,
                                builder = { transformations(CircleCropTransformation()) }
                            ),
                            contentDescription = null,
                            modifier = Modifier
                                .size(size)
                        )
                    }
                    Text(
                        text = meal?.name ?: "default name", modifier = Modifier
                            .padding(16.dp)
                            .align(Alignment.CenterVertically)
                    )
                }
            }
            val dummyContentList = (0..10).map { it.toString() }
            LazyColumn(state = scrollState, modifier = Modifier.fillMaxWidth()) {
                items(dummyContentList) { dummyItem ->
                    meal?.description?.let {
                        Text(
                            text = it, modifier = Modifier
                                .padding(start = 16.dp,top = 16.dp, end = 16.dp),
                            textAlign = TextAlign.Justify,
                            lineHeight = 25.sp,
                            style = MaterialTheme.typography.body1
                        )
                    }
                }
            }
        }
    }

}
//
//enum class MealProfilePictureState(val color: Color, val size: Dp, val borderWidth: Dp) {
//    Normal(Color.Magenta, 120.dp, 8.dp),
//    Expanded(Color.Green, 200.dp, 24.dp)
//}