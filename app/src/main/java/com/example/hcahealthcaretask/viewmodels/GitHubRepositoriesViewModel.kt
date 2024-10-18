package com.example.hcahealthcaretask.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.hcahealthcaretask.model.RepositoryDataItem
import com.example.hcahealthcaretask.repository.GitHubRepository
import io.reactivex.rxjava3.disposables.CompositeDisposable

class GitHubRepositoriesViewModel(private val repository: GitHubRepository) : ViewModel() {

    //set and update repository list according response
    private val _repositories = MutableLiveData<List<RepositoryDataItem>>()
    val repositories: LiveData<List<RepositoryDataItem>> get() = _repositories

    //set and update repository list according filter by language
    private val _filteredRepositories = MutableLiveData<List<RepositoryDataItem>>()
    val filteredRepositories: LiveData<List<RepositoryDataItem>> get() = _filteredRepositories

    //error handling for No API Call Response
    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> get() = _error

    private val compositeDisposable = CompositeDisposable()

    private var allRepositories: List<RepositoryDataItem> = emptyList()
    var currentPage = 1
    var isLastPage = false
    var isLoading = false

    fun fetchRepositories(userName: String = "google", isNewSearch : Boolean) {
        // Avoid multiple calls while loading or if it's the last page
        if (isLoading || isLastPage) return

        if (isNewSearch) {
            // Clear the existing data and reset pagination
            allRepositories = emptyList()
            currentPage = 1
            isLastPage = false
        }

        isLoading = true

        val disposable = repository.getRepositories(userName, 10, currentPage )
            .subscribe({ result ->
                isLoading = false
                if (result.isNotEmpty()) {
                    allRepositories = allRepositories + result
                    _repositories.value = allRepositories
                    _filteredRepositories.value = allRepositories // Initially, no filter is applied
                    currentPage++
                } else {
                    isLastPage = true
                }
            }, { throwable ->
                isLoading = false
                _error.value = "Error fetching data: ${throwable.message}"
            })
        compositeDisposable.add(disposable)
    }

    // Filter repositories by language
    fun filterRepositories(language: String) {
        if (language == "All") {
            _filteredRepositories.value = allRepositories
        } else {
            Log.e("Nenshi", "before filter : $allRepositories")
            _filteredRepositories.value = allRepositories.filter {
                it.language?.equals(language, ignoreCase = true) == true
            }
        }
    }

    fun clearRepositoryList() {
        _repositories.value = emptyList() // Clear existing data
    }
    fun clearFilteredRepositoryList() {
        _filteredRepositories.value = emptyList() // Clear existing data
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}
