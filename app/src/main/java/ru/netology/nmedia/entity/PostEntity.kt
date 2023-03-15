package ru.netology.nmedia.entity


import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.netology.nmedia.dto.Post

@Entity
data class PostEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val author: String,
    val aythorAvatar: String,
    val content: String,
    val published: String,
    val favorites: Long = 0,
    val shares: Long,
    val views: Long,
    val favoritesByMe: Boolean,
    val sharesByMe: Boolean,
    val video: String? = null
) {
    fun toDto() = Post(id, author, aythorAvatar, content, published, favorites, shares, views, favoritesByMe, sharesByMe, video)

    companion object {
        fun fromDto(dto: Post) =
            PostEntity(
                dto.id,
                dto.author,
                dto.authorAvatar,
                dto.content,
                dto.published,
                dto.favorites,
                dto.shares,
                dto.views,
                dto.favoritesByMe,
                dto.sharesByMe,
                dto.video
            )

    }
}