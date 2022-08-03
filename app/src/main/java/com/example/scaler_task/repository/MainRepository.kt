package com.example.scaler_task.repository

import com.example.scaler_task.pojo.VideosList

interface MainRepository {
    suspend fun getVideoList(pageSize:Int): VideosList
}