package com.example.scaler_task.viewModel

import android.text.TextUtils
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.scaler_task.pojo.Resource
import com.example.scaler_task.pojo.VideosList
import com.example.scaler_task.repository.MainRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


const val pageSize = 15

class MainViewModel
@ViewModelInject
constructor(private val mainRepository: MainRepository) : ViewModel() {
    var videoList: VideosList = VideosList(null)
    var title: MutableLiveData<String> = MutableLiveData()
    var imageUrl: MutableLiveData<String> = MutableLiveData()
    var videoUrl: MutableLiveData<String> = MutableLiveData()


    fun getVideoList() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(
                Resource.success(
                    data = mainRepository.getVideoList(pageSize)
                )
            )

        } catch (exception: Exception) {
            emit(Resource.error(exception.message ?: "Error Occurred!", data = null))
        }
    }

    fun getTitleFromUrl(url: String): String {
        return url.replace("https://www.pexels.com/video/", "")
    }

    fun getIsValidData(): Boolean {
        return !TextUtils.isEmpty(title.value) &&
                !TextUtils.isEmpty(imageUrl.value) &&
                !TextUtils.isEmpty(videoUrl.value)
    }
}