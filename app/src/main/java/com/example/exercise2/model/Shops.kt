package com.example.exercise2.model

data class Shops(
    val developerMessage: Any,
    val errors: List<Any>,
    val httpStatusCode: Int,
    val shops: List<Shop>,
    val success: Boolean,
    val userMessage: Any
)