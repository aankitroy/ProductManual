package com.example.scaler_task.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.scaler_task.R
import com.example.scaler_task.databinding.ActivityMainBinding
import com.example.scaler_task.viewModel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(){

    private val mainViewModel: MainViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val intent = Intent(this, KhabriTaskActivity::class.java)
        binding.mainScreen.setOnClickListener{
            startActivity(intent)
        }

        Thread {
            try {
                Thread.sleep(1500)
            } catch (e: InterruptedException) {
                e.printStackTrace()
            } finally {
                startActivity(intent)
                finish()
            }
        }.start()
    }


}