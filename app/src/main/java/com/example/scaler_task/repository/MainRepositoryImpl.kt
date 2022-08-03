package com.example.scaler_task.repository

import com.example.scaler_task.constants.Constants
import com.example.scaler_task.pojo.VideosList

class MainRepositoryImpl(private val mainHttpInterface: MainHttpInterface) : MainRepository {


    override suspend fun getVideoList(pageSize:Int): VideosList {
        return mainHttpInterface.getDataList(pageSize, Constants.AUTHRIZATION_KEY)
    }

}