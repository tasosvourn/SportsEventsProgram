package com.example.sportseventsprogram.features.upcomingevents.adapterdelegates

import com.example.sportseventsprogram.common.adapterdelegates.RowUiItem
import com.example.sportseventsprogram.databinding.ItemEventBinding
import com.example.sportseventsprogram.features.upcomingevents.EventRowUiItem
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import java.util.concurrent.TimeUnit

interface EventEventHandler {
    fun onFavoriteClicked(eventClicked: EventRowUiItem, isChecked: Boolean)
}

fun eventAdapterDelegate(eventClickHandler: EventEventHandler) =
    adapterDelegateViewBinding<EventRowUiItem, RowUiItem, ItemEventBinding>(
        { layoutInflater, parent -> ItemEventBinding.inflate(layoutInflater, parent, false) }
    ) {
        bind {
            binding.apply {
                countDownText.text = item.remainingTime.toCountdownString()
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

private fun Long.toCountdownString(): String {
    val hours = TimeUnit.MILLISECONDS.toHours(this)
    val minutes = TimeUnit.MILLISECONDS.toMinutes(this) - TimeUnit.HOURS.toMinutes(hours)
    val seconds = TimeUnit.MILLISECONDS.toSeconds(this) - TimeUnit.MINUTES.toSeconds(minutes) - TimeUnit.HOURS.toSeconds(hours)

    return String.format("%02d:%02d:%02d", hours, minutes, seconds)
}
