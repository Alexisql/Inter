package co.com.inter.data.remote.auth.datasource

import co.com.inter.data.remote.auth.dto.UserResponseDto

interface IAuthRemoteDataSource {
    suspend fun login(): Result<UserResponseDto>
}