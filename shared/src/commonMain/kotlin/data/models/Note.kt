import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Note(
    @SerialName("_id")
    val id: String,
    val title: String,
    val userId:String,
    val description: String,
    val dateCreated: Long,
    val dateUpdated: Long,
)
