package com.example.sportseventsprogram.features.upcomingevents

import com.example.sportseventsprogram.common.adapterdelegates.RowUiItem
import com.example.sportseventsprogram.features.upcomingevents.adapterdelegates.EventEventHandler
import com.example.sportseventsprogram.features.upcomingevents.adapterdelegates.SportEventHandler
import com.example.sportseventsprogram.features.upcomingevents.adapterdelegates.sportAdapterDelegate
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter

class SportsAdapter(sportEventHandler: SportEventHandler, eventHandler: EventEventHandler) : ListDelegationAdapter<List<RowUiItem>>(
    sportAdapterDelegate(sportEventHandler, eventHandler)
)
