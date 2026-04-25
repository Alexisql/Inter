package co.com.inter.data.remote.login.datasource

import co.com.inter.data.remote.login.dto.UserResponseDto

interface ILoginRemoteDataSource {
    suspend fun login(): Result<UserResponseDto>
}