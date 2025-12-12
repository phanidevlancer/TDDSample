package com.phani.tddsample.domain.usecase

import com.phani.tddsample.domain.model.User
import com.phani.tddsample.domain.repository.UserRepository
import javax.inject.Inject

class GetUserDetailUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(userId: Int): Result<User> {
        return userRepository.getUserById(userId)
    }
}
