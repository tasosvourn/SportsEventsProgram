package com.example.sportseventsprogram.features.upcomingevents.adapterdelegates

import android.view.View
import com.example.sportseventsprogram.common.adapterdelegates.RowUiItem
import com.example.sportseventsprogram.databinding.ItemSportCardViewBinding
import com.example.sportseventsprogram.features.upcomingevents.EventsAdapter
import com.example.sportseventsprogram.features.upcomingevents.SportRowUiItem
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

interface SportEventHandler {
    fun onExpandIconClicked(item: SportRowUiItem, isExpanded: Boolean)
}

fun sportAdapterDelegate(sportEventHandler: SportEventHandler, eventHandler: EventEventHandler) =
    adapterDelegateViewBinding<SportRowUiItem, RowUiItem, ItemSportCardViewBinding>(
        { layoutInflater, parent ->
            ItemSportCardViewBinding.inflate(layoutInflater,
                parent,
                false)
        }
    ) {
        bind {
            binding.apply {
                if (item.isExpanded) childRecycler.visibility = View.VISIBLE
                else childRecycler.visibility = View.GONE
                expandButton.setOnCheckedChangeListener(null)
                expandButton.isChecked = item.isExpanded
                expandButton.setOnCheckedChangeListener { _, isChecked ->
                    sportEventHandler.onExpandIconClicked(item, isChecked)
                    if (isChecked) childRecycler.visibility = View.VISIBLE
                    else childRecycler.visibility = View.GONE
                }
                titleText.text = item.sportName
                val eventsAdapter = EventsAdapter(eventHandler)
                eventsAdapter.items = item.events
                childRecycler.adapter = eventsAdapter
            }
        }
    }