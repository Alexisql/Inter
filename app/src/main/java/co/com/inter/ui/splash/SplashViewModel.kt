package co.com.inter.ui.splash

import androidx.lifecycle.viewModelScope
import co.com.inter.domain.model.InitializationState
import co.com.inter.domain.usecase.InitializationUseCase
import co.com.inter.ui.splash.contract.SplashEffect
import co.com.inter.ui.util.BaseViewModel
import co.com.inter.ui.util.ResultState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val initializationUseCase: InitializationUseCase
) : BaseViewModel<ResultState<InitializationState>, SplashEffect>(ResultState.Idle) {

    init {
        initApp()
    }

    private fun initApp() {
        updateState(ResultState.Loading)
        viewModelScope.launch {
            initializationUseCase().onSuccess { state ->
                updateState(ResultState.Success(state))
                if (state == InitializationState.Success) {
                    emitEffect(SplashEffect.OnNavigate)
                }
            }.onFailure { exception ->
                updateState(ResultState.Idle)
                emitEffect(SplashEffect.ShowError(exception.message))
            }
        }
    }

}