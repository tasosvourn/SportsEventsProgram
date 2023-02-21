package com.example.sportseventsprogram.common

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/** Base view model to reduce boilerplate code and share the common logic between all the view models. */
@HiltViewModel
open class BaseViewModel @Inject constructor() : ViewModel() {

}
