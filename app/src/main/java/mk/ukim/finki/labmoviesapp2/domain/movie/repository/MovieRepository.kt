package mk.ukim.finki.labmoviesapp2.domain.movie.repository

import mk.ukim.finki.labmoviesapp2.domain.movie.LocalMovieDataSource
import mk.ukim.finki.labmoviesapp2.domain.movie.RemoteMovieDataSource
import mk.ukim.finki.labmoviesapp2.domain.movie.model.Movie
import mk.ukim.finki.labmoviesapp2.domain.movie.model.MovieDetailsResponse
import mk.ukim.finki.labmoviesapp2.domain.movie.utils.NetworkConnectivity

class MovieRepository(
    private val remoteMovieDataSource: RemoteMovieDataSource,
    private val localMovieDataSource: LocalMovieDataSource,
    private val networkConnectivity: NetworkConnectivity
) {

    suspend fun queryMovies(query: String): List<Movie> {
        if (networkConnectivity.isNetworkAvailable) {
            return remoteMovieDataSource.search(query).apply {
                localMovieDataSource.saveAll(this)
            }
        }
        return localMovieDataSource.searchMovies(query) ?: emptyList()
    }

    suspend fun getMovieDetails(imdbID: String): MovieDetailsResponse {
        if (networkConnectivity.isNetworkAvailable) {
            return remoteMovieDataSource.getMovieDetails(imdbID).apply {
                localMovieDataSource.insertDetails(this)
            }
        }
        return localMovieDataSource.searchMovieDetails(imdbID)
            ?: MovieDetailsResponse(
                title = "N/A",
                year = "N/A",
                imdbID = "N/A",
                poster = "N/A",
                plot = "N/A",
                actors = "N/A",
                director = "N/A",
                writer = "N/A",
                released = "N/A",
                runtime = "N/A",
                genre = "N/A",
                language = "N/A",
                country = "N/A",
                awards = "N/A",
                rated = "N/A",
                id = 0
            )
    }

    // When starting the app
    suspend fun listMovies(): List<Movie> {
        return localMovieDataSource.getAll()
    }
}