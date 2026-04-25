package co.com.inter.domain.repository

interface ILoginRepository {
    suspend fun login(): Result<Unit>
}