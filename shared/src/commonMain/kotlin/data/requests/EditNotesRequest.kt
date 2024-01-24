package data.requests

import kotlinx.serialization.Serializable

@Serializable
data class EditNotesRequest(
    val id:String,
    val title :String,
    val description: String,
)
