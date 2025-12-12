package com.phani.tddsample.presentation.userdetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.phani.tddsample.domain.usecase.GetUserDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserDetailViewModel @Inject constructor(
    private val getUserDetailUseCase: GetUserDetailUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _uiState = MutableStateFlow<UserDetailUiState>(UserDetailUiState.Loading)
    val uiState: StateFlow<UserDetailUiState> = _uiState.asStateFlow()

    init {
        val userId = savedStateHandle.get<String>("userId")?.toIntOrNull()
        userId?.let { loadUserDetail(it) }
    }

    private fun loadUserDetail(userId: Int) {
        viewModelScope.launch {
            _uiState.value = UserDetailUiState.Loading
            getUserDetailUseCase(userId)
                .onSuccess { user ->
                    _uiState.value = UserDetailUiState.Success(user)
                }
                .onFailure { error ->
                    _uiState.value = UserDetailUiState.Error(
                        error.message ?: "Unknown error occurred"
                    )
                }
        }
    }
}
