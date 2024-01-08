package mk.ukim.finki.labmoviesapp2.ui.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import mk.ukim.finki.labmoviesapp2.domain.movie.model.Movie
import mk.ukim.finki.labmoviesapp2.domain.movie.model.MovieDetailsResponse
import mk.ukim.finki.labmoviesapp2.domain.movie.repository.MovieRepository
import okhttp3.Dispatcher

class MoviesViewModel(private val movieRepository: MovieRepository) : ViewModel() {

    private val moviesLiveData = MutableLiveData<List<Movie>>()

    private val movieDetailsResponse = MutableLiveData<MovieDetailsResponse>()

    fun getMovieDetailsResponse(): LiveData<MovieDetailsResponse> {
        return movieDetailsResponse
    }


    fun getMoviesLiveData(): LiveData<List<Movie>> {
        return moviesLiveData
    }


    fun search(query: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val movies = movieRepository.queryMovies(query)
            moviesLiveData.postValue(movies)
        }
    }

    fun showDetails(imdbID: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val movieDetails = movieRepository.getMovieDetails(imdbID)
            movieDetailsResponse.postValue(movieDetails)
        }
    }

    //ROOM
    fun listAll() {
        viewModelScope.launch(Dispatchers.IO) {
            val movies = movieRepository.listMovies()
            moviesLiveData.postValue(movies)
        }
    }


}