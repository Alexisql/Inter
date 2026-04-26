package co.com.inter.ui.home

import androidx.lifecycle.viewModelScope
import co.com.inter.domain.model.User
import co.com.inter.domain.usecase.GetUserUseCase
import co.com.inter.ui.home.contract.HomeEffect
import co.com.inter.ui.util.BaseViewModel
import co.com.inter.ui.util.ResultState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val userUseCase: GetUserUseCase
) : BaseViewModel<ResultState<User>, HomeEffect>(ResultState.Idle) {

    init {
        getUser()
    }

    fun getUser() {
        updateState(ResultState.Loading)
        viewModelScope.launch {
            userUseCase().onSuccess {
                updateState(
                    ResultState.Success(it)
                )
            }.onFailure {
                emitEffect(HomeEffect.ShowError(it.message))
            }
        }
    }
}