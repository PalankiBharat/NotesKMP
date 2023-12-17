package com.bharat.noteskmp.data.request

import kotlinx.serialization.Serializable

@Serializable
data class AddNotesRequest(
    val title :String,
    val description: String,
    val userId:String,
)
