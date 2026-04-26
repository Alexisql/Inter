package co.com.inter.ui.location

import androidx.lifecycle.viewModelScope
import co.com.inter.domain.model.Location
import co.com.inter.domain.usecase.GetLocationUseCase
import co.com.inter.ui.util.BaseViewModel
import co.com.inter.ui.util.ResultState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LocationViewModel @Inject constructor(
    private val locationUseCase: GetLocationUseCase
) : BaseViewModel<ResultState<List<Location>>, Unit>(ResultState.Idle) {

    init {
        getLocations()
    }

    private fun getLocations() {
        updateState(ResultState.Loading)
        viewModelScope.launch {
            locationUseCase().onSuccess { locations ->
                updateState(ResultState.Success(locations))
            }
        }
    }

}