package com.iesam.androidtrainning.features.ex01.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iesam.androidtrainning.app.ErrorApp
import com.iesam.androidtrainning.features.ex01.domain.GetUserUseCase
import com.iesam.androidtrainning.features.ex01.domain.SaveUserUseCase
import com.iesam.androidtrainning.features.ex01.domain.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class Ex01FormViewModel(
    private val saveUserUseCase: SaveUserUseCase,
    private val getUserUseCase: GetUserUseCase
) : ViewModel() {

    private val _uiState = MutableLiveData<UiState>()
    val uiState: LiveData<UiState> = _uiState

    fun saveUser(name: String, surname: String, date: String) {
        saveUserUseCase(SaveUserUseCase.Input(name, surname, date)).fold(
            { responseError(it) },
            { responseSuccess(it) }
        )
    }

    fun loadUser() {
        //Main Thread
        _uiState.value = UiState(isLoading = true)
        viewModelScope.launch(Dispatchers.IO) {
            delay(5000)
            getUserUseCase().fold(
                { responseError(it) },
                { responseError(ErrorApp.UnknowError) }
            )
        }
    }

    private fun responseError(errorApp: ErrorApp) {
        _uiState.postValue(UiState(errorApp = errorApp, isLoading = false))
    }

    private fun responseSuccess(isOk: Boolean) {

    }

    private fun responseGetUserSuccess(user: User) {
        _uiState.postValue(UiState(user = user))
    }

    data class UiState(
        val errorApp: ErrorApp? = null,
        val isLoading: Boolean = false,
        val user: User? = null
    )
}