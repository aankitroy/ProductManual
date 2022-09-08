package com.example.scaler_task.fragment

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.speech.RecognizerIntent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.scaler_task.R
import com.example.scaler_task.common.color
import com.example.scaler_task.common.drawable
import com.example.scaler_task.databinding.FragmentAudioManualStartBinding
import com.example.scaler_task.viewModel.AudioManualViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class AudioManualStartFragment : Fragment() {

    private val viewModel: AudioManualViewModel by activityViewModels()
    private lateinit var binding: FragmentAudioManualStartBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_audio_manual_start,
            container,
            false
        )
        initView()
        setListeners()
        return binding.root
    }

    private fun initView() {
        binding.lgSwitch.isChecked = viewModel.selectedLanguageTitle == "English"
        binding.title.text = viewModel.selectedLanguageTitle
        binding.lgSwitch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                viewModel.setSelectedLanguage("English")
            } else {
                viewModel.setSelectedLanguage("Hindi")
            }
            binding.title.text = viewModel.selectedLanguageTitle

        }
        binding.searchIcon.setOnClickListener {
            viewModel.showSearchScreen()
        }

        binding.productImage.setImageResource(viewModel.selectedProduct.productImage)
        binding.productName.text = viewModel.selectedProduct.name
        binding.productSerialNo.text = viewModel.selectedProduct.serialNumber
        binding.askMeAnything.text = viewModel.selectedProduct.askMeAnythingText
        binding.root.background = context?.drawable(viewModel.selectedProduct.screenBackground)
        binding.askMeAnything.setTextColor(requireActivity().color(viewModel.selectedProduct.askMeAnythingTextColor))
        binding.askMeAnything.background = context?.drawable(viewModel.selectedProduct.textBubbleBackground)
        binding.serviceMan.setImageResource(viewModel.selectedProduct.serviceManImage)
    }

    private fun setListeners() {
        binding.listenContainer.setOnClickListener {
            if (checkPermission())
                viewModel.showListeningFragment()
        }
    }

    private fun checkPermission(): Boolean {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(
                    requireActivity(),
                    Manifest.permission.RECORD_AUDIO
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                requestPermissions(
                    arrayOf(Manifest.permission.RECORD_AUDIO),
                    200
                )
                return false
            }
        }
        return true
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String?>,
        grantResults: IntArray
    ) {
        try {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults)
            if (requestCode == 200 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                binding.listenContainer.callOnClick()
            }
        } catch (ex: Exception) {
        }
    }

    companion object {
        const val TAG = "AudioManualStartFragment"
        fun newInstance(): AudioManualStartFragment {
            val fragment = AudioManualStartFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }
}