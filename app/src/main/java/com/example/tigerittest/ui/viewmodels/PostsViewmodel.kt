package com.example.tigerittest.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tigerittest.core.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class PostsViewmodel @Inject constructor(
    private val mainRepository: MainRepository
) : ViewModel() {
    fun getPosts() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                mainRepository.getPostsByOffset()
            }
        }
    }

}