package com.example.scaler_task.repository

import com.example.scaler_task.pojo.VideosList
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface MainHttpInterface {

    @GET("popular")
    suspend fun getDataList(
        @Query("per_page") page: Int,
        @Header("Authorization") auth: String
    ): VideosList

}