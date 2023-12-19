package com.bharat.noteskmp.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserEntity(
    @SerialName("_id")
    val id :String,
    val email:String,
    val password:String,
    val salt:String
)
