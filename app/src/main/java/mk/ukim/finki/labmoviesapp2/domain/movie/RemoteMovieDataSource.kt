package mk.ukim.finki.labmoviesapp2.domain.movie

import mk.ukim.finki.labmoviesapp2.domain.movie.model.Movie
import mk.ukim.finki.labmoviesapp2.domain.movie.model.MovieDetailsResponse

interface RemoteMovieDataSource {
    suspend fun search(query: String): List<Movie>

    suspend fun getMovieDetails(imdbID: String): MovieDetailsResponse
}