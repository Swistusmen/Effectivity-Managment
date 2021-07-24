package com.example.efectivitymanagment

class SyncRepository constructor(private val server: SyncService){

    suspend fun checkSynchro():SyncResponse{
        return  server.hello();
    }

}