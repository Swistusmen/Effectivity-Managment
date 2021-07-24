package com.example.efectivitymanagment

import retrofit2.http.GET
import retrofit2.http.Query

interface SyncService {
    @GET("/welcome_message")
    fun hello(): SyncResponse
}

