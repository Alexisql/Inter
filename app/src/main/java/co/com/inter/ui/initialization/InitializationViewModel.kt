package co.com.inter.ui.initialization

import androidx.lifecycle.viewModelScope
import co.com.inter.domain.model.InitializationState
import co.com.inter.domain.usecase.InitializationUseCase
import co.com.inter.ui.initialization.contract.InitializationEffect
import co.com.inter.ui.util.BaseViewModel
import co.com.inter.ui.util.ResultState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InitializationViewModel @Inject constructor(
    private val initializationUseCase: InitializationUseCase
) : BaseViewModel<ResultState<InitializationState>, InitializationEffect>(ResultState.Idle) {

    init {
        initApp()
    }

    private fun initApp() {
        updateState(ResultState.Loading)
        viewModelScope.launch {
            initializationUseCase().onSuccess { state ->
                updateState(ResultState.Success(state))
                if (state == InitializationState.Success) {
                    emitEffect(InitializationEffect.OnNavigate)
                }
            }.onFailure { exception ->
                updateState(ResultState.Idle)
                emitEffect(InitializationEffect.ShowError(exception.message))
            }
        }
    }

}