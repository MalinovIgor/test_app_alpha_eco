import android.app.Application

class Malinov: Application() {
    override fun onCreate() {
        super.onCreate()
//        startKoin {
//            androidContext(this@VacancyApplication)
//            modules(listOf(appModule, dataModule, interactorModule, repositoryModule, viewModelModule))
//        }
    }
}