package com.example.scaler_task.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.scaler_task.R
import com.example.scaler_task.databinding.ItemQuestionBinding
import com.example.scaler_task.pojo.Question

class QuestionsAdapter(
    private val mList: List<Question>,
    private val onQuestionClickListener: OnQuestionClickListener
) : RecyclerView.Adapter<QuestionsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemQuestionBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context),
            R.layout.item_question, parent, false)

        return ViewHolder(binding)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val questionModel = mList[position]

        // sets the text to the textview from our itemHolder class
        holder.binding.textView.text = questionModel.question

        holder.binding.textView.setOnClickListener {
            onQuestionClickListener.onQuestionClicked(questionModel)
        }

    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return mList.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(val binding: ItemQuestionBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    interface OnQuestionClickListener {
        fun onQuestionClicked(question: Question)
    }
}