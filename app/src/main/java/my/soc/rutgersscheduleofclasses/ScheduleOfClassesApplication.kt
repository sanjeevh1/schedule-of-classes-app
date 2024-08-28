package my.soc.rutgersscheduleofclasses

import android.app.Application
import my.soc.rutgersscheduleofclasses.data.AppContainer
import my.soc.rutgersscheduleofclasses.data.DefaultAppContainer

//An application class to store the AppContainer
class ScheduleOfClassesApplication : Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}