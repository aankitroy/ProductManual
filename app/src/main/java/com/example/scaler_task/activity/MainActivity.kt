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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val intent = Intent(this, AudioManualActivity::class.java)
        binding.mainScreen.setOnClickListener {
            startActivity(intent)
        }

        lifecycleScope.launch(Dispatchers.IO) {
            delay(1500)
            withContext(Dispatchers.Main) {
                startActivity(intent)
                finish()
            }
        }
    }


}