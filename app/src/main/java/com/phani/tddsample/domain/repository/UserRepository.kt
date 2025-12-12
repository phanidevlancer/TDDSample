package com.phani.tddsample.domain.repository

import com.phani.tddsample.domain.model.User

interface UserRepository {
    suspend fun getUsers(): Result<List<User>>
    suspend fun getUserById(userId: Int): Result<User>
}
