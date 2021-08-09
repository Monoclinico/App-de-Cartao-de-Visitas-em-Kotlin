package com.dev_monoclinico.businesscard.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import androidx.activity.viewModels
import com.dev_monoclinico.businesscard.App
//import com.dev_monoclinico.businesscard.R
import com.dev_monoclinico.businesscard.databinding.ActivityMainBinding
import com.dev_monoclinico.businesscard.util.Image

class MainActivity : AppCompatActivity() {

    private val binding by lazy {ActivityMainBinding.inflate(layoutInflater)}
    private val mainViewModel: MainViewModel by viewModels {
        MainViewModelFactory((application as App).repository)
    }

    private val adapter by lazy{BusinessCardAdapter()}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.rvCards.adapter = adapter
        getAllBusinessCard()
        insertListener()
    }

    private fun insertListener() {
        binding.fab.setOnClickListener{
            val intent: Intent = Intent(this@MainActivity, AddBusinessCardActivity::class.java)
            startActivity(intent)
        }
        adapter.listenerShare = {card ->
            Image.share(this@MainActivity,card)
        }

    }

    private fun getAllBusinessCard() {
        mainViewModel.getAll().observe(this) {businessCards ->
            adapter.submitList(businessCards)
        }
    }
}

