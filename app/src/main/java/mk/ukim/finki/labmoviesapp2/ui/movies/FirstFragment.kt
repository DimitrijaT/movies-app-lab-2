package mk.ukim.finki.labmoviesapp2.ui.movies

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import mk.ukim.finki.labmoviesapp2.R
import mk.ukim.finki.labmoviesapp2.adapters.MovieAdapter
import mk.ukim.finki.labmoviesapp2.databinding.FragmentFirstBinding

class FirstFragment : Fragment(R.layout.fragment_first) {

    private var _binding: FragmentFirstBinding? = null

    private val binding get() = _binding!!

    private lateinit var moviesViewModel: MoviesViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        _binding = FragmentFirstBinding.bind(view)


        val viewModelFactory = MoviesViewModelFactory(requireContext())
        moviesViewModel = ViewModelProvider(this, viewModelFactory)[MoviesViewModel::class.java]


        val adapter: MovieAdapter = MovieAdapter(ArrayList(), parentFragmentManager)

        binding.recyclerView.adapter = adapter

        moviesViewModel.getMoviesLiveData().observe(viewLifecycleOwner) {
            adapter.updateMovies(it)
        }

        binding.button.setOnClickListener {
            val query = binding.editText.text.toString()
            if (query.isEmpty()) {
                Snackbar.make(view, R.string.please_enter_query, Snackbar.LENGTH_LONG).show()
            } else {
                moviesViewModel.search(query)
            }
        }

        moviesViewModel.listAll()

    }


}