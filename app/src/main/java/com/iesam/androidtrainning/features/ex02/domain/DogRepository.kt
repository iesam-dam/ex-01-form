package com.iesam.androidtrainning.features.ex02.domain

import com.iesam.androidtrainning.app.ErrorApp
import com.iesam.kotlintrainning.Either

interface DogRepository {
    fun findDog(): Either<ErrorApp, Dog>
}