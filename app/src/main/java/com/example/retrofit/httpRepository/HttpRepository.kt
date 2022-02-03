package com.example.retrofit.httpRepository

import android.util.Log
import com.example.retrofit.data.AllDataModel
import com.example.retrofit.data.DataEntryModel
import com.example.retrofit.data.EntryModel
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

class HttpRepository {

    private val BASE_URL = "https://botw-compendium.herokuapp.com/api/v2/"
    private val retrofit = createRetrofit()
    private val service: ZeldaService = retrofit.create(ZeldaService::class.java)

    private fun createRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


    fun getAllEntries(): AllDataModel? {
        val response = service.getEntries().execute()

        if (response.isSuccessful) {
            return response.body()!!
        } else {
            Log.e("HTTP Error Tag", "${response.errorBody()}")
            return null
        }
    }



    fun getAnEntry(id: Int): DataEntryModel? {
        val response = service.getEntry(id).execute()

        if (response.isSuccessful) {
            return response.body()!!
        } else {
            Log.e("HTTP Error Tag", "${response.errorBody()}")
            return null
        }
    }


    // DEFINE API - Api structure
    interface ZeldaService {

        @GET("all")
        fun getEntries(): Call<AllDataModel>


        @GET("entry/{id}")
        fun getEntry(@Path("id") id: Int): Call<DataEntryModel>
    }
}