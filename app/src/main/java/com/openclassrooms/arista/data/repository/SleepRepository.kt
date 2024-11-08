package com.openclassrooms.arista.data.repository

import android.util.Log
import com.openclassrooms.arista.data.dao.SleepDtoDao
import com.openclassrooms.arista.domain.model.Sleep
import kotlinx.coroutines.flow.first

class SleepRepository(private val sleepDao: SleepDtoDao) {

    // Get all sleep records
    suspend fun getAllSleeps(): List<Sleep> {
        try {
            return sleepDao.getAllSleeps()
                .first() // Collect the first emission of the Flow
                .map { Sleep.fromDto(it) } // Convert every DTO in Sleep
        } catch (e: Exception) {
            Log.d("DatabaseError", "Error when retrieving sleep records", e)
        }
        return emptyList()
    }

}