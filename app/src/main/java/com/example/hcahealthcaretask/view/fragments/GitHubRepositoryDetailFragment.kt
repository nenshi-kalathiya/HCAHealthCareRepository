package com.example.hcahealthcaretask.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.hcahealthcaretask.R
import com.example.hcahealthcaretask.databinding.FragmentRepositoryDetailBinding
import com.example.hcahealthcaretask.model.RepositoryDataItem
import com.example.hcahealthcaretask.utils.Constants
import com.example.hcahealthcaretask.viewmodels.RepositoryDetailsViewModel
import com.google.gson.Gson
import com.squareup.picasso.Picasso

class GitHubRepositoryDetailFragment : Fragment() {

    private lateinit var binding: FragmentRepositoryDetailBinding
    private val viewModel: RepositoryDetailsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRepositoryDetailBinding.inflate(inflater, container, false)

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        //fetch data from RepositoriesListFragment
        val repositoryString = arguments?.getString("repository")

        repositoryString?.let {
            //  Parse the string to a Repository object like Gson
            val repository = parseRepositoryFromString(it)
            bindRepositoryData(repository)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    private fun bindRepositoryData(repository: RepositoryDataItem) {
        setDataToView(repository)
        binding.repository = repository
    }

    private fun parseRepositoryFromString(repositoryString: String): RepositoryDataItem {
        //converting data from string to data class
        return Gson().fromJson(repositoryString, RepositoryDataItem::class.java)
    }

    //set details manually from repository item
    private fun setDataToView(repository: RepositoryDataItem){
        Picasso.get()
            .load(repository.owner.avatar_url)
            .placeholder(R.drawable.ic_avatar) // placeholder while loading
            .error(R.drawable.ic_error) // error image if loading fails
            .into(binding.imageAvatar)

        binding.textRepoName.text = repository.name
        binding.textRepoDescription.text = Constants.REPO_DES.plus(repository.description)
        binding.textRepoLanguage.text = Constants.REPO_LANG.plus(repository.language)
        binding.textOwnerLoginName.text = Constants.REPO_OWNER.plus(repository.owner.login)
        binding.textOwnersOrganization.text = Constants.REPO_OWNER_BTYPE.plus(repository.owner.type)
        binding.textCreatedDate.text = Constants.REPO_CREATE.plus(repository.created_at)
        binding.textUpdatedDate.text = Constants.REPO_UPDATE.plus(repository.updated_at)
        binding.textStargazerCount.text = Constants.REPO_STAG_COUNT.plus(repository.stargazers_count)
        binding.textForksCount.text = Constants.REPO_FORKS_COUNT.plus(repository.forks_count)
        binding.textWatchersCount.text = Constants.REPO_WATCHER_COUNT.plus(repository.watchers_count)
        binding.textMainBranch.text = Constants.REPO_MAIN_BRANCH.plus(repository.default_branch)
    }

}