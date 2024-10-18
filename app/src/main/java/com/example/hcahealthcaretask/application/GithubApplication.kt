package com.example.hcahealthcaretask.application

import android.app.Application
import com.example.hcahealthcaretask.component.ApplicationComponent
import dagger.android.DaggerApplication

class GithubApplication : Application(){

    //to define some object scope are on application level
    lateinit var appComponent: ApplicationComponent
    override fun onCreate() {
        super.onCreate()
      //Suppose to use dagger dependency injection but having problem to generate dagger classes
      //  appComponent =
    }
}