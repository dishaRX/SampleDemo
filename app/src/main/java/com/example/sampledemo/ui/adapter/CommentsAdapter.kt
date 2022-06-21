package com.example.sampledemo.ui.adapter

import android.content.Context
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.example.sampledemo.R
import com.example.sampledemo.data.model.CommentResponse
import com.example.sampledemo.databinding.RowCommentBinding
import com.example.sampledemo.extensions.makeGone
import com.example.sampledemo.extensions.makeVisible

class CommentsAdapter(
    private val context: Context, private val commentList: ArrayList<CommentResponse>
) : RecyclerView.Adapter<CommentsAdapter.CommentsViewHolder>(), Filterable {
    private var filterList: ArrayList<CommentResponse> = commentList

    init {
        filterList = commentList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentsViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_comment, parent, false)
        return CommentsViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CommentsViewHolder, position: Int) {
        with(holder) {
            if (!TextUtils.isEmpty(filterList[position].name)) {
                binding.rowCommentTvName.text = filterList[position].name
                binding.rowCommentTvName.makeVisible()
            } else {
                binding.rowCommentTvName.makeGone()
            }

            if (!TextUtils.isEmpty(filterList[position].email)) {
                binding.rowCommentTvEmail.text = filterList[position].email
                binding.rowCommentTvEmail.makeVisible()
            } else {
                binding.rowCommentTvEmail.makeGone()
            }

            if (!TextUtils.isEmpty(filterList[position].body)) {
                binding.rowCommentTvComment.text = filterList[position].body
                binding.rowCommentTvComment.makeVisible()
            } else {
                binding.rowCommentTvComment.makeGone()
            }
        }
    }

    override fun getItemCount(): Int {
        return filterList.size
    }

    inner class CommentsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = RowCommentBinding.bind(view)

    }

    /**
     * Filters query result data
     */
    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                filterList = if (charSearch.isEmpty()) {
                    commentList
                } else {
                    val resultList = ArrayList<CommentResponse>()
                    for (row in commentList) {
                        if (row.name.lowercase().contains(
                                constraint.toString().lowercase()
                            ) || row.email.lowercase()
                                .contains(constraint.toString().lowercase()) || row.body.lowercase()
                                .contains(constraint.toString().lowercase())
                        ) {
                            resultList.add(row)
                        }
                    }
                    resultList
                }
                val filterResults = FilterResults()
                filterResults.values = filterList
                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                filterList = results?.values as ArrayList<CommentResponse>
                notifyDataSetChanged()
            }
        }
    }
}