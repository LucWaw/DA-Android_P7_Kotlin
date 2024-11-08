package com.openclassrooms.arista.domain.model

import com.openclassrooms.arista.data.entity.SleepDto
import java.time.LocalDateTime
import java.time.ZoneOffset

data class Sleep(
    @JvmField var startTime: LocalDateTime,
    var duration: Int,
    var quality: Int
) {
    // Méthode pour convertir Sleep en SleepDto
    fun toDto(): SleepDto {
        return SleepDto(
            id = 0L, // Mettre un id à 0 pour l’auto-génération
            startTime = startTime.toEpochSecond(ZoneOffset.UTC),
            duration = duration,
            quality = quality
        )
    }

    companion object {
        // Fonction pour convertir SleepDto en Sleep
        fun fromDto(sleepDto: SleepDto): Sleep {
            return Sleep(
                startTime = LocalDateTime.ofEpochSecond(sleepDto.startTime, 0, ZoneOffset.UTC),
                duration = sleepDto.duration,
                quality = sleepDto.quality
            )
        }
    }
}
