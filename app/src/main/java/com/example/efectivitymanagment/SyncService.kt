package com.example.efectivitymanagment

import SyncResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface SyncService {
    @GET("/welcome")
    fun hello(): Call<SyncResponse>
}

