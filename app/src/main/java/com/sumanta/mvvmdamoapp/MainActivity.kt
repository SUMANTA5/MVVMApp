package com.sumanta.mvvmdamoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.sumanta.mvvmdamoapp.databinding.ActivityMainBinding
import com.sumanta.mvvmdamoapp.db.Quote
import com.sumanta.mvvmdamoapp.db.QuoteDatabase
import com.sumanta.mvvmdamoapp.repo.QuoteRepository
import com.sumanta.mvvmdamoapp.viewmodel.MainViewModel
import com.sumanta.mvvmdamoapp.viewmodel.MainViewModelFactory

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var mainViewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val dao = QuoteDatabase.getDatabase(applicationContext).quoteDao()
        val repository = QuoteRepository(dao)

        mainViewModel =
            ViewModelProvider(this, MainViewModelFactory(repository)).get(MainViewModel::class.java)

        mainViewModel.getQuotes().observe(this, Observer {
            binding.quotes = it.toString()
        })

        binding.btnAddQuote.setOnClickListener {
            val quote = Quote(0,"This is testing","Testing")
            mainViewModel.insertQuote(quote)
        }
    }
}