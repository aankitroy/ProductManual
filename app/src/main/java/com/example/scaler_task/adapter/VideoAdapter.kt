package com.example.scaler_task.adapter

import android.view.LayoutInflater
import android.view.MenuItem
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.bumptech.glide.Glide
import com.example.scaler_task.databinding.ItemLayoutBinding
import com.example.scaler_task.pojo.Videos
import com.example.scaler_task.viewModel.MainViewModel


interface ItemClickListner {
    fun onItemClick(position: Int, videoUrl: String)
    fun removeItem(position: Int)
    fun editItem(videos: Videos, position: Int)
}

class VideoAdapter(
    private val videoList: ArrayList<Videos>,
    private val itemClickListner: ItemClickListner,
    private val mainViewModel: MainViewModel
) : RecyclerView.Adapter<VideoAdapter.DataViewHolder>() {


    inner class DataViewHolder(itemView: ViewBinding) :
        RecyclerView.ViewHolder(itemView.root) {
        private val binding: ItemLayoutBinding = itemView as ItemLayoutBinding
        fun onBind(
            data: Videos,
            position: Int
        ) {
            binding.item = data
            binding.model = mainViewModel
            Glide.with(binding.root.context)
                .load(data.image) // image url
                .centerCrop()
                .into(binding.image);
            binding.parent.setOnClickListener {
                itemClickListner.onItemClick(position, data.video_files.get(0).link)
            }
            binding.threeDot.setOnClickListener {
                openPopUpMenu(position, data)
            }

        }


        private fun openPopUpMenu(position: Int, data: Videos) {
            val popup = PopupMenu(binding.root.context, binding.threeDot)
            popup.inflate(com.example.scaler_task.R.menu.options_menu)
            popup.setOnMenuItemClickListener(object : PopupMenu.OnMenuItemClickListener {
                override fun onMenuItemClick(item: MenuItem?): Boolean {
                    when (item?.itemId) {
                        com.example.scaler_task.R.id.edit -> {
                            itemClickListner.editItem(data, position)
                        }
                        com.example.scaler_task.R.id.delete -> {
                            itemClickListner.removeItem(position)
                        }
                    }
                    return false
                }
            });
            popup.show()
        }

    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DataViewHolder {
        val binding: ItemLayoutBinding = DataBindingUtil.inflate<ViewDataBinding>(
            LayoutInflater.from(parent.context),
            com.example.scaler_task.R.layout.item_layout, parent, false
        ) as ItemLayoutBinding
        return DataViewHolder(binding)
    }

    override fun getItemCount(): Int = videoList.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) =
        holder.onBind(videoList[position], position)

    fun addData(list: List<Videos>) {
        videoList.addAll(list)
    }

    fun updateItem(position: Int, videos: Videos) {
        videoList.set(position, videos)
        notifyItemChanged(position, videos)
    }

    fun addItem(videos: Videos) {
        videoList.add(videos)
        notifyItemInserted(videoList.size - 1)
    }

    fun remove(position: Int) {
        if (position < itemCount) {
            videoList.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, videoList.size)
        }
    }


}