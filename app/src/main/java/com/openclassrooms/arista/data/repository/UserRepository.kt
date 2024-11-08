package com.openclassrooms.arista.data.repository

import android.util.Log
import com.openclassrooms.arista.data.dao.UserDtoDao
import com.openclassrooms.arista.domain.model.User

class UserRepository(private val userDtoDao: UserDtoDao) {

    suspend fun getUser(): User? {
        return try {
            // Get the user
            User.fromDto(userDtoDao.getUserById(1))
        } catch (e: Exception) {
            Log.d("DatabaseError", "Error when retrieving user", e)
            null // Return null if an exception occurs
        }
    }
}