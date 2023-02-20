package com.example.sportseventsprogram

import android.app.Application
import com.example.data.networking.EndPointConfiguration
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class UpcomingSportsEventsApp: Application() {

    @Inject
    lateinit var endPointConfiguration: EndPointConfiguration
}