package com.example.tigerittest.data.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.Ignore
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class PostsRes(
    @SerializedName("limit") val limit: Int = 0,
    @SerializedName("posts") val posts: List<Post> = listOf(),
    @SerializedName("skip") val skip: Int = 0,
    @SerializedName("total") val total: Int = 0
)

@Parcelize
@Entity(tableName = "posts", primaryKeys = ["id"])
data class Post(
    @field:SerializedName("id") var id: Int = 0,
    @field:SerializedName("body") var body: String = "",
    @Ignore @field:SerializedName("reactions") var reactions: Reactions = Reactions(),
    @Ignore @field:SerializedName("tags") var tags: List<String> = listOf(),
    @field:SerializedName("title") var title: String = "",
    @field:SerializedName("userId") var userId: Int = 0,
    @field:SerializedName("views") var views: Int = 0,
) : Parcelable

@Parcelize
data class Reactions(
    @SerializedName("dislikes") val dislikes: Int = 0, @SerializedName("likes") val likes: Int = 0
) : Parcelable