package data.requests

import kotlinx.serialization.Serializable

@Serializable
data class SignupRequest(
    val name:String,
    val email:String,
    val password:String
)
