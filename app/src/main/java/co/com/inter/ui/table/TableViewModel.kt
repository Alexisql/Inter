package co.com.inter.ui.table

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import co.com.inter.domain.usecase.GetTableUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TableViewModel @Inject constructor(
    private val getTableUseCase: GetTableUseCase
) : ViewModel() {

    val tables = getTableUseCase()
        .cachedIn(viewModelScope)
}