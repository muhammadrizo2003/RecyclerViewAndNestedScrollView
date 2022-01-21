package com.example.project6k.model

data class Member(
    val name: String,
    val surname: String,
    val available: Boolean = false,
    val subMembers: ArrayList<SubMember>?
)

