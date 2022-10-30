package az.khayalsharifli.rickandmorty.di

import az.khayalsharifli.rickandmorty.BuildConfig
import az.khayalsharifli.rickandmorty.data.CharacterRepository
import az.khayalsharifli.rickandmorty.data.CharacterRepositoryImpl
import az.khayalsharifli.rickandmorty.data.remote.CharacterService
import az.khayalsharifli.rickandmorty.ui.content.home.HomeViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

val appModule = module {
    single {
        val client = OkHttpClient.Builder()
            .callTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .connectTimeout(10, TimeUnit.SECONDS)

        if (BuildConfig.DEBUG) {
            val logger = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
            client.addInterceptor(logger)
        }

        client.build()
    }

    single {
        Retrofit.Builder()
            .client(get())
            .baseUrl(getProperty("base"))
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    factory<CharacterService> { get<Retrofit>().create(CharacterService::class.java) }

    factory<CharacterRepository> {
        CharacterRepositoryImpl(
            service = get()
        )
    }

    viewModel {
        HomeViewModel(repository = get())
    }
}