package com.iesam.androidtrainning.features.ex01.data.local

import android.content.Context
import com.google.gson.Gson
import com.iesam.androidtrainning.app.ErrorApp
import com.iesam.androidtrainning.features.ex01.domain.SaveUserUseCase
import com.iesam.androidtrainning.features.ex01.domain.User
import com.iesam.kotlintrainning.Either
import com.iesam.kotlintrainning.left
import com.iesam.kotlintrainning.right

class XmlLocalDataSource(private val context: Context) {

    private val sharedPref = context.getSharedPreferences("users", Context.MODE_PRIVATE)

    private val gson = Gson()

    fun saveUser(input: SaveUserUseCase.Input): Either<ErrorApp, Boolean> {
        return try {
            with(sharedPref.edit()) {
                val id = (1..100).random()
                val user = User(id, input.username, input.surname, input.age)
                val jsonUser = gson.toJson(user, User::class.java)
                putString(id.toString(), jsonUser)
                apply()
            }
            true.right()
        } catch (ex: Exception) {
            ErrorApp.UnknowError.left()
        }
    }

    fun findUser(userId: Int = 0): Either<ErrorApp, User> {
        return try {
            val jsonUser = sharedPref.getString(userId.toString(), "{}")
            return gson.fromJson(jsonUser, User::class.java).right()
        } catch (ex: Exception) {
            return ErrorApp.UnknowError.left()
        }
    }

    fun removeUserById(userId: Int) {
        sharedPref.edit().remove(userId.toString()).apply()
    }

    fun getAll(): List<User> {
        val mapUsers = sharedPref.all as Map<String, String>
        return mapUsers.values.map { jsonUser ->
            gson.fromJson(jsonUser, User::class.java)
        }
    }
}