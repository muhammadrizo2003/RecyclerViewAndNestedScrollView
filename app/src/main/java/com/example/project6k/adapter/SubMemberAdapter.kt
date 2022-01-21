package com.example.project6k.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.project6k.R
import com.example.project6k.model.SubMember
import kotlinx.android.synthetic.main.item_nested_recycler_view_layout.view.*

// this is simple recycler view adapter
data class SubMemberAdapter(private val context: Context, private val subMembers: ArrayList<SubMember>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return CustomViewHolder(
            LayoutInflater.from(context)
                .inflate(R.layout.item_nested_recycler_view_layout, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val subMember = subMembers[position]

        // set data to items
        if (holder is CustomViewHolder) {
            holder.text_view_nested_rv_layout.text = subMember.name
        }
    }

    override fun getItemCount(): Int {
        return subMembers.size
    }

    // this holder is for custom item
    private data class CustomViewHolder(
        val view: View,
        val text_view_nested_rv_layout: TextView = view.text_view_nested_rv_layout
    ) : RecyclerView.ViewHolder(view)
}

