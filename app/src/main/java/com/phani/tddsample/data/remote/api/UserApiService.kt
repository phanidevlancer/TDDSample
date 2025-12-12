package com.phani.tddsample.data.remote.api

import com.phani.tddsample.data.remote.dto.UserDto
import retrofit2.http.GET
import retrofit2.http.Path

interface UserApiService {

    @GET("users")
    suspend fun getUsers(): List<UserDto>

    @GET("users/{id}")
    suspend fun getUserById(@Path("id") userId: Int): UserDto
}
