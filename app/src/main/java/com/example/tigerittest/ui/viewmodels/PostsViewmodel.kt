package com.example.tigerittest.ui.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.tigerittest.domain.core.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class PostsViewmodel @Inject constructor(
    private val mainRepository: MainRepository, val savedStateHandle: SavedStateHandle
) : ViewModel() {

    //  val postsList = MutableStateFlow<List<Post>>(emptyList())
    val posts = mainRepository.getPostsFromMediator().cachedIn(viewModelScope)


    fun getPostsFromApi() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val posts = mainRepository.getPostsByOffset().posts
                mainRepository.insertPostsToDb(posts)
                //  postsList.emit(posts)
            }
        }
    }

    fun getUserByPost(postId: Int, fetched: (Boolean) -> Unit) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val userDetail = mainRepository.getUserByIdDb(postId)
                if (userDetail == null) {
                    val user = mainRepository.getUserByIdNetwork(postId)
                    mainRepository.insertSingleUser(user)
                    withContext(Dispatchers.Main) {
                        fetched(true)
                    }
                } else {
                    withContext(Dispatchers.Main) {
                        fetched(true)
                    }
                }
            }
        }
    }

    fun getUserId(): Int {
        return savedStateHandle.get<Int>("userId") ?: 0

    }

    fun getPostId(): Int {
        return savedStateHandle.get<Int>("postId") ?: 0
    }


}