package com.example.tigerittest.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tigerittest.core.MainRepository
import com.example.tigerittest.models.Post
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class PostsViewmodel @Inject constructor(
    private val mainRepository: MainRepository
) : ViewModel() {

    val postsList = MutableStateFlow<List<Post>>(emptyList())
    fun getPosts() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val posts = mainRepository.getPostsByOffset().posts
                mainRepository.insertPostsToDb(posts)
                postsList.emit(posts)

            }
        }
    }

}