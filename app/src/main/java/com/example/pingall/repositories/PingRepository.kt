package com.example.pingall.repositories

import android.util.Log
import com.example.pingall.entities.PingResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException

object PingRepository {
    suspend fun ping(host: String): PingResult {
        return withContext(Dispatchers.IO) {
            try {
                val command = "ping -c 1 -W 1 $host"
                Log.d("PingRepo", "Executando comando: $command")

                val startTime = System.currentTimeMillis()
                val process = Runtime.getRuntime().exec(command)

                val exitCode = process.waitFor()
                val endTime = System.currentTimeMillis()

                Log.d("PingRepo", "Código de saída: $exitCode")

                if (exitCode == 0) {
                    val elapsedTime = endTime - startTime
                    Log.d("PingRepo", "Ping para $host OK: $elapsedTime ms")
                    PingResult(host, elapsedTime)
                } else {
                    Log.d("PingRepo", "Ping para $host falhou.")
                    PingResult(host, null)
                }
            } catch (e: IOException) {
                Log.e("PingRepo", "Erro ao fazer ping para $host", e)
                PingResult(host, null)
            }
        }
    }
}