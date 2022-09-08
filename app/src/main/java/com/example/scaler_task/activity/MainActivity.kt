package com.example.scaler_task.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.example.scaler_task.R
import com.example.scaler_task.databinding.ActivityMainBinding
import com.example.scaler_task.viewModel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val intent = Intent(this, AudioManualActivity::class.java)

        val job = lifecycleScope.launch(Dispatchers.IO) {
            delay(1500)
            withContext(Dispatchers.Main) {
                if (isActive) {
                    startActivity(intent)
                }
            }
        }

        binding.mainScreen.setOnClickListener {
            job.cancel()
            startActivity(intent)
        }

    }


}