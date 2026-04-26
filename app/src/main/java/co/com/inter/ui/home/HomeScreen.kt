package co.com.inter.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import co.com.inter.R
import co.com.inter.domain.model.User
import co.com.inter.ui.component.ErrorDialog
import co.com.inter.ui.component.LocalErrorHandler
import co.com.inter.ui.home.contract.HomeEffect
import co.com.inter.ui.home.contract.HomeIntent
import co.com.inter.ui.navigation.route.Route
import co.com.inter.ui.util.ResultState

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel = hiltViewModel(),
    navController: NavHostController
) {
    val state by homeViewModel.uiState.collectAsStateWithLifecycle()
    val errorHandler = LocalErrorHandler.current

    LaunchedEffect(Unit) {
        homeViewModel.effects.collect { effect ->
            when (effect) {
                is HomeEffect.ShowError -> {
                    errorHandler.showError(ErrorDialog(effect.message))
                }

                is HomeEffect.NavigateToTables -> {
                    navController.navigate(Route.Table.route)
                }

                HomeEffect.NavigateToLocations -> {
                    navController.navigate(Route.Location.route)
                }
            }
        }
    }

    HomeContent(state) {
        homeViewModel.onIntent(it)
    }
}

@Composable
private fun HomeContent(
    state: ResultState<User>,
    onIntent: (HomeIntent) -> Unit
) {
    Scaffold(
        modifier = Modifier,
        bottomBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .navigationBarsPadding(),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    onClick = { onIntent(HomeIntent.NavigateToTables) },
                    modifier = Modifier.weight(1f)
                ) {
                    Text(stringResource(R.string.label_table))
                }
                Button(
                    onClick = { onIntent(HomeIntent.NavigateToLocations) },
                    modifier = Modifier.weight(1f)
                ) {
                    Text(stringResource(R.string.label_location))
                }
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            when (state) {
                is ResultState.Loading -> {
                    CircularProgressIndicator()
                }

                is ResultState.Success -> {
                    val user = state.data
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                            .height(120.dp),
                        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(12.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Image(
                                imageVector = Icons.Default.AccountCircle,
                                contentDescription = null,
                                modifier = Modifier
                                    .size(80.dp)
                                    .clip(CircleShape),
                                contentScale = ContentScale.Crop
                            )

                            Spacer(modifier = Modifier.width(16.dp))

                            Column(
                                modifier = Modifier.fillMaxHeight(),
                                verticalArrangement = Arrangement.Center
                            ) {
                                Text(
                                    text = "Nombre: ${user?.name}",
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Bold
                                )
                                Text(
                                    text = "Usuario: ${user?.user}",
                                    fontSize = 14.sp,
                                    color = Color.Gray
                                )
                                Text(
                                    text = "ID: ${user?.id}",
                                    fontSize = 14.sp,
                                    color = Color.Gray
                                )
                            }
                        }
                    }
                }

                else -> Unit
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun HomeContentPreview() {
    HomeContent(ResultState.Loading){}
}