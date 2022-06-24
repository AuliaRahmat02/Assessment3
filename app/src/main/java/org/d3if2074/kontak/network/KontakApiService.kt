package org.d3if2074.kontak.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.d3if2074.kontak.model.KontakPenting
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://raw.githubusercontent.com/AuliaRahmat02/api/main/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface KontakApiService {
    @GET("kontak.json")
    suspend fun getKontakPenting() : List<KontakPenting>
}

object KontakApi {
    val service: KontakApiService by lazy {
        retrofit.create(KontakApiService::class.java)
    }

    fun getKontakUrl(nama : String): String {
        return "$BASE_URL$nama.jpg"
    }
}

enum class ApiStatus {LOADING, SUCCESS, FAILED}