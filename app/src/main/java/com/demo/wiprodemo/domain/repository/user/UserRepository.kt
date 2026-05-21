package com.demo.wiprodemo.domain.repository.user

import com.demo.wiprodemo.domain.module.User
import com.demo.wiprodemo.domain.utils.Result
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun fetchUser(): Flow<Result<List<User>>>
}