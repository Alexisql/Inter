package co.com.inter.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.com.inter.domain.model.InitEvent
import co.com.inter.domain.usecase.InitializationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InitializationViewModel @Inject constructor(
    private val initializationUseCase: InitializationUseCase
) : ViewModel() {

    fun getAppVersion() {
        viewModelScope.launch {
            val response = initializationUseCase.invoke()
            response.events.forEach { event ->
                when (event) {
                    InitEvent.UpperApp -> {
                        Log.e("TAG", "UpperApp")
                    }

                    InitEvent.LowerApp -> {
                        Log.e("TAG", "LowerApp")
                    }

                    is InitEvent.Error -> {
                        Log.e("TAG", "Error")
                    }
                }
            }
        }
    }
}