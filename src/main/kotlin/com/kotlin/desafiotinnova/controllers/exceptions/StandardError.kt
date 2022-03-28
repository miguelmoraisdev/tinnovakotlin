package com.kotlin.desafiotinnova.controllers.exceptions

import java.time.LocalDateTime

data class StandardError(
    val timestamp: LocalDateTime,
    val status: Int,
    val message: String,
    val path: String
)
