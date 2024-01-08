package mk.ukim.finki.labmoviesapp2.domain.movie

import mk.ukim.finki.labmoviesapp2.domain.movie.model.Movie
import mk.ukim.finki.labmoviesapp2.domain.movie.model.MovieDetailsResponse

interface LocalMovieDataSource {

    suspend fun insert(movie: Movie)
    suspend fun saveAll(movies: List<Movie>)
    suspend fun delete(id: Int)
    suspend fun getAll(): List<Movie>
    suspend fun searchMovies(query: String): List<Movie>?


    suspend fun insertDetails(movieDetails: MovieDetailsResponse)
    suspend fun searchMovieDetails(imdbId: String): MovieDetailsResponse?
}