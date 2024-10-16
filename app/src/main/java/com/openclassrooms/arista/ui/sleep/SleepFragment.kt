package com.openclassrooms.arista.ui.sleep

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.openclassrooms.arista.databinding.FragmentSleepBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

/**
 * The Sleep Activity for the app.
 */
@AndroidEntryPoint
class SleepFragment : Fragment() {

    private var _binding: FragmentSleepBinding? = null
    private val binding get() = _binding!!

    private val viewModel: SleepViewModel by viewModels()
    private val sleepAdapter = SleepAdapter(emptyList())

    /**
     * Create the main view of the fragment
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSleepBinding.inflate(inflater, container, false)
        return binding.root
    }

    /**
     * Perform additional actions on the fragment view once it is created.
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()
        binding.sleepRecyclerview.layoutManager = LinearLayoutManager(context)
        binding.sleepRecyclerview.adapter = sleepAdapter
        viewModel.fetchSleeps()

        // Handling the database data recovery error
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.uiState.collect { uiState ->
                if (uiState.error.isNotBlank()) {
                    Toast.makeText(requireContext(), uiState.error, Toast.LENGTH_LONG).show()
                    viewModel.updateErrorState("")
                }
            }
        }
    }

    /**
     * Configures sleeps data collection from the ViewModel, and whenever new data is available,
     * sleepAdapter.updateData is called to update the adapter with this new data.
     */
    private fun setupObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.sleeps.collect { sleeps ->
                sleepAdapter.updateData(sleeps)
            }
        }
    }

    /**
     * Called when the view associated with the fragment is about to be destroyed.
     * This is a lifecycle callback method that provides an opportunity to clean up resources and references
     * associated with the view.
     * In this method, the binding object (_binding) is set to null to avoid memory leaks and potential crashes.
     */
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
