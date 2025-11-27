package com.example.demo.api

import java.time.LocalDate
import java.time.LocalDateTime

/**
 * 단순 스키마 표현을 위한 DTO.
 */
data class User(
    val id: Int,
    val name: String,
    val email: String,
    val password: String,
    val age: Int,
    val role: UserRole
)

enum class UserRole {
    ADMIN,
    USER
}

data class Category(
    val id: Int,
    val name: String,
    val description: String,
    val isActive: Boolean
)

data class Mission(
    val id: Int,
    val categoryId: Int,
    val name: String,
    val description: String,
    val missionType: String,
    val isActive: Boolean
)

data class UserMission(
    val id: Int,
    val userId: Int,
    val missionId: Int,
    val progress: Double,
    val isCleared: Boolean,
    val startDate: LocalDateTime,
    val endDate: LocalDateTime?
)

data class WalkNote(
    val id: Int,
    val userId: Int,
    val walkDate: LocalDate,
    val description: String,
    val totalDistance: Double,
    val totalTime: Double,
    val totalWalkCount: Int
)

data class CalendarEntry(
    val userId: Int,
    val month: LocalDate,
    val day: LocalDate,
    val year: Int,
    val walkNote: Int?
)
