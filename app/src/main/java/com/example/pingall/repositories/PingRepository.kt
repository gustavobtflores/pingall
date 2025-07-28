package com.example.pingall.repositories

import com.example.pingall.entities.PingResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.HttpURLConnection
import java.net.URL

object PingRepository {
    suspend fun ping(url: String): PingResult {
        return withContext(Dispatchers.IO) {
            try {
                val start = System.currentTimeMillis();
                val connection = URL(url).openConnection() as HttpURLConnection;
                connection.connectTimeout = 1000;
                connection.readTimeout = 1000;

                connection.connect();

                connection.inputStream.close();

                val elapsed = System.currentTimeMillis() - start;

                PingResult(url, elapsed);
            } catch (err: Exception) {
                PingResult(url, null);
            }
        }
    }
}