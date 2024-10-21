package com.example.hcahealthcaretask.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.hcahealthcaretask.model.RepositoryDataItem
import com.example.hcahealthcaretask.repository.GitHubRepository
import com.example.hcahealthcaretask.viewmodels.GitHubRepositoriesViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.reactivex.rxjava3.core.Single
import org.junit.After
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentMatchers.anyList
import org.mockito.Mock
import org.mockito.Mockito.never
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.any
import java.io.Closeable

class GitHubRepositoriesViewModelTest {

    private val repositoryListString = """
    [
    {
    "id": 460600860,
    "node_id": "R_kgDOG3Q2HA",
    "name": ".allstar",
    "full_name": "google/.allstar",
    "private": false,
    "owner": {
      "login": "google",
      "id": 1342004,
      "node_id": "MDEyOk9yZ2FuaXphdGlvbjEzNDIwMDQ=",
      "avatar_url": "https://avatars.githubusercontent.com/u/1342004?v=4",
      "gravatar_id": "",
      "url": "https://api.github.com/users/google",
      "html_url": "https://github.com/google",
      "followers_url": "https://api.github.com/users/google/followers",
      "following_url": "https://api.github.com/users/google/following{/other_user}",
      "gists_url": "https://api.github.com/users/google/gists{/gist_id}",
      "starred_url": "https://api.github.com/users/google/starred{/owner}{/repo}",
      "subscriptions_url": "https://api.github.com/users/google/subscriptions",
      "organizations_url": "https://api.github.com/users/google/orgs",
      "repos_url": "https://api.github.com/users/google/repos",
      "events_url": "https://api.github.com/users/google/events{/privacy}",
      "received_events_url": "https://api.github.com/users/google/received_events",
      "type": "Organization",
      "user_view_type": "public",
      "site_admin": false
    },
    "html_url": "https://github.com/google/.allstar",
    "description": null,
    "fork": false,
    "url": "https://api.github.com/repos/google/.allstar",
    "forks_url": "https://api.github.com/repos/google/.allstar/forks",
    "keys_url": "https://api.github.com/repos/google/.allstar/keys{/key_id}",
    "collaborators_url": "https://api.github.com/repos/google/.allstar/collaborators{/collaborator}",
    "teams_url": "https://api.github.com/repos/google/.allstar/teams",
    "hooks_url": "https://api.github.com/repos/google/.allstar/hooks",
    "issue_events_url": "https://api.github.com/repos/google/.allstar/issues/events{/number}",
    "events_url": "https://api.github.com/repos/google/.allstar/events",
    "assignees_url": "https://api.github.com/repos/google/.allstar/assignees{/user}",
    "branches_url": "https://api.github.com/repos/google/.allstar/branches{/branch}",
    "tags_url": "https://api.github.com/repos/google/.allstar/tags",
    "blobs_url": "https://api.github.com/repos/google/.allstar/git/blobs{/sha}",
    "git_tags_url": "https://api.github.com/repos/google/.allstar/git/tags{/sha}",
    "git_refs_url": "https://api.github.com/repos/google/.allstar/git/refs{/sha}",
    "trees_url": "https://api.github.com/repos/google/.allstar/git/trees{/sha}",
    "statuses_url": "https://api.github.com/repos/google/.allstar/statuses/{sha}",
    "languages_url": "https://api.github.com/repos/google/.allstar/languages",
    "stargazers_url": "https://api.github.com/repos/google/.allstar/stargazers",
    "contributors_url": "https://api.github.com/repos/google/.allstar/contributors",
    "subscribers_url": "https://api.github.com/repos/google/.allstar/subscribers",
    "subscription_url": "https://api.github.com/repos/google/.allstar/subscription",
    "commits_url": "https://api.github.com/repos/google/.allstar/commits{/sha}",
    "git_commits_url": "https://api.github.com/repos/google/.allstar/git/commits{/sha}",
    "comments_url": "https://api.github.com/repos/google/.allstar/comments{/number}",
    "issue_comment_url": "https://api.github.com/repos/google/.allstar/issues/comments{/number}",
    "contents_url": "https://api.github.com/repos/google/.allstar/contents/{+path}",
    "compare_url": "https://api.github.com/repos/google/.allstar/compare/{base}...{head}",
    "merges_url": "https://api.github.com/repos/google/.allstar/merges",
    "archive_url": "https://api.github.com/repos/google/.allstar/{archive_format}{/ref}",
    "downloads_url": "https://api.github.com/repos/google/.allstar/downloads",
    "issues_url": "https://api.github.com/repos/google/.allstar/issues{/number}",
    "pulls_url": "https://api.github.com/repos/google/.allstar/pulls{/number}",
    "milestones_url": "https://api.github.com/repos/google/.allstar/milestones{/number}",
    "notifications_url": "https://api.github.com/repos/google/.allstar/notifications{?since,all,participating}",
    "labels_url": "https://api.github.com/repos/google/.allstar/labels{/name}",
    "releases_url": "https://api.github.com/repos/google/.allstar/releases{/id}",
    "deployments_url": "https://api.github.com/repos/google/.allstar/deployments",
    "created_at": "2022-02-17T20:40:32Z",
    "updated_at": "2024-06-17T11:54:24Z",
    "pushed_at": "2023-04-13T18:39:09Z",
    "git_url": "git://github.com/google/.allstar.git",
    "ssh_url": "git@github.com:google/.allstar.git",
    "clone_url": "https://github.com/google/.allstar.git",
    "svn_url": "https://github.com/google/.allstar",
    "homepage": null,
    "size": 12,
    "stargazers_count": 6,
    "watchers_count": 6,
    "language": kotlin,
    "has_issues": true,
    "has_projects": true,
    "has_downloads": true,
    "has_wiki": true,
    "has_pages": false,
    "has_discussions": false,
    "forks_count": 2,
    "mirror_url": null,
    "archived": true,
    "disabled": false,
    "open_issues_count": 0,
    "license": {
      "key": "apache-2.0",
      "name": "Apache License 2.0",
      "spdx_id": "Apache-2.0",
      "url": "https://api.github.com/licenses/apache-2.0",
      "node_id": "MDc6TGljZW5zZTI="
    },
    "allow_forking": true,
    "is_template": false,
    "web_commit_signoff_required": false,
    "topics": [

    ],
    "visibility": "public",
    "forks": 2,
    "open_issues": 0,
    "watchers": 6,
    "default_branch": "main"
  },
  {
    "id": 170908616,
    "node_id": "MDEwOlJlcG9zaXRvcnkxNzA5MDg2MTY=",
    "name": ".github",
    "full_name": "google/.github",
    "private": false,
    "owner": {
      "login": "google",
      "id": 1342004,
      "node_id": "MDEyOk9yZ2FuaXphdGlvbjEzNDIwMDQ=",
      "avatar_url": "https://avatars.githubusercontent.com/u/1342004?v=4",
      "gravatar_id": "",
      "url": "https://api.github.com/users/google",
      "html_url": "https://github.com/google",
      "followers_url": "https://api.github.com/users/google/followers",
      "following_url": "https://api.github.com/users/google/following{/other_user}",
      "gists_url": "https://api.github.com/users/google/gists{/gist_id}",
      "starred_url": "https://api.github.com/users/google/starred{/owner}{/repo}",
      "subscriptions_url": "https://api.github.com/users/google/subscriptions",
      "organizations_url": "https://api.github.com/users/google/orgs",
      "repos_url": "https://api.github.com/users/google/repos",
      "events_url": "https://api.github.com/users/google/events{/privacy}",
      "received_events_url": "https://api.github.com/users/google/received_events",
      "type": "Organization",
      "user_view_type": "public",
      "site_admin": false
    },
    "html_url": "https://github.com/google/.github",
    "description": "default configuration for @google repos",
    "fork": false,
    "url": "https://api.github.com/repos/google/.github",
    "forks_url": "https://api.github.com/repos/google/.github/forks",
    "keys_url": "https://api.github.com/repos/google/.github/keys{/key_id}",
    "collaborators_url": "https://api.github.com/repos/google/.github/collaborators{/collaborator}",
    "teams_url": "https://api.github.com/repos/google/.github/teams",
    "hooks_url": "https://api.github.com/repos/google/.github/hooks",
    "issue_events_url": "https://api.github.com/repos/google/.github/issues/events{/number}",
    "events_url": "https://api.github.com/repos/google/.github/events",
    "assignees_url": "https://api.github.com/repos/google/.github/assignees{/user}",
    "branches_url": "https://api.github.com/repos/google/.github/branches{/branch}",
    "tags_url": "https://api.github.com/repos/google/.github/tags",
    "blobs_url": "https://api.github.com/repos/google/.github/git/blobs{/sha}",
    "git_tags_url": "https://api.github.com/repos/google/.github/git/tags{/sha}",
    "git_refs_url": "https://api.github.com/repos/google/.github/git/refs{/sha}",
    "trees_url": "https://api.github.com/repos/google/.github/git/trees{/sha}",
    "statuses_url": "https://api.github.com/repos/google/.github/statuses/{sha}",
    "languages_url": "https://api.github.com/repos/google/.github/languages",
    "stargazers_url": "https://api.github.com/repos/google/.github/stargazers",
    "contributors_url": "https://api.github.com/repos/google/.github/contributors",
    "subscribers_url": "https://api.github.com/repos/google/.github/subscribers",
    "subscription_url": "https://api.github.com/repos/google/.github/subscription",
    "commits_url": "https://api.github.com/repos/google/.github/commits{/sha}",
    "git_commits_url": "https://api.github.com/repos/google/.github/git/commits{/sha}",
    "comments_url": "https://api.github.com/repos/google/.github/comments{/number}",
    "issue_comment_url": "https://api.github.com/repos/google/.github/issues/comments{/number}",
    "contents_url": "https://api.github.com/repos/google/.github/contents/{+path}",
    "compare_url": "https://api.github.com/repos/google/.github/compare/{base}...{head}",
    "merges_url": "https://api.github.com/repos/google/.github/merges",
    "archive_url": "https://api.github.com/repos/google/.github/{archive_format}{/ref}",
    "downloads_url": "https://api.github.com/repos/google/.github/downloads",
    "issues_url": "https://api.github.com/repos/google/.github/issues{/number}",
    "pulls_url": "https://api.github.com/repos/google/.github/pulls{/number}",
    "milestones_url": "https://api.github.com/repos/google/.github/milestones{/number}",
    "notifications_url": "https://api.github.com/repos/google/.github/notifications{?since,all,participating}",
    "labels_url": "https://api.github.com/repos/google/.github/labels{/name}",
    "releases_url": "https://api.github.com/repos/google/.github/releases{/id}",
    "deployments_url": "https://api.github.com/repos/google/.github/deployments",
    "created_at": "2019-02-15T18:14:38Z",
    "updated_at": "2024-09-14T09:58:30Z",
    "pushed_at": "2024-06-28T04:31:46Z",
    "git_url": "git://github.com/google/.github.git",
    "ssh_url": "git@github.com:google/.github.git",
    "clone_url": "https://github.com/google/.github.git",
    "svn_url": "https://github.com/google/.github",
    "homepage": "",
    "size": 6,
    "stargazers_count": 87,
    "watchers_count": 87,
    "language": null,
    "has_issues": true,
    "has_projects": false,
    "has_downloads": true,
    "has_wiki": false,
    "has_pages": false,
    "has_discussions": false,
    "forks_count": 261,
    "mirror_url": null,
    "archived": false,
    "disabled": false,
    "open_issues_count": 17,
    "license": null,
    "allow_forking": true,
    "is_template": false,
    "web_commit_signoff_required": false,
    "topics": [

    ],
    "visibility": "public",
    "forks": 261,
    "open_issues": 17,
    "watchers": 87,
    "default_branch": "master"
  },
]
""".trimIndent()

    // Rule to allow LiveData to work synchronously
    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    lateinit var mockRepository: GitHubRepository

    private lateinit var viewModel: GitHubRepositoriesViewModel

    @Mock
    lateinit var repositoriesObserver: Observer<List<RepositoryDataItem>>

    @Mock
    lateinit var filteredRepositoriesObserver: Observer<List<RepositoryDataItem>>

    @Mock
    lateinit var errorObserver: Observer<String?>

    private lateinit var closeable: Closeable

    // Initialize mocks before test case run
    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)

        viewModel = GitHubRepositoriesViewModel(mockRepository)

        viewModel.repositories.observeForever(repositoriesObserver)
        viewModel.filteredRepositories.observeForever(filteredRepositoriesObserver)
        viewModel.error.observeForever(errorObserver)
    }

    // Test the initial state of LiveData, ensuring no values are emitted initially
    @Test
    fun test_initialLiveDataState() {
        verify(repositoriesObserver, never()).onChanged(anyList())
        verify(filteredRepositoriesObserver, never()).onChanged(anyList())
        verify(errorObserver, never()).onChanged(any())
    }

    // Test that the ViewModel fetches repositories and updates LiveData correctly
    @Test
    fun test_fetchRepositories_success() {

        val gson = Gson()
        val listType = object : TypeToken<List<RepositoryDataItem>>() {}.type

        val repositoryList: List<RepositoryDataItem> = gson.fromJson(repositoryListString, listType)


        `when`(mockRepository.getRepositories("google", 10, 1)).thenReturn(Single.just(repositoryList))

        viewModel.fetchRepositories("google", true)

        verify(repositoriesObserver).onChanged(repositoryList)
        verify(errorObserver, never()).onChanged(any())
    }

    // Test that the ViewModel handles errors when fetching repositories
    @Test
    fun test_fetchRepositories_error() {

        val errorMessage = "Network Error"
        `when`(mockRepository.getRepositories("google", 10, 1)).thenReturn(Single.error(Throwable(errorMessage)))

        viewModel.fetchRepositories("google", true)

        verify(errorObserver).onChanged("Error fetching data: $errorMessage")
        verify(repositoriesObserver, never()).onChanged(anyList())
    }

    //  Test  _filteredRepositories assign value from response
    @Test
    fun test_filterRepositories_byLanguage() {

        val gson = Gson()
        val listType = object : TypeToken<List<RepositoryDataItem>>() {}.type

        val repositoryList: List<RepositoryDataItem> = gson.fromJson(repositoryListString, listType)

        `when`(mockRepository.getRepositories("google", 10, 1))
            .thenReturn(Single.just(repositoryList))

        val viewModel = GitHubRepositoriesViewModel(mockRepository)

        viewModel.fetchRepositories("google", true)

        assertEquals(repositoryList, viewModel._filteredRepositories.value)
    }

    // Test filtering by "All" to ensure all repositories are displayed
    @Test
    fun test_filterRepositories_showAll() {

        val gson = Gson()
        val listType = object : TypeToken<List<RepositoryDataItem>>() {}.type

        val repositoryList: List<RepositoryDataItem> = gson.fromJson(repositoryListString, listType)

        viewModel._repositories.value = repositoryList

        viewModel.filterRepositories("All")

        assertEquals(repositoryList, viewModel._repositories.value)
    }

    // Test that pagination updates the page count correctly
    @Test
    fun test_pagination_updatesCorrectly() {

            val gson = Gson()
        val listType = object : TypeToken<List<RepositoryDataItem>>() {}.type

        val firstPage: List<RepositoryDataItem> = gson.fromJson(repositoryListString, listType)

        viewModel._repositories.value = firstPage

        `when`(mockRepository.getRepositories("test", 10, 1)).thenReturn(Single.just(firstPage))
        `when`(mockRepository.getRepositories("test", 10, 2)).thenReturn(Single.just(emptyList()))

        viewModel.fetchRepositories("test", true)

        Assert.assertEquals(2, viewModel.currentPage)
        Assert.assertFalse(viewModel.isLastPage)
    }

    // Clean up mocks after the test
    @After
    fun tearDown() {
        closeable.close()
    }
}
