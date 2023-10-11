package com.iesam.androidtrainning.features.ex02.domain

import com.iesam.androidtrainning.app.ErrorApp
import com.iesam.kotlintrainning.Either

class GetDogUseCase(private val repository: DogRepository) {

    operator fun invoke(): Either<ErrorApp, Dog> {
        return repository.findDog()
    }
}