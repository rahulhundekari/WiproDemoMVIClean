package com.demo.wiprodemo.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.demo.wiprodemo.domain.module.User
import com.demo.wiprodemo.domain.repository.user.UserRepository
import com.demo.wiprodemo.domain.usecases.FetchUserUseCase
import com.demo.wiprodemo.domain.utils.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ComposeViewModel @Inject constructor(
    private val fetchUserUseCase: FetchUserUseCase
) : ViewModel() {

    private val _userUiState = MutableStateFlow<UiState>(UiState.Loading)
    val userUiState = _userUiState.asStateFlow()

    init {
        viewModelScope.launch {
            fetchUserUseCase().collect { result ->
                when (result) {
                    is Result.Success -> {
                        _userUiState.value = UiState.Success(result.data)
                    }

                    is Result.Error -> {
                        _userUiState.value = UiState.Error(result.error.message ?: "Unknown Error")
                    }

                    Result.Loading -> {
                        _userUiState.value = UiState.Loading
                    }
                }

            }
        }


    }
}


sealed class UiState {
    data object Loading : UiState()

    data class Success(
        val users: List<User>
    ) : UiState()

    data class Error(
        val message: String
    ) : UiState()
}

