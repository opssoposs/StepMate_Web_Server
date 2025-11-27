package com.example.demo.api

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDate
import java.time.LocalDateTime

@Tag(name = "StepMate API", description = "ERD 기반 스웨거 명세 예시")
@RestController
@RequestMapping("/api")
class ApiDocumentationController {

    @Operation(
        summary = "사용자 목록 조회",
        description = "모든 사용자를 반환합니다.",
        responses = [
            ApiResponse(
                responseCode = "200",
                description = "성공",
                content = [Content(mediaType = "application/json", schema = Schema(implementation = User::class))]
            )
        ]
    )
    @GetMapping("/users")
    fun listUsers(): List<User> = listOf(
        User(1, "관리자", "admin@example.com", "******", 30, UserRole.ADMIN),
        User(2, "홍길동", "user@example.com", "******", 25, UserRole.USER)
    )

    @Operation(
        summary = "카테고리 등록",
        description = "새로운 카테고리를 생성합니다.",
        responses = [
            ApiResponse(
                responseCode = "201",
                description = "생성됨",
                content = [Content(mediaType = "application/json", schema = Schema(implementation = Category::class))]
            )
        ]
    )
    @PostMapping("/categories")
    fun createCategory(@RequestBody category: Category): ResponseEntity<Category> =
        ResponseEntity.status(HttpStatus.CREATED).body(category)

    @Operation(
        summary = "미션 목록 조회",
        description = "카테고리별 미션 템플릿을 조회합니다.",
        responses = [
            ApiResponse(
                responseCode = "200",
                description = "성공",
                content = [Content(mediaType = "application/json", schema = Schema(implementation = Mission::class))]
            )
        ]
    )
    @GetMapping("/missions")
    fun listMissions(): List<Mission> = listOf(
        Mission(1, 1, "주간 10km", "일주일 동안 10km 걷기", "DISTANCE", true)
    )

    @Operation(
        summary = "사용자 미션 진행률 조회",
        description = "특정 사용자의 미션 할당 정보를 반환합니다.",
        responses = [
            ApiResponse(
                responseCode = "200",
                description = "성공",
                content = [Content(mediaType = "application/json", schema = Schema(implementation = UserMission::class))]
            )
        ]
    )
    @GetMapping("/user-missions")
    fun listUserMissions(): List<UserMission> = listOf(
        UserMission(
            id = 1,
            userId = 2,
            missionId = 1,
            progress = 0.4,
            isCleared = false,
            startDate = LocalDateTime.now().minusDays(2),
            endDate = null
        )
    )

    @Operation(
        summary = "산책 기록 생성",
        description = "산책 기록을 저장하고 반환합니다.",
        responses = [
            ApiResponse(
                responseCode = "201",
                description = "생성됨",
                content = [Content(mediaType = "application/json", schema = Schema(implementation = WalkNote::class))]
            )
        ]
    )
    @PostMapping("/walk-notes")
    fun createWalkNote(@RequestBody note: WalkNote): ResponseEntity<WalkNote> =
        ResponseEntity.status(HttpStatus.CREATED).body(note)

    @Operation(
        summary = "캘린더 기록 조회",
        description = "유저의 월/일 단위 산책 기록 연결 상태를 조회합니다.",
        responses = [
            ApiResponse(
                responseCode = "200",
                description = "성공",
                content = [Content(mediaType = "application/json", schema = Schema(implementation = CalendarEntry::class))]
            )
        ]
    )
    @GetMapping("/calendar")
    fun getCalendar(): List<CalendarEntry> = listOf(
        CalendarEntry(
            userId = 2,
            month = LocalDate.now().withDayOfMonth(1),
            day = LocalDate.now(),
            year = LocalDate.now().year,
            walkNote = 1
        )
    )
}
