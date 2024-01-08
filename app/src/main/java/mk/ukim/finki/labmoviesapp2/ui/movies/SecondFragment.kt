package mk.ukim.finki.labmoviesapp2.ui.movies

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import mk.ukim.finki.labmoviesapp2.R
import mk.ukim.finki.labmoviesapp2.databinding.FragmentFirstBinding
import mk.ukim.finki.labmoviesapp2.databinding.FragmentSecondBinding


class SecondFragment : Fragment(R.layout.fragment_second) {

    private var _binding: FragmentSecondBinding? = null

    private val binding get() = _binding!!

    private lateinit var moviesViewModel: MoviesViewModel


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // (dont use FragmentFirstBinding.bind(view))
        _binding = FragmentSecondBinding.bind(view)


        val viewModelFactory = MoviesViewModelFactory(requireContext())
        moviesViewModel = ViewModelProvider(this, viewModelFactory)[MoviesViewModel::class.java]


        moviesViewModel.getMovieDetailsResponse().observe(viewLifecycleOwner) { movieDetails ->
            with(binding) {
                detailsMovieTitle.text = movieDetails.title
                detailsMovieActors.text = movieDetails.actors
                detailsMovieYear.text = movieDetails.year
                detailsMovieGenre.text = movieDetails.genre
                detailsMovieDirector.text = movieDetails.director
                detailsMoviePlot.text = movieDetails.plot
                Glide.with(detailsMoviePoster).load(movieDetails.poster).centerCrop()
                    .placeholder(R.drawable.empty_movie).into(detailsMoviePoster)

            }
        }


        val imdbID = arguments?.getString("imdbID")
        moviesViewModel.showDetails(imdbID!!)

    }

}