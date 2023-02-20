package com.example.sportseventsprogram.features.upcomingevents

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.domain.model.DomainResult
import com.example.domain.model.upcomingsportsevents.EventDomainModel
import com.example.domain.model.upcomingsportsevents.SportsDomainModel
import com.example.domain.usecase.UpcomingSportsEventsUseCase
import com.example.sportseventsprogram.common.BaseViewModel
import com.example.sportseventsprogram.common.adapterdelegates.RowUiItem
import com.example.sportseventsprogram.features.upcomingevents.adapterdelegates.EventEventHandler
import com.example.sportseventsprogram.features.upcomingevents.adapterdelegates.SportEventHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class UpcomingEventsViewModel @Inject constructor(
    private val upcomingSportsEventsUseCase: UpcomingSportsEventsUseCase,
) : BaseViewModel() {

    private var _upcomingEvents = MutableStateFlow<List<RowUiItem>>(listOf())
    var upcomingEvents = _upcomingEvents.asStateFlow()

    private var _dataChangedIndex = MutableStateFlow(-1)
    var dataChangedIndex = _dataChangedIndex.asStateFlow()

    val uiList = mutableListOf<RowUiItem>()

    init {
        fetchUpcomingEvents()
    }

    private fun fetchUpcomingEvents() {
        runBlocking {
            delay(5000)
        }
        viewModelScope.launch {
            when (val response = upcomingSportsEventsUseCase().first()) {
                is DomainResult.Success -> {
                    if (response.data.isNotEmpty()) makeSportsUiItemsList(response.data)
                }
                is DomainResult.Error -> {
                    Log.d("API error", response.errorDomainModel.errorMessage ?: "")
                }
            }
        }
    }

    private fun makeSportsUiItemsList(sportsList: List<SportsDomainModel>) {
        sportsList.forEach { sport ->
            uiList.add(SportRowUiItem(
                sportName = sport.sportName,
                isExpanded = false,
                events = makeEventsUiItemsList(sport.events, sport.sportName)
            ))
        }
        notifyDataSetChanged()
    }

    private fun makeEventsUiItemsList(
        eventsList: List<EventDomainModel>,
        sportName: String,
    ): List<EventRowUiItem> {
        val list = mutableListOf<EventRowUiItem>()
        eventsList.forEach { event ->
            list.add(EventRowUiItem(
                sportName = sportName,
                eventName = event.sh,
                remainingTime = event.eventStartTime,
                isFavorite = false,
                firstCompetitor = event.eventName.first,
                secondCompetitor = event.eventName.second
            ))
        }
        return list
    }

    val eventHandler = object : EventEventHandler {
        override fun onFavoriteClicked(eventClicked: EventRowUiItem, isChecked: Boolean) {
            eventClicked.isFavorite = isChecked
            sortFavorites(eventClicked)
        }
    }

    fun notifyDataSetChanged() {
        _upcomingEvents.value = uiList.toList()
    }

    fun notifyDataChangeConsumed() {
        _dataChangedIndex.value = -1
    }

    private fun sortFavorites(item: EventRowUiItem) {
        val sport = uiList.firstOrNull { (it as SportRowUiItem).sportName == item.sportName }
        sport?.let {
            val sportListIndex = uiList.indexOf(sport)
            if (sportListIndex != -1) {
                val favoriteList = mutableListOf<EventRowUiItem>()
                val nonFavoriteList = mutableListOf<EventRowUiItem>()
                (uiList[sportListIndex] as SportRowUiItem).events.forEach {
                    if (it.isFavorite) favoriteList.add(it)
                    else nonFavoriteList.add(it)
                }
                (uiList[sportListIndex] as SportRowUiItem).events = favoriteList + nonFavoriteList
                _dataChangedIndex.value = sportListIndex
            }
        }
    }

    val sportEventHandler = object : SportEventHandler {
        override fun onExpandIconClicked(item: SportRowUiItem, isExpanded: Boolean) {
            val index = uiList.indexOf(item)
            if (index != -1) (uiList[index] as SportRowUiItem).isExpanded = isExpanded
            notifyDataSetChanged()
        }
    }
}
