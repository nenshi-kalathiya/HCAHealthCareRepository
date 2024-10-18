package com.example.hcahealthcaretask.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.hcahealthcaretask.repository.GitHubRepository
import javax.inject.Inject

// UseFull while use Dagger
/*class GitHubRepositoriesViewModelFactory @Inject constructor(
    private val repository: GitHubRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GitHubRepositoriesViewModel::class.java)) {
            return GitHubRepositoriesViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}*/

class GitHubRepositoriesViewModelFactory constructor(private val repository: GitHubRepository): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(GitHubRepositoriesViewModel::class.java)) {
            GitHubRepositoriesViewModel(this.repository) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}