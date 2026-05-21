package com.demo.wiprodemo.presentation.di

import com.demo.wiprodemo.data.repository.UserRepositoryImpl
import com.demo.wiprodemo.domain.repository.user.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModel {

    @Binds
    abstract fun provideUserRepository(
        userRepositoryImpl: UserRepositoryImpl
    ): UserRepository
}