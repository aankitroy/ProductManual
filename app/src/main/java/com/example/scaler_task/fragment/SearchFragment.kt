package com.example.scaler_task.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.scaler_task.R
import com.example.scaler_task.databinding.FragmentSearchBinding
import com.example.scaler_task.viewModel.AudioManualViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment: Fragment() {

    private val viewModel: AudioManualViewModel by activityViewModels()
    private lateinit var binding: FragmentSearchBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_search,
            container,
            false
        )
        initView()
        return binding.root
    }

    private fun initView() {
        binding.backButton.setOnClickListener {
            viewModel.hideSearchScreen()
        }
    }

    companion object {
        const val TAG = "SearchFragment"
        fun newInstance(): SearchFragment {
            val fragment = SearchFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }
}