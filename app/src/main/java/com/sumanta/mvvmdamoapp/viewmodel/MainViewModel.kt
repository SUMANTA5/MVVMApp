package com.sumanta.mvvmdamoapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sumanta.mvvmdamoapp.db.Quote
import com.sumanta.mvvmdamoapp.repo.QuoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val quoteRepository: QuoteRepository) : ViewModel() {

    fun getQuotes(): LiveData<List<Quote>>{
        return quoteRepository.getQuotes()
    }

    fun insertQuote(quote: Quote){
        viewModelScope.launch(Dispatchers.IO) {
            quoteRepository.insertQuote(quote)
        }
    }
}