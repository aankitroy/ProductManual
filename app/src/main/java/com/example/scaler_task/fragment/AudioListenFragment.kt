package com.example.scaler_task.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.example.scaler_task.R
import com.example.scaler_task.databinding.FragmentAudioListenBinding
import com.example.scaler_task.viewModel.AudioManualViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@AndroidEntryPoint
class AudioListenFragment : Fragment() {

    private val viewModel: AudioManualViewModel by activityViewModels()
    private lateinit var binding: FragmentAudioListenBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_audio_listen,
            container,
            false
        )
        initView()
        return binding.root
    }

    private fun initView() {
        viewModel.partialResult.observe(viewLifecycleOwner) {
            binding.listenInput.text = it
        }

        viewModel.result.observe(viewLifecycleOwner) {
            micOffState()
            binding.listenInput.text = it
        }
    }

    private fun micOffState() {
        binding.mic.setColorFilter(
            ContextCompat.getColor(requireActivity(),
            R.color.black))
        binding.micOnText.visibility = View.GONE
    }

    companion object {
        const val TAG = "AudioListenFragment"
        fun newInstance(): AudioListenFragment {
            val fragment = AudioListenFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }
}