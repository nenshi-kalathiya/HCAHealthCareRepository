package com.example.hcahealthcaretask.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hcahealthcaretask.R
import com.example.hcahealthcaretask.databinding.ItemRepositoryBinding
import com.example.hcahealthcaretask.model.RepositoryDataItem
import com.squareup.picasso.Picasso

class GitHubRepositoriesAdapter(val onClick: (RepositoryDataItem) -> Unit) : RecyclerView.Adapter<GitHubRepositoriesAdapter.RepositoryViewHolder>() {

    private var repositories = listOf<RepositoryDataItem>()

    fun submitList(repos: List<RepositoryDataItem>) {
        repositories = repos
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryViewHolder {
        val binding = ItemRepositoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RepositoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RepositoryViewHolder, position: Int) {
        holder.bind(repositories[position])
    }


    override fun getItemCount() = repositories.size

    inner class RepositoryViewHolder(private val binding: ItemRepositoryBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(repository: RepositoryDataItem) {
            binding.repoName.text = repository.name
            binding.repoDescription.text = repository.description ?: "Description: Unknown"
            binding.repoLanguage.text = repository.language.let { "Language: $it" } ?: "Language: Unknown"
            binding.repoStars.text = repository.stargazers_count?.let { "Stargazers Count: ${it.toString()}" } ?: "Stargazers Count: 0"
            binding.repoForks.text = repository.stargazers_count?.let {"Fork Count : ${repository.forks_count.toString()}"} ?: "Forks Count: 0"

            //load avtar image url
            Picasso.get()
                .load(repository.owner.avatar_url)
                .placeholder(R.drawable.ic_avatar) // placeholder while loading
                .error(R.drawable.ic_error) // error image if loading fails
                .into(binding.imageAvatar)

            binding.root.setOnClickListener { onClick(repository) }
        }
    }
}
