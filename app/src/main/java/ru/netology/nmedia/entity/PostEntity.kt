package ru.netology.nmedia.entity


import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.netology.nmedia.dto.Post

@Entity
data class PostEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val author: String,
    val authorAvatar: String,
    val content: String,
    val published: String,
    val likes: Long = 0,
    val shares: Long,
    val views: Long,
    val likedByMe: Boolean,
    val sharesByMe: Boolean,
    val video: String? = null,
    val hidden: Boolean = false
) {
    fun toDto() = Post(id, author, authorAvatar, content, published, likes, shares, views, likedByMe, sharesByMe, video, hidden)

    companion object {
        fun fromDto(dto: Post) =
            PostEntity(
                dto.id,
                dto.author,
                dto.authorAvatar,
                dto.content,
                dto.published,
                dto.likes,
                dto.shares,
                dto.views,
                dto.likedByMe,
                dto.sharesByMe,
                dto.video,
                dto.hidden
            )
    }
}

fun List<PostEntity>.toDto(): List<Post> = map(PostEntity::toDto)
fun List<Post>.toEntity(): List<PostEntity> = map(PostEntity::fromDto)