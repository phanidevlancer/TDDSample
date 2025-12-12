package com.phani.tddsample.presentation.userdetail

import com.phani.tddsample.domain.model.User

sealed class UserDetailUiState {
    data object Loading : UserDetailUiState()
    data class Success(val user: User) : UserDetailUiState()
    data class Error(val message: String) : UserDetailUiState()
}
