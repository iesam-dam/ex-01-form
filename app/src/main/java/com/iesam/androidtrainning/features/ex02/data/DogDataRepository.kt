package com.iesam.androidtrainning.features.ex02.data

import com.iesam.androidtrainning.app.ErrorApp
import com.iesam.androidtrainning.features.ex02.domain.Dog
import com.iesam.androidtrainning.features.ex02.domain.DogRepository
import com.iesam.kotlintrainning.Either
import com.iesam.kotlintrainning.left

class DogDataRepository : DogRepository {
    override fun findDog(): Either<ErrorApp, Dog> {
        //Si está en local, devuelvo lo de local.
        //Sino, voy a red, lo descargo y lo guardo en local.
        //Devuelvo lo de red.
        return ErrorApp.UnknowError.left()
    }
}