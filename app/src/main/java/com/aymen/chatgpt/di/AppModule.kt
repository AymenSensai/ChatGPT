package com.aymen.chatgpt.di

import android.app.Application
import androidx.room.Room
import com.aymen.chatgpt.core.Constants
import com.aymen.chatgpt.feature_chat.data.local.AnswerDao
import com.aymen.chatgpt.feature_chat.data.local.entity.AppDatabase
import com.aymen.chatgpt.feature_chat.data.remote.ChatGptApi
import com.aymen.chatgpt.feature_chat.data.repository.ChatRepositoryImpl
import com.aymen.chatgpt.feature_chat.domain.repository.ChatRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideAnswerDao(app: Application): AnswerDao {
        return Room.databaseBuilder(
            app,
            AppDatabase::class.java,
            AppDatabase.DATABASE_NAME
        ).build().answerDao()
    }

    @Provides
    @Singleton
    fun provideChatGptApi(): ChatGptApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create()
    }

    @Provides
    @Singleton
    fun provideChatRepository(
        api: ChatGptApi,
        dao: AnswerDao
    ): ChatRepository {
        return ChatRepositoryImpl(api, dao)
    }

}