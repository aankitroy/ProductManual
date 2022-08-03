package com.example.scaler_task.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.scaler_task.R
import com.example.scaler_task.activity.MainActivity
import com.example.scaler_task.databinding.MainFragmentBinding
import com.example.scaler_task.pojo.VideoFiles
import com.example.scaler_task.pojo.Videos
import com.example.scaler_task.viewModel.MainViewModel

class MainFragment : Fragment(), View.OnClickListener {
    private val mainViewModel: MainViewModel by activityViewModels()
    private lateinit var binding: MainFragmentBinding
    private var videos: Videos? = null
    private var position: Int? = null

    companion object {
        val TAG = "MainFragment"
        val POS = "POSITION"
        fun newInstance(videos: Videos?, position: Int?): MainFragment {
            val fragment = MainFragment()
            videos?.let {
                val args = Bundle()
                args.putSerializable(TAG, videos)
                if (position != null) {
                    args.putInt(POS, position)
                }
                fragment.arguments = args
            }
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.main_fragment, container, false)
        binding.model = mainViewModel
        initListneres()
        initView()
        initObserver()
        return binding.root
    }

    private fun initObserver() {
        mainViewModel.title.observe(viewLifecycleOwner, {
            setSubmitButton()
        })
        mainViewModel.imageUrl.observe(viewLifecycleOwner, {
            setSubmitButton()
        })
        mainViewModel.videoUrl.observe(viewLifecycleOwner, {
            setSubmitButton()
        })
    }

    private fun initView() {
        arguments?.let { arguments ->
            videos = arguments.getSerializable(TAG) as Videos
            position = arguments.getInt(POS)
            mainViewModel.title.value = videos?.url?.let { mainViewModel.getTitleFromUrl(it) }
            mainViewModel.imageUrl.value = videos!!.image
            mainViewModel.videoUrl.value = videos!!.video_files.get(0).link
        }

    }

    private fun initListneres() {
        binding.backButton.setOnClickListener(this)
        binding.submitButton.setOnClickListener(this)
    }

    private fun getVideoItem(): Videos {
        val title: String = mainViewModel.getTitleFromUrl(mainViewModel.title.value.toString())
        val image: String = mainViewModel.imageUrl.value.toString()
        val playedCount = videos?.playedCount ?: 0
        val videosList: ArrayList<VideoFiles> = ArrayList()
        val videoFiles = mainViewModel.videoUrl.value?.let { VideoFiles(it) }
        if (videoFiles != null) {
            videosList.add(videoFiles)
        }
        return Videos(title, image, playedCount, videosList)
    }

    private fun clearVieModeData() {
        mainViewModel.title.value = ""
        mainViewModel.videoUrl.value = ""
        mainViewModel.imageUrl.value = ""
    }

    private fun setSubmitButton() {
        if (mainViewModel.getIsValidData()) {
            binding.submitButton.alpha = 1f
        } else {
            binding.submitButton.alpha = 0.5f
        }
    }

    override fun onDestroyView() {
        clearVieModeData()
        super.onDestroyView()
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.back_button -> {
                requireActivity().onBackPressed()
            }
            R.id.submitButton -> {
                if (mainViewModel.getIsValidData()) {
                    (requireActivity() as MainActivity).addItem(getVideoItem(), position)
                    requireActivity().onBackPressed()
                } else {
                    Toast.makeText(requireActivity(), "Enter all field", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}