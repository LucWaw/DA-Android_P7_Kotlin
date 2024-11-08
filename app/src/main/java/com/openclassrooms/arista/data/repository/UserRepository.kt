package com.openclassrooms.arista.data.repository

import com.openclassrooms.arista.data.dao.UserDtoDao
import com.openclassrooms.arista.domain.model.User

class UserRepository(private val userDtoDao: UserDtoDao) {

    suspend fun getUser() : User{
        return User.fromDto(userDtoDao.getUserById(1))
    }
}