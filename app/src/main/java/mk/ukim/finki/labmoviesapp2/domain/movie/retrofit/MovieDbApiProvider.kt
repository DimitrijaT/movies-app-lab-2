package mk.ukim.finki.labmoviesapp2.domain.movie.retrofit

import com.google.gson.GsonBuilder
import mk.ukim.finki.labmoviesapp2.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

class MovieDbApiProvider {

    companion object {
        // Singleton
        @Volatile
        private var INSTANCE: MovieDbApi? = null

        // JvmStatic - in Java it is a static method
        @JvmStatic
        fun getMovieDbApi(): MovieDbApi {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = createMovieDbApi()
                INSTANCE = instance
                // return instance
                instance
            }
        }

        // Gives back a MovieDbApi, and creates an Interceptor
        private fun createMovieDbApi(): MovieDbApi {
            class QueryParamInterceptor : Interceptor {
                @Throws(IOException::class)
                override fun intercept(chain: Interceptor.Chain): Response {
                    var request: Request = chain.request()
                    println("Outgoing request to ${request.url}")
                    val htt = request.url.newBuilder()
//                        .addQueryParameter("apikey", BuildConfig.MOVIE_DB_API_KEY)
                        .addQueryParameter("apikey", BuildConfig.MOVIE_DB_API_KEY)
                        .build()
                    request = request.newBuilder().url(htt).build()
                    return chain.proceed(request)
                }
            }

            val httpLoggingInterceptor = HttpLoggingInterceptor()
            // Every info from the body we can see in the log
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

            // We add 2 interceptors one for the api key and one for the logging
            val okhttpClient = OkHttpClient.Builder()
                .addInterceptor(QueryParamInterceptor())
                .addInterceptor(httpLoggingInterceptor)
                .build()

            // We create a Gson object
            // It is a library that converts JSON to Kotlin objects
            val gson = GsonBuilder()
                .setLenient()
                .create()

            val gsonConverterFactory = GsonConverterFactory.create(gson)


            val BASE_URL = "https://www.omdbapi.com/"

            // We say what's the base url, what client we use, and what converter we use
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okhttpClient)
                .addConverterFactory(gsonConverterFactory)
                .build()

            return retrofit.create(MovieDbApi::class.java)
        }


    }
}