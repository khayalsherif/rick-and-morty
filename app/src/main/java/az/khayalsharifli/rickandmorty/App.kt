package az.khayalsharifli.rickandmorty

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import az.khayalsharifli.rickandmorty.di.appModule
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            properties(
                mapOf("base" to "https://rickandmortyapi.com/api/")
            )
            modules(modules = appModule)
        }
        //Close Dark Mode
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }
}