package com.phani.tddsample.presentation.userlist

import com.phani.tddsample.domain.model.User

sealed class UserListUiState {
    data object Loading : UserListUiState()
    data class Success(val users: List<User>) : UserListUiState()
    data class Error(val message: String) : UserListUiState()
}
