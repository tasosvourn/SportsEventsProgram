package com.example.sportseventsprogram.utilities

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.Flow

/** This is an extension function on the Flow<T> class in Kotlin.
 * It is used to collect emissions from a flow and handle them in a suspend function
 * in a lifecycle-aware way.
 * @param owner - LifecyleOwner provides the lifecycle scope for the coroutine that will collect the flow.
 * @param onCollect - Suspend function that will be called for each emission from the flow.*/
inline fun <T> Flow<T>.collect(
    owner: LifecycleOwner,
    crossinline onCollect: suspend (T) -> Unit,
) = owner.lifecycleScope.launchWhenStarted {
    collect {
        onCollect(it)
    }
}