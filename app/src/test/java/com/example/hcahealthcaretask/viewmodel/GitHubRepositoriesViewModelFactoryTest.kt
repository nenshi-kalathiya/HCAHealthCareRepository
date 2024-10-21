package com.example.hcahealthcaretask.viewmodel

import androidx.lifecycle.ViewModel
import com.example.hcahealthcaretask.repository.GitHubRepository
import com.example.hcahealthcaretask.viewmodels.GitHubRepositoriesViewModel
import com.example.hcahealthcaretask.viewmodels.GitHubRepositoriesViewModelFactory
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Assert.fail
import org.junit.Test
import org.mockito.Mockito.mock

class GitHubRepositoriesViewModelFactoryTest {
    // Test to verify that ViewModelFactory correctly creates a GitHubRepositoriesViewModel instance
    @Test
    fun `create returns GitHubRepositoriesViewModel when ViewModel is of correct type`() {
        val mockRepository = mock(GitHubRepository::class.java)
        val factory = GitHubRepositoriesViewModelFactory(mockRepository)

        val viewModel = factory.create(GitHubRepositoriesViewModel::class.java)

        assertNotNull(viewModel) // Check that ViewModel is created
        assertTrue(viewModel is GitHubRepositoriesViewModel) // Check if ViewModel is of the correct type
    }

    // Test to verify that ViewModelFactory throws IllegalArgumentException for unsupported ViewModel types
    @Test(expected = IllegalArgumentException::class)
    fun `create throws IllegalArgumentException when ViewModel is of incorrect type`() {
        val mockRepository = mock(GitHubRepository::class.java)
        val factory = GitHubRepositoriesViewModelFactory(mockRepository)

        factory.create(UnsupportedViewModel::class.java) // This should throw an IllegalArgumentException
    }

    // Test to verify that the exception message is correct when an invalid ViewModel type is requested
    @Test
    fun `create throws correct error message for incorrect ViewModel type`() {
        val mockRepository = mock(GitHubRepository::class.java)
        val factory = GitHubRepositoriesViewModelFactory(mockRepository)

        try {
            factory.create(UnsupportedViewModel::class.java)
            fail("Expected IllegalArgumentException to be thrown")
        } catch (e: IllegalArgumentException) {
            assertEquals("ViewModel Not Found", e.message)
        }
    }

    class UnsupportedViewModel : ViewModel()
}