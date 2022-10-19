package com.example.diary.model

data class User(
    val id: Long,
    val photo: String?,
    val title: String,
    val content: String?,
    val epigraph: String?,
    val data: String,
    val editData: String?,
)
