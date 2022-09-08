package com.example.scaler_task.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.scaler_task.R
import com.example.scaler_task.constants.UIUtils
import com.example.scaler_task.databinding.ActivityAudioManualBinding
import com.example.scaler_task.fragment.AudioManualStartFragment
import com.example.scaler_task.viewModel.AudioManualViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AudioManualActivity : AppCompatActivity() {

    private val viewModel: AudioManualViewModel by viewModels()
    private lateinit var binding: ActivityAudioManualBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_audio_manual)

        viewModel.fragmentChange.observe(this) {
            UIUtils.replaceFragment(
                this, supportFragmentManager, it.T,
                it.args, it.tag, R.id.container, it.addToBackStack,
                it.startTransition, it.endTransition, it.popEnterTransition, it.popExitTransition
            )
        }

        viewModel.fragmentOverLay.observe(this) {
            if (it.shouldRemove) {
                UIUtils.removeFragmentsByTag(this, it.tag)
            } else {
                UIUtils.replaceFragment(
                    this, supportFragmentManager, it.T,
                    it.args, it.tag, R.id.overlay, it.addToBackStack
                )
            }
        }
        viewModel.fragmentChange(AudioManualStartFragment::class.java, AudioManualStartFragment.TAG)
    }

}