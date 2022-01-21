package com.example.project6k.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.project6k.R
import com.example.project6k.model.Member
import com.example.project6k.model.SubMember
import kotlinx.android.synthetic.main.item_available_layout.view.*
import kotlinx.android.synthetic.main.item_footer_layout.view.*
import kotlinx.android.synthetic.main.item_header_layout.view.*
import kotlinx.android.synthetic.main.item_not_available_layout.view.*
import kotlinx.android.synthetic.main.item_recycler_view_layout.view.*

// this is multiple items adapter
// recycler view inside nested scroll view
// recycler view inside recycler view
data class MemberAdapter(private val context: Context, private val members: ArrayList<Member>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    // items types
    companion object {
        private const val TYPE_ITEM_HEADER: Int = 0
        private const val TYPE_ITEM_AVAILABLE: Int = 1
        private const val TYPE_ITEM_NOT_AVAILABLE: Int = 2
        private const val TYPE_ITEM_FOOTER: Int = 3
        private const val TYPE_ITEM_RECYCLER: Int = 4
    }

    // is header item
    private fun isHeader(position: Int): Boolean {
        return position == 0
    }

    // is footer item
    private fun isFooter(position: Int): Boolean {
        return position == members.size - 1
    }

    override fun getItemViewType(position: Int): Int {
        return when {
            members[position].subMembers != null -> TYPE_ITEM_RECYCLER
            isHeader(position) -> TYPE_ITEM_HEADER
            isFooter(position) -> TYPE_ITEM_FOOTER
            members[position].available -> TYPE_ITEM_AVAILABLE
            else -> TYPE_ITEM_NOT_AVAILABLE
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when (viewType) {
            TYPE_ITEM_HEADER -> {
                return CustomViewHeaderHolder(
                    LayoutInflater.from(context).inflate(R.layout.item_header_layout, parent, false)
                )
            }
            TYPE_ITEM_FOOTER -> {
                return CustomViewFooterHolder(
                    LayoutInflater.from(context).inflate(R.layout.item_footer_layout, parent, false)
                )
            }
            TYPE_ITEM_AVAILABLE -> {
                return CustomAvailableViewHolder(
                    LayoutInflater.from(context)
                        .inflate(R.layout.item_available_layout, parent, false)
                )
            }
            TYPE_ITEM_RECYCLER -> {
                return CustomViewRecyclerHolder(
                    LayoutInflater.from(context)
                        .inflate(R.layout.item_recycler_view_layout, parent, false)
                )
            }
            else -> {
                return CustomNotAvailableViewHolder(
                    LayoutInflater.from(context)
                        .inflate(R.layout.item_not_available_layout, parent, false)
                )
            }
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val member = members[position]

        // set data to items
        when (holder) {
            is CustomViewHeaderHolder -> {
                holder.text_view_header_holder.text = member.name
            }
            is CustomAvailableViewHolder -> {
                holder.text_view_available_holder.text = member.name
            }
            is CustomNotAvailableViewHolder -> {
                holder.text_view_not_available_holder.text = member.name
            }
            is CustomViewFooterHolder -> {
                holder.text_view_footer_holder.text = member.name
            }
            is CustomViewRecyclerHolder -> {
                refreshAdapter(holder.nested_rv_layout, member.subMembers!!)
            }
        }
    }

    // set list to sub recycler view's adapter
    private fun refreshAdapter(recyclerView: RecyclerView, list: ArrayList<SubMember>) {
        val subMemberAdapter = SubMemberAdapter(context, list)
        recyclerView.adapter = subMemberAdapter
    }

    override fun getItemCount(): Int {
        return members.size
    }

    // this holder is for header item
    private data class CustomViewHeaderHolder(
        val view: View,
        val text_view_header_holder: TextView = view.text_view_header_layout
    ) : RecyclerView.ViewHolder(view)

    // this holder is for available user's item
    private data class CustomAvailableViewHolder(
        val view: View,
        val text_view_available_holder: TextView = view.text_view_available_layout
    ) : RecyclerView.ViewHolder(view)

    // this holder is for not available user's item
    private data class CustomNotAvailableViewHolder(
        val view: View,
        val text_view_not_available_holder: TextView = view.text_view_not_available_layout
    ) : RecyclerView.ViewHolder(view)

    // this holder is for footer item
    private data class CustomViewFooterHolder(
        val view: View,
        val text_view_footer_holder: TextView = view.text_view_footer_layout
    ) : RecyclerView.ViewHolder(view)

    // this holder is for inner recycler view item
    private data class CustomViewRecyclerHolder(
        val view: View,
        val nested_rv_layout: RecyclerView = view.nested_rv_layout
    ) : RecyclerView.ViewHolder(view)
}

