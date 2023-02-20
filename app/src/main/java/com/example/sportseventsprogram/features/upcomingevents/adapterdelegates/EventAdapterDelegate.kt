package com.example.sportseventsprogram.features.upcomingevents.adapterdelegates

import com.example.sportseventsprogram.utilities.DateUtils
import com.example.sportseventsprogram.common.adapterdelegates.RowUiItem
import com.example.sportseventsprogram.databinding.ItemEventBinding
import com.example.sportseventsprogram.features.upcomingevents.EventRowUiItem
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

interface EventEventHandler {
    fun onFavoriteClicked(eventClicked: EventRowUiItem, isChecked: Boolean)
}

fun eventAdapterDelegate(eventClickHandler: EventEventHandler) =
    adapterDelegateViewBinding<EventRowUiItem, RowUiItem, ItemEventBinding>(
        { layoutInflater, parent -> ItemEventBinding.inflate(layoutInflater, parent, false) }
    ) {
        bind {
            binding.apply {
                countDownText.text = DateUtils.getStringDate(item.startTime)
                favoriteButton.setOnCheckedChangeListener(null)
                favoriteButton.isChecked = item.isFavorite
                favoriteButton.setOnCheckedChangeListener { _, isChecked ->
                    eventClickHandler.onFavoriteClicked(item, isChecked)
                }
                firstName.text = item.firstCompetitor
                secondName.text = item.secondCompetitor
            }
        }
    }