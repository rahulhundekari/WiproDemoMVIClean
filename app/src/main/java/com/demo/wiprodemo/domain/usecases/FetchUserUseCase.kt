package com.demo.wiprodemo.domain.usecases

import com.demo.wiprodemo.data.entity.toUser
import com.demo.wiprodemo.domain.module.User
import com.demo.wiprodemo.domain.repository.user.UserRepository
import com.demo.wiprodemo.domain.utils.Result
import com.demo.wiprodemo.domain.utils.Result.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FetchUserUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke() : Flow<Result<List<User>>> = userRepository.fetchUser()
}