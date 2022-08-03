package com.example.scaler_task.pojo

import java.io.Serializable

data class Videos(
    var url: String,
    var image: String,
    var playedCount: Int,
    var video_files: List<VideoFiles>
) : Serializable