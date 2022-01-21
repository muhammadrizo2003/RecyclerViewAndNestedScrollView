package com.example.project6k.activity

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.project6k.R
import com.example.project6k.adapter.MemberAdapter
import com.example.project6k.model.Member
import com.example.project6k.model.SubMember
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val TAG = MainActivity::class.java.simpleName.toString()
    private lateinit var recyclerView: RecyclerView
    private lateinit var context: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()

        refreshAdapter(prepareMemberList())

    }

    private fun initViews() {
        context = this
        recyclerView = main_recycler_view
        recyclerView.layoutManager = GridLayoutManager(context, 1)
    }

    private fun refreshAdapter(list: ArrayList<Member>) {
        val adapter = MemberAdapter(context, list)
        recyclerView.adapter = adapter
    }

    private fun prepareMemberList(): ArrayList<Member> {
        val list = ArrayList<Member>()

        list.add(Member("Header", "Header", false, null))
        // this is for header item

        for (i in 1..30)
            if (i % 5 == 0) {
                list.add(
                    Member(
                        "This is nested recycler view",
                        "This is nested recycler view",
                        false,
                        prepareSubMemberList()
                    )
                )
            } else {
                if (i % 2 == 0) {
                    list.add(Member("Offline Member", "Offline Member", false, null))
                } else {
                    list.add(Member("Member", "Member", true, null))
                }
            }

        list.add(Member("Footer", "Footer", false, null))
        // this is for footer item

        return list
    }

    private fun prepareSubMemberList(): ArrayList<SubMember> {
        val list = ArrayList<SubMember>()

        for (i in 1..10) list.add(SubMember("SubMember", "SubMember"))

        return list
    }
}

