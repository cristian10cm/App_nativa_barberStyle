package com.example.app_native_store.network

import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.gson.gson

private const val SUPABASE_KEY = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InR4d3hhanZrbG1seHdra2Nhb2lsIiwicm9sZSI6ImFub24iLCJpYXQiOjE3Nzk1NjU3MjcsImV4cCI6MjA5NTE0MTcyN30.4vk94i97JXbq2FiGHfEJu5nm6qGCFvv3spye3KamwRs"

val supabaseClient = HttpClient(Android) {
    install(ContentNegotiation) { gson() }
    defaultRequest {
        url(SUPABASE_URL)
        header("apikey", SUPABASE_KEY)
        header("Authorization", "Bearer $SUPABASE_KEY")
        contentType(ContentType.Application.Json)
    }
}

suspend fun supabaseGet(table: String): String {
    val response = supabaseClient.get("$SUPABASE_URL/rest/v1/$table?select=*")
    return response.bodyAsText()
}