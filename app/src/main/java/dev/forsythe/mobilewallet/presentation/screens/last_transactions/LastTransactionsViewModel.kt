package dev.forsythe.mobilewallet.presentation.screens.last_transactions

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.forsythe.mobilewallet.domain.repository.TransactionsRepo
import javax.inject.Inject

@HiltViewModel
class LastTransactionsViewModel @Inject constructor(
    private val transactionsRepo: TransactionsRepo
) : ViewModel() {



}