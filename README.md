# GitHub Repositories Explorer

An Android application that allows users to explore GitHub repositories for a given user.
The app displays repository details such as avatar, name, description, language, stars, forks, and some additional repository information.

## Features

1. List of Repositories:
- Fetches and displays a list of repositories for a specified user from the GitHub API.
- Shows key information such as:
- Repository name
- Description
- Language
- Number of stars
- Number of forks

2. Filtering:
- Allows users to filter repositories by programming language (e.g., Kotlin, Java, PHP, JavaScript, HTML).

3. Repository Details:
- Displays detailed information when a repository is selected, such as:
- Owner information (name, avatar, type of bussines)
- Description
- Last updated date
- Default Branch

4. Pagination:
- Loads more repositories as the user scrolls to the bottom of the list.

5. Error Handling:
- Handled API
- Displays error messages when necessary.

## Technologies Used

- Kotlin: Primary language for Android development.
- MVVM (Model-View-ViewModel): Implements separation of concerns and testability.
- Data Binding: To bind UI components in layouts to data sources.
- Fragment Navigation: Manages fragment transactions for repository list and details.
- Retrofit: For making network calls to GitHub API.
- RxKotlin: For reactive programming and managing async operations.
- Unit Testing: Provides test coverage for ViewModel and repository logic.

## Getting Started

### Prerequisites

- Android Studio
- Android SDK (version 24 or higher)
- Git (to clone the repository)

### Installation

1. Clone the repository:

```bash
git clone https://github.com/your-username/your-repository-name.git
