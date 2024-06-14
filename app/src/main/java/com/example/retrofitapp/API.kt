package com.example.retrofitapp

import retrofit2.Call
import retrofit2.http.GET

interface API {
    @GET("posts")
    fun getPost(): Call<ArrayList<PostResponse>>
}