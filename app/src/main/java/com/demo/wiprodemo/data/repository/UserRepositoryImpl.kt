package com.demo.wiprodemo.data.repository

import com.demo.wiprodemo.data.api.UserApi
import com.demo.wiprodemo.data.entity.toUser
import com.demo.wiprodemo.domain.module.User
import com.demo.wiprodemo.domain.repository.user.UserRepository
import com.demo.wiprodemo.domain.utils.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userApi: UserApi
) : UserRepository {
    override suspend fun fetchUser(): Flow<Result<List<User>>> = flow {
        try {
            emit(Result.Loading)
            val res = userApi.fetchUser()
            emit(Result.Success(res.map { it.toUser() }))
        } catch (e: Exception) {
            emit(Result.Error(e))
        }
    }
}

