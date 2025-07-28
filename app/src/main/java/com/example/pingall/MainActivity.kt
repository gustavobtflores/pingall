package com.example.pingall

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import com.example.pingall.adapters.PingsResultAdapter
import com.example.pingall.databinding.ActivityMainBinding
import com.example.pingall.entities.PingResult

class MainActivity : ComponentActivity() {
    private lateinit var binding: ActivityMainBinding;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater);
        setContentView(binding.root);

//        val urls = listOf(
//            "google.com",
//            "reddit.com",
//            "twitch.tv"
//        )

        val pingsList = listOf(
            PingResult("google.com", 100),
            PingResult("reddit.com", 100),
            PingResult("twitch.tv", 100)
        )

        val recyclerView = binding.pingsRecyler;
        val pingsAdapter = PingsResultAdapter(pingsList);

        recyclerView.adapter = pingsAdapter;
        recyclerView.layoutManager = LinearLayoutManager(this);
    }
}