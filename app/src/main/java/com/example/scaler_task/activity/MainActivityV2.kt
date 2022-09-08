package com.example.scaler_task.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.scaler_task.R
import com.example.scaler_task.databinding.ActivityMainV2Binding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivityV2 : AppCompatActivity(){

    private lateinit var binding: ActivityMainV2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main_v2)
        binding.mainScreen.setOnClickListener{
            val intent = Intent(this, KhabriTaskActivityV2::class.java)
            startActivity(intent)
        }
    }


}