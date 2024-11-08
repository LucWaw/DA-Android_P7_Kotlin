package com.openclassrooms.arista.domain.model

import com.openclassrooms.arista.data.entity.UserDto

data class User(
    var name: String,
    var email: String
) {
    // Méthode pour convertir User en UserDto
    fun toDto(): UserDto {
        return UserDto(
            id = 0L, // Assigner 0 pour indiquer un nouvel utilisateur si l'id est inconnu
            name = name,
            email = email,
            motDePasse = "placeholder"
        )
    }

    companion object {
        // Méthode pour convertir UserDto en User
        fun fromDto(userDto: UserDto): User {
            return User(
                name = userDto.name,
                email = userDto.email
            )
        }
    }
}