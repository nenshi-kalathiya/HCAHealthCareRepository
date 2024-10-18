package com.example.hcahealthcaretask.component

import com.example.hcahealthcaretask.module.NetworkModule
import com.example.hcahealthcaretask.view.activities.MainActivity
import dagger.Component
import javax.inject.Singleton

// useful while you are integrating Dagger
@Singleton
@Component(modules = [NetworkModule::class])
interface ApplicationComponent {
    fun inject(activity: MainActivity)
}
