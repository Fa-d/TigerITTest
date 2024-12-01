package com.example.tigerittest.models;

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class PostsRes(
    @SerializedName("limit") val limit: Int = 0,
    @SerializedName("posts") val posts: List<Post> = listOf(),
    @SerializedName("skip") val skip: Int = 0,
    @SerializedName("total") val total: Int = 0
)

@Parcelize
data class Post(
    @field:SerializedName("id") var id: Int = 0,
    @field:SerializedName("body") var body: String = "",
    @field:SerializedName("reactions") var reactions: Reactions = Reactions(),
    @field:SerializedName("tags") var tags: List<String> = listOf(),
    @field:SerializedName("title") var title: String = "",
    @field:SerializedName("userId") var userId: Int = 0,
    @field:SerializedName("views") var views: Int = 0,
) : Parcelable

@Parcelize
data class Reactions(
    @SerializedName("dislikes") val dislikes: Int = 0, @SerializedName("likes") val likes: Int = 0
) : Parcelable