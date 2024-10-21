package com.example.hcahealthcaretask.fragments

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.hasMinimumChildCount
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.hcahealthcaretask.R
import com.example.hcahealthcaretask.view.activities.MainActivity
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


// private lateinit var viewModel: GitHubRepositoriesViewModel
//    private lateinit var binding: FragmentRepositoriesBinding
//    private lateinit var adapter: GitHubRepositoriesAdapter
//    private lateinit var layoutManager: LinearLayoutManager

@RunWith(AndroidJUnit4::class)
class GitHubRepositoriesFragmentTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

//    @Before
//    fun registerIdlingResource() {
//        val idlingResource = getIdlingResource() // your implementation of IdlingResource
//        IdlingRegistry.getInstance().register(idlingResource)
//    }

//    @After
//    fun unregisterIdlingResource() {
//        IdlingRegistry.getInstance().unregister(idlingResource)
//    }
    @Test
    fun testRecyclerViewIsDisplayed() {
        // Navigate to the GitHubRepositoriesFragment
        onView(withId(R.id.github_nav_graph)).perform(click())

        // Check if RecyclerView is displayed
        onView(withId(R.id.repositoriesRecyclerView)).check(matches(isDisplayed()))
    }

    @Test
    fun testSearchBarUpdatesResults() {
        // Navigate to the GitHubRepositoriesFragment
        onView(withId(R.id.fragment_github_nav)).perform(click())

        // Enter text in the search bar and check if results are updated
        onView(withId(R.id.searchBar)).perform(typeText("username"))
        onView(withId(R.id.repositoriesRecyclerView))
            .check(matches(hasMinimumChildCount(1)))  // Check if results are updated
    }
}
