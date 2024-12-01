package com.example.tigerittest.ui.viewmodels

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
    private val mainRepository: MainRepository
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

}