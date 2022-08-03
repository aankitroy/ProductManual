package com.example.scaler_task.activity

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.scaler_task.R
import com.example.scaler_task.adapter.ItemClickListner
import com.example.scaler_task.adapter.VideoAdapter
import com.example.scaler_task.constants.UIUtils
import com.example.scaler_task.databinding.ActivityMainBinding
import com.example.scaler_task.fragment.MainFragment
import com.example.scaler_task.pojo.Status
import com.example.scaler_task.pojo.Videos
import com.example.scaler_task.viewModel.MainViewModel
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.PlaybackException
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.source.DefaultMediaSourceFactory
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.upstream.DataSource
import com.google.android.exoplayer2.upstream.DefaultDataSource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), ItemClickListner, Player.Listener, View.OnClickListener {

    private val mainViewModel: MainViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: VideoAdapter;
    private var simpleExoPlayer: ExoPlayer? = null
    private lateinit var mediaDataSourceFactory: DataSource.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        initListener()
        initRecyclerView()
        setupObserver()

    }

    private fun initListener() {
        binding.addButton.setOnClickListener(this)
    }


    private fun initRecyclerView() {
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = VideoAdapter(arrayListOf(), this, mainViewModel)
        binding.recyclerView.adapter = adapter
    }

    private fun setupObserver() {
        if (UIUtils.isNetworkAvailable(this)) {
            mainViewModel.getVideoList().observe(this) {
                when (it.status) {
                    Status.SUCCESS -> {
                        binding.progressBar.visibility = View.GONE
                        it.data?.let { data ->
                            mainViewModel.videoList = data
                            renderList()
                        }
                        binding.recyclerView.visibility = View.VISIBLE

                    }
                    Status.LOADING -> {
                        binding.progressBar.visibility = View.VISIBLE
                        binding.recyclerView.visibility = View.GONE
                    }
                    Status.ERROR -> {
                        binding.progressBar.visibility = View.GONE
                        Toast.makeText(
                            this,
                            "Something went wrong. " +
                                    "Api taking long time to response, Please re-open app",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            }
        } else {
            Toast.makeText(this, "No Internet", Toast.LENGTH_LONG).show()
        }


    }

    private fun renderList() {
        mainViewModel.videoList.videos?.let {
            adapter.addData(it)
            adapter.notifyDataSetChanged()
            setMediaSourceList(it)
        }
    }

    private fun setMediaSourceList(list: List<Videos>) {
        simpleExoPlayer?.let { simpleExoPlayer ->
            simpleExoPlayer.addMediaSources(getMediSourcesList(list))
            if (!simpleExoPlayer.isPlaying) {
                simpleExoPlayer.playWhenReady = true
                simpleExoPlayer.prepare()
            }
        }

    }


    private fun getMediSourcesList(list: List<Videos>): List<MediaSource> {
        val mediaSources_contents: ArrayList<MediaSource> = ArrayList()
        for (item in list.indices) {
            mediaSources_contents.add(
                getMediaSource(
                    list.get(item).video_files.get(
                        0
                    ).link
                )
            )
        }
        return mediaSources_contents;
    }

    private fun initializePlayer() {
        if (simpleExoPlayer == null) {
            mediaDataSourceFactory = DefaultDataSource.Factory(this)
            val mediaSourceFactory = DefaultMediaSourceFactory(mediaDataSourceFactory)

            simpleExoPlayer = ExoPlayer.Builder(this)
                .setMediaSourceFactory(mediaSourceFactory)
                .build()
            mainViewModel.videoList.videos?.let {
                getMediSourcesList(
                    it
                )
            }?.let {
                simpleExoPlayer?.addMediaSources(it)
                if (!simpleExoPlayer?.isPlaying!!) {
                    simpleExoPlayer?.playWhenReady = true
                    simpleExoPlayer?.prepare()
                }
            }
            simpleExoPlayer?.addListener(this);

            binding.playerView.player = simpleExoPlayer
            binding.playerView.requestFocus()
        }


    }


    private fun getMediaSource(url: String): MediaSource {
        return ProgressiveMediaSource.Factory(mediaDataSourceFactory)
            .createMediaSource(
                MediaItem.fromUri(
                    url
                )
            )
    }


    private fun updateReplayCount(position: Int) {
        if (mainViewModel.videoList.videos?.size!! > 0) {
            var playCount = mainViewModel.videoList.videos?.get(position)?.playedCount
            if (playCount != null) {
                mainViewModel.videoList.videos?.get(position)?.playedCount = playCount.plus(1)
                adapter.notifyItemChanged(position, mainViewModel.videoList.videos?.get(position))
            }
        }

    }


    override fun onPlayerError(error: PlaybackException) {
        super.onPlayerError(error)
        Toast.makeText(this, "Inavlid video url " + error.message, Toast.LENGTH_LONG).show()
    }

    override fun onMediaItemTransition(mediaItem: MediaItem?, reason: Int) {
        simpleExoPlayer?.currentPeriodIndex?.let { pos ->
            updateReplayCount(pos)
        }
        super.onMediaItemTransition(mediaItem, reason)
    }

    override fun onItemClick(position: Int, videoUrl: String) {
        if (position != simpleExoPlayer?.currentPeriodIndex) {
            simpleExoPlayer?.seekTo(position, 0)
        }
    }

    override fun editItem(videos: Videos, position: Int) {
        openMainFragment(videos, position)
    }

    private fun openMainFragment(videos: Videos?, position: Int?) {
        simpleExoPlayer?.pause()
        UIUtils.addFragment(
            this,
            R.id.fragment_container,
            MainFragment.newInstance(videos, position),
            MainFragment.TAG
        )
    }


    fun addItem(videos: Videos, position: Int?) {
        if (position != null) {
            adapter.updateItem(position, videos)
            mainViewModel.videoList.videos?.add(position, videos)
            simpleExoPlayer?.addMediaSource(
                position,
                getMediaSource(videos.video_files.get(0).link)
            )
        } else {
            adapter.addItem(videos)
            mainViewModel.videoList.videos?.add(videos)
            simpleExoPlayer?.addMediaSource(getMediaSource(videos.video_files.get(0).link))

        }
    }

    override fun removeItem(position: Int) {
        mainViewModel.videoList.videos?.removeAt(position)
        adapter.remove(position)
        simpleExoPlayer?.removeMediaItem(position)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.addButton -> {
                openMainFragment(null, null)
            }
        }
    }


    override fun onStop() {
        simpleExoPlayer?.release()
        simpleExoPlayer = null
        super.onStop()
    }

    override fun onResume() {
        initializePlayer()
        super.onResume()
    }


}