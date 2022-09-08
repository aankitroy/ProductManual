package com.example.scaler_task.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.scaler_task.R
import com.example.scaler_task.common.color
import com.example.scaler_task.common.drawable
import com.example.scaler_task.databinding.FragmentStepBinding
import com.example.scaler_task.viewModel.AudioManualViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StepFragment : Fragment() {

    private val viewModel: AudioManualViewModel by activityViewModels()
    private lateinit var binding: FragmentStepBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.onBackPressedDispatcher?.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if(viewModel.currentAnswerStepIndex >= 0) {
                    viewModel.showPreviousStep()
                }else{
                    isEnabled = false
                    activity?.onBackPressed()
                }
            }
        })
    }

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

        viewModel.isSpeechPlaying.observe(viewLifecycleOwner) {
            if (it) {
                binding.mic.setImageResource(R.drawable.ic_pause)
            } else {
                binding.mic.setImageResource(R.drawable.ic_play)
            }
        }

        binding.listenContainer.setOnClickListener {
            if (viewModel.isSpeechPlaying.value == true) {
                viewModel.pauseStep()
            } else {
                viewModel.playStep()
            }
        }
        binding.root.background = context?.drawable(viewModel.selectedProduct.screenBackground)
        binding.stepText.setTextColor(requireActivity().color(viewModel.selectedProduct.askMeAnythingTextColor))
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