package com.openclassrooms.arista.data.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.openclassrooms.arista.data.entity.UserDto
import kotlinx.coroutines.flow.Flow


@Dao
interface UserDtoDao {
    @Upsert
    suspend fun addUser(User: UserDto): Long


    @Query("SELECT * FROM user")
    fun getAllUsers(): Flow<List<UserDto>>

    @Query("SELECT * FROM user WHERE id = :id")
    suspend fun getUserById(id: Long): UserDto


    @Query("DELETE FROM user WHERE id = :id")
    suspend fun deleteUserById(id: Long)
}