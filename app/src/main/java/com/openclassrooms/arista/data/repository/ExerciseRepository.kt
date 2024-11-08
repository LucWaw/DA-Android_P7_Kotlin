package com.openclassrooms.arista.data.repository

import android.util.Log
import com.openclassrooms.arista.data.dao.ExerciseDtoDao
import com.openclassrooms.arista.domain.model.Exercise
import kotlinx.coroutines.flow.first



class ExerciseRepository(private val exerciseDao: ExerciseDtoDao) {


    // Get all exercises
    suspend fun getAllExercises(): List<Exercise> {
        try{
            return exerciseDao.getAllExercises()
                .first() // Collect the first emission of the Flow
                .map { Exercise.fromDto(it) } // Convert every DTO in Exercise
        }catch (e : Exception){
            Log.d("DatabaseError", "Erreur when retrive exercices")
        }
        return emptyList()
    }


    // Add a new exercise
    suspend fun addExercise(exercise: Exercise) {
        try {
            exerciseDao.insertExercise(exercise.toDto())
        } catch (e: Exception) {
            Log.d("DatabaseError", "Error when adding an exercise", e)
        }
    }

    // Delete an exercise
    suspend fun deleteExercise(exercise: Exercise) {
        try {
            // If there is no id, you can raise an exception and catch it in the use case and viewmodel
            exercise.id?.let {
                exerciseDao.deleteExerciseById(
                    id = exercise.id,
                )
            } ?: throw IllegalArgumentException("Exercise ID cannot be null")
        } catch (e: Exception) {
            Log.d("DatabaseError", "Error when deleting an exercise", e)
        }
    }
}