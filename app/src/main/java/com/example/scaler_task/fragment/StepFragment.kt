package com.example.scaler_task.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.scaler_task.R
import com.example.scaler_task.databinding.FragmentStepBinding
import com.example.scaler_task.viewModel.AudioManualViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StepFragment : Fragment() {

    private val viewModel: AudioManualViewModel by activityViewModels()
    private lateinit var binding: FragmentStepBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_step,
            container,
            false
        )
        initView()
        return binding.root
    }

    private fun initView() {
        binding.questionText.text = viewModel.selectedQuestion?.question
        viewModel.currentAnswerStep.observe(viewLifecycleOwner) {
            binding.stepNumber.text = "Step ${it.stepNumber}"
            binding.stepText.text = it.text
            binding.stepImage.setImageResource(it.image)
        }
        binding.listenContainer.setOnClickListener {
            if (viewModel.isSpeechPlaying) {
                binding.mic.setImageResource(R.drawable.ic_play)
                viewModel.pauseStep()
            } else {
                binding.mic.setImageResource(R.drawable.ic_pause)
                viewModel.playStep()
            }
        }
    }

    companion object {
        const val TAG = "StepFragment"
        fun newInstance(): StepFragment {
            val fragment = StepFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }
}