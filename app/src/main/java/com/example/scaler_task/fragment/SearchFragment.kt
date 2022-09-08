package com.example.scaler_task.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.scaler_task.R
import com.example.scaler_task.adapter.QuestionsAdapter
import com.example.scaler_task.databinding.FragmentSearchBinding
import com.example.scaler_task.pojo.Question
import com.example.scaler_task.pojo.QuestionDemo
import com.example.scaler_task.viewModel.AudioManualViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment: Fragment() {

    private val viewModel: AudioManualViewModel by activityViewModels()
    private lateinit var binding: FragmentSearchBinding
    private lateinit var adapter: QuestionsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.onBackPressedDispatcher?.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                viewModel.hideSearchScreen()
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
        adapter = QuestionsAdapter(QuestionDemo.getQuestionList(), object : QuestionsAdapter.OnQuestionClickListener {
            override fun onQuestionClicked(question: Question) {
                viewModel.selectedQuestion = question
                viewModel.hideSearchScreen()
                viewModel.showAnswerScreen()
            }
        })
        binding.recyclerView.layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
        binding.recyclerView.adapter = adapter
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