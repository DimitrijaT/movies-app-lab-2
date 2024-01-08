package mk.ukim.finki.labmoviesapp2.domain.movie.retrofit

import mk.ukim.finki.labmoviesapp2.domain.movie.model.Movie
import mk.ukim.finki.labmoviesapp2.domain.movie.model.MovieDetailsResponse
import mk.ukim.finki.labmoviesapp2.domain.movie.model.MovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieDbApi {

    @GET("/")
    suspend fun search(@Query("s") query: String): Response<MovieResponse>

    @GET("/")
    suspend fun showDetails(@Query("i") query: String): Response<MovieDetailsResponse>
}