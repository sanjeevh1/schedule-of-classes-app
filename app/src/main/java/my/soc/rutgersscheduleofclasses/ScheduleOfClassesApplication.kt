package my.soc.rutgersscheduleofclasses

import android.app.Application
import my.soc.rutgersscheduleofclasses.data.AppContainer
import my.soc.rutgersscheduleofclasses.data.DefaultAppContainer

/**
 * An application class that triggers the creation of the AppContainer
 */
class ScheduleOfClassesApplication : Application() {
    lateinit var container: AppContainer

    /**
     * Creates the AppContainer and launches the app
     */
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}