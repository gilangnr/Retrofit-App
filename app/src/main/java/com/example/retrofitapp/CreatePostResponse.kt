package com.example.retrofitapp

data class CreatePostResponse (
    val userId: String,
    val id: Int,
    val title: String?,
    val body: String?
)