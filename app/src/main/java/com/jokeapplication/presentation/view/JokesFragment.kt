package com.jokeapplication.presentation.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.jokeapplication.data.model.JokesModel
import com.jokeapplication.databinding.FragmentJokesBinding
import com.jokeapplication.presentation.viewmodel.JokesViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

class JokesFragment : Fragment() {
    private lateinit var binding: FragmentJokesBinding
    private lateinit var adapter: JokesAdapter
    private var jokesList = ArrayList<JokesModel>()
    private val jokesViewModel: JokesViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentJokesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialise()
        setAdapter()
        initObservers()
    }

    private fun initialise() {
        Timer().scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                jokesViewModel.getRemoteJoke()
            }
        }, 0, 6 * 1000)
    }

    private fun setAdapter() {
        adapter = JokesAdapter(jokesList)
        binding.rvJokes.layoutManager = LinearLayoutManager(requireContext())
        binding.rvJokes.adapter = adapter
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun initObservers() {
        viewLifecycleOwner.lifecycleScope.launch(Dispatchers.Main) {
            jokesViewModel.getJokesFromDB().observe(viewLifecycleOwner) {
                jokesList.clear()
                jokesList.addAll(it.map { jokesEntity ->
                    jokesEntity.toJokeModel()
                }.reversed())
                binding.rvJokes.scrollToPosition(jokesList.size - 1)
                adapter.notifyDataSetChanged()
            }
        }
    }
}