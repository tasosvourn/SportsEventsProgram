package com.example.sportseventsprogram.features.upcomingevents

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.example.sportseventsprogram.common.BaseActivity
import com.example.sportseventsprogram.databinding.ActivityUpcomingSportsEventsBinding
import com.example.sportseventsprogram.utilities.collect
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UpcomingSportsEventsActivity : BaseActivity() {

    private lateinit var viewBinding: ActivityUpcomingSportsEventsBinding
    private val viewModel: UpcomingEventsViewModel by viewModels()

    private val sportsAdapter by lazy {
        SportsAdapter(viewModel.sportEventHandler,
            viewModel.eventHandler)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityUpcomingSportsEventsBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        viewBinding.recycler.adapter = sportsAdapter

        viewModel.upcomingEvents.collect(this) {
            Log.d("events", "list fetched")
            sportsAdapter.items = it
            sportsAdapter.notifyDataSetChanged()
        }

        viewModel.dataChangedIndex.collect(this) {
            if ( it != -1) updateUiList(it)
        }
    }

    /** This method is for updating the UI items list when a change occurs in an event*/
    private fun updateUiList(position: Int) {
        sportsAdapter.items = viewModel.uiList
        sportsAdapter.notifyItemChanged(position)
        viewModel.notifyDataChangeConsumed()
    }
}