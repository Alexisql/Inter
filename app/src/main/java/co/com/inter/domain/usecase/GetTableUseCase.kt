package co.com.inter.domain.usecase

import androidx.paging.PagingData
import co.com.inter.domain.model.Table
import co.com.inter.domain.repository.ITableRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTableUseCase @Inject constructor(
    private val repository: ITableRepository
) {
    operator fun invoke(): Flow<PagingData<Table>> = repository.getTable()
}