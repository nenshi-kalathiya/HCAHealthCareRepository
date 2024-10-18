package com.example.hcahealthcaretask.module

import androidx.lifecycle.ViewModelProvider
import com.example.hcahealthcaretask.service.GitHubApiService
import com.example.hcahealthcaretask.utils.Constants
import com.example.hcahealthcaretask.viewmodels.GitHubRepositoriesViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

// useful while you are integrating Dagger
@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideGitHubApiService(retrofit: Retrofit): GitHubApiService {
        return retrofit.create(GitHubApiService::class.java)
    }
}

@Module
abstract class ViewModelModule {
    @Binds
    abstract fun bindViewModelFactory(factory: GitHubRepositoriesViewModelFactory): ViewModelProvider.Factory
}
