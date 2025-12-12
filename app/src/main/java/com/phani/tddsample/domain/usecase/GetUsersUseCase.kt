package com.phani.tddsample.domain.usecase

import com.phani.tddsample.domain.model.User
import com.phani.tddsample.domain.repository.UserRepository
import javax.inject.Inject

class GetUsersUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(): Result<List<User>> {
        return userRepository.getUsers()
    }
}
