package com.sumanta.mvvmdamoapp.repo

import androidx.lifecycle.LiveData
import com.sumanta.mvvmdamoapp.db.Quote
import com.sumanta.mvvmdamoapp.db.QuoteDao

class QuoteRepository(private val quoteDao: QuoteDao) {

    fun getQuotes(): LiveData<List<Quote>>{
        return quoteDao.getQuotes()
    }

    suspend fun insertQuote(quote: Quote){
        quoteDao.insertQuote(quote)
    }
}