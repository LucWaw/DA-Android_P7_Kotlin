package com.openclassrooms.arista.domain.model

import com.openclassrooms.arista.data.entity.ExerciseDto
import java.time.LocalDateTime
import java.time.ZoneOffset

data class Exercise(
    val id: Long? = null,
    var startTime: LocalDateTime,
    var duration: Int,
    var category: ExerciseCategory,
    var intensity: Int
){
    fun toDto(): ExerciseDto {
        return ExerciseDto(
            id = id ?: 0L, // Remettre 0 si `id` est `null` pour auto-génération
            startTime = startTime.toEpochSecond(ZoneOffset.UTC),
            duration = duration,
            category = category.name,
            intensity = intensity
        )
    }


    companion object {
        fun fromDto(exercice : ExerciseDto) : Exercise{
            return Exercise(
                id = if (exercice.id == 0L) null else exercice.id, // Si `id` est 0, cela signifie qu'il est nouveau
                startTime = LocalDateTime.ofEpochSecond(exercice.startTime, 0, ZoneOffset.UTC),
                duration = exercice.duration,
                category = ExerciseCategory.valueOf(exercice.category),
                intensity = exercice.intensity
            )
        }
    }
}