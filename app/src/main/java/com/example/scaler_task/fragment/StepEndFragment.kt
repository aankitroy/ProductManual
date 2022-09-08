package com.example.scaler_task.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.scaler_task.R
import com.example.scaler_task.databinding.FragmentStepEndBinding
import com.example.scaler_task.viewModel.AudioManualViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StepEndFragment: Fragment() {
    private val viewModel: AudioManualViewModel by activityViewModels()
    private lateinit var binding: FragmentStepEndBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_step_end,
            container,
            false
        )
        initView()
        return binding.root
    }

    private fun initView() {
        binding.listenContainer.setOnClickListener {
            viewModel.showListeningFragment()
        }
        binding.randomQuestion.setOnClickListener {
            viewModel.selectedQuestion = viewModel.randomQuestion
            viewModel.showAnswerScreen()
        }
        binding.randomQuestion.text = viewModel.randomQuestion?.question
    }

    companion object {
        const val TAG = "StepEndFragment"
        fun newInstance(): StepEndFragment {
            val fragment = StepEndFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }
}