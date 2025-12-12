package com.phani.tddsample.data.repository

import com.phani.tddsample.data.remote.api.UserApiService
import com.phani.tddsample.data.remote.dto.toDomain
import com.phani.tddsample.domain.model.User
import com.phani.tddsample.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val apiService: UserApiService
) : UserRepository {

    override suspend fun getUsers(): Result<List<User>> {
        return try {
            val users = apiService.getUsers().map { it.toDomain() }
            Result.success(users)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getUserById(userId: Int): Result<User> {
        return try {
            val user = apiService.getUserById(userId).toDomain()
            Result.success(user)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
