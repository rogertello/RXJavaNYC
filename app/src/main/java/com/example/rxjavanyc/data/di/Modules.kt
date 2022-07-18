package com.example.rxjavanyc.data.api

import com.example.rxjavanyc.repository.Repository
import com.example.rxjavanyc.repository.RepositoryImpl
import com.example.rxjavanyc.ui.viewmodel.SchoolViewModelProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RepoModule {
    @Singleton
    @Provides
    fun providesRepository(): Repository {
        return RepositoryImpl(Network.getInstance()!!)
    }
}

@InstallIn(ActivityComponent::class)
@Module
object ViewModelModule{
    @Provides
    fun providesVM(repository: Repository): SchoolViewModelProvider {
        return SchoolViewModelProvider(repository)
    }
}