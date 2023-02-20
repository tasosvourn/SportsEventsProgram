package com.example.sportseventsprogram.features.upcomingevents

import com.example.sportseventsprogram.common.adapterdelegates.RowUiItem
import com.example.sportseventsprogram.features.upcomingevents.adapterdelegates.EventEventHandler
import com.example.sportseventsprogram.features.upcomingevents.adapterdelegates.eventAdapterDelegate
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter

class EventsAdapter(eventHandler: EventEventHandler) : ListDelegationAdapter<List<RowUiItem>>(
    eventAdapterDelegate(eventHandler)
)