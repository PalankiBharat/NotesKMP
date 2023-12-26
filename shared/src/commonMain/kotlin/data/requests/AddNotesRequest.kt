package data.requests

import kotlinx.serialization.Serializable

@Serializable
data class AddNotesRequest(
    val title :String,
    val description: String,
)
