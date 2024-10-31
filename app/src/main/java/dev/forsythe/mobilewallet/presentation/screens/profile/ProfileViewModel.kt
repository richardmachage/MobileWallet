package dev.forsythe.mobilewallet.presentation.screens.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.forsythe.mobilewallet.domain.models.CustomerModel
import dev.forsythe.mobilewallet.domain.repository.CustomerRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val customerRepo: CustomerRepo,
) : ViewModel() {



    fun getCustomerDetails() : Flow<CustomerModel?> {
        return customerRepo.getUserDetails().stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = null
        )
    }
}