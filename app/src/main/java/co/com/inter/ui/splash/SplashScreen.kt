package co.com.inter.ui.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import co.com.inter.R
import co.com.inter.domain.model.InitializationState
import co.com.inter.ui.component.ErrorDialog
import co.com.inter.ui.component.ErrorHandler
import co.com.inter.ui.component.LocalErrorHandler
import co.com.inter.ui.component.SpacerComponent
import co.com.inter.ui.navigation.route.Route
import co.com.inter.ui.splash.contract.SplashEffect
import co.com.inter.ui.util.ResultState

@Composable
fun SplashScreen(
    splashViewModel: SplashViewModel = hiltViewModel(),
    navController: NavHostController
) {
    val state by splashViewModel.uiState.collectAsStateWithLifecycle()
    val errorHandler = LocalErrorHandler.current

    LaunchedEffect(Unit) {
        splashViewModel.effects.collect { effect ->
            when (effect) {
                is SplashEffect.ShowError -> {
                    errorHandler.showError(ErrorDialog(effect.message))
                }

                is SplashEffect.OnNavigate -> {
                    navController.navigate(Route.Home.route)
                }
            }
        }
    }

    HomeContent(
        state = state,
        errorHandler = errorHandler
    )
}

@Composable
private fun HomeContent(
    state: ResultState<InitializationState>,
    errorHandler: ErrorHandler
) {
    Scaffold(
        modifier = Modifier
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = Modifier.size(180.dp),
                painter = painterResource(id = R.drawable.ic_interrapidisimo),
                contentDescription = null
            )
            when (state) {
                is ResultState.Loading -> {
                    SpacerComponent(20)
                    CircularProgressIndicator()
                }

                is ResultState.Success -> {
                    when (state.data) {
                        InitializationState.LowerApp -> {
                            val message = ErrorDialog(
                                title = stringResource(R.string.title_app_lower),
                                message = stringResource(R.string.message_app_lower)
                            )
                            errorHandler.showError(message)
                        }

                        InitializationState.UpperApp -> {
                            val message = ErrorDialog(
                                title = stringResource(R.string.title_app_upper),
                                message = stringResource(R.string.message_app_upper)
                            )
                            errorHandler.showError(message)
                        }

                        else -> Unit
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
    HomeContent(ResultState.Loading, ErrorHandler())
}