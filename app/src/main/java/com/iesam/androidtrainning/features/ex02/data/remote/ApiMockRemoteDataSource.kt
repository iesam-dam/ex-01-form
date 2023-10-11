package com.iesam.androidtrainning.features.ex02.data.remote

import com.iesam.androidtrainning.app.ErrorApp
import com.iesam.androidtrainning.features.ex02.domain.Dog
import com.iesam.kotlintrainning.Either
import com.iesam.kotlintrainning.right
import java.util.Date

class ApiMockRemoteDataSource {

    fun getDogMock(): Either<ErrorApp, Dog> {
        return Dog("Rami", "Un diamante por pulir", "hembra", Date()).right()
    }
}