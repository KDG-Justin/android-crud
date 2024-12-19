package android.mythology.dl

import android.mythology.data.MythologyRepository
import android.mythology.data.NetworkMythologyRepository
import android.mythology.network.MythologyApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    private const val BASE_URL = "http://10.0.2.2:3000/"

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .baseUrl(BASE_URL)
            .build()
    }

    @Provides
    @Singleton
    fun provideMythologyApiService(retrofit: Retrofit): MythologyApiService {
        return retrofit.create(MythologyApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideMythologyRepository(mythologyApiService: MythologyApiService) : MythologyRepository {
        return NetworkMythologyRepository(mythologyApiService)
    }

}