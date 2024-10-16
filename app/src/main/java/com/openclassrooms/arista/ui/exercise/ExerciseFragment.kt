package com.openclassrooms.arista.ui.exercise

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.openclassrooms.arista.R
import com.openclassrooms.arista.databinding.FragmentExerciseBinding
import com.openclassrooms.arista.domain.model.Exercise
import com.openclassrooms.arista.domain.model.ExerciseCategory
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.time.LocalDateTime

/**
 * A Fragment for displaying and managing exercises in the UI.
 */
interface DeleteExerciseInterface {
    suspend fun deleteExercise(exercise: Exercise?)
}

@AndroidEntryPoint
class ExerciseFragment : Fragment(), DeleteExerciseInterface {

    private var _binding: FragmentExerciseBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ExerciseViewModel by viewModels()
    private lateinit var exerciseAdapter: ExerciseAdapter

    /**
     * Creates the view for the fragment.
     * Sets up the RecyclerView, FloatingActionButton, and observes the exercises.
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentExerciseBinding.inflate(inflater, container, false)
        return binding.root
    }

    /**
     * Perform additional actions on the fragment view once it is created.
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupFab()
        observeExercises()

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.loadAllExercises()
        }
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
     * Setup Recycler View to display the exercises.
     */
    private fun setupRecyclerView() {
        exerciseAdapter = ExerciseAdapter(this)
        binding.exerciseRecyclerview.layoutManager = LinearLayoutManager(context)
        binding.exerciseRecyclerview.adapter = exerciseAdapter
    }

    /**
     * Observe List of exercises to update display recycler view
     */
    private fun observeExercises() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.exercisesFlow.collect { exercises ->
                exerciseAdapter.submitList(exercises)
            }
        }
    }

    /**
     * Sets up the FloatingActionButton and its click listener.
     *
     **/
    private fun setupFab() {
        binding.fab.setOnClickListener { showAddExerciseDialog() }
    }

    private fun showAddExerciseDialog() {
        val dialogView = layoutInflater.inflate(R.layout.dialog_add_exercise, null)
        val views = setupDialogViews(dialogView)

        lifecycleScope.launch {
            AlertDialog.Builder(requireContext())
                .setView(dialogView)
                .setTitle(R.string.add_new_exercise)
                .setPositiveButton(R.string.add) { _, _ ->
                    lifecycleScope.launch {
                        addExercise(views)
                    }
                }
                .setNegativeButton(R.string.cancel) { dialog, _ -> dialog.dismiss() }
                .show()
        }
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
     * Sets up the views inside the add exercise dialog.
     *
     * @param dialogView The view of the add exercise dialog.
     * @return A Triple object containing the EditText for duration, Spinner for category, and EditText for intensity.
     */
    private fun setupDialogViews(dialogView: View): Triple<EditText, Spinner, EditText> {
        val durationEditText = dialogView.findViewById<EditText>(R.id.durationEditText)
        val categorySpinner = dialogView.findViewById<Spinner>(R.id.categorySpinner).apply {
            adapter = ArrayAdapter(
                requireContext(),
                android.R.layout.simple_spinner_item,
                ExerciseCategory.values()
            ).apply {
                setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            }
        }
        val intensityEditText = dialogView.findViewById<EditText>(R.id.intensityEditText)
        return Triple(durationEditText, categorySpinner, intensityEditText)
    }

    /**
     * Adds a new exercise to the database.
     *
     * @param views A Triple object containing the EditText for duration, Spinner for category, and EditText for intensity.
     */
    private suspend fun addExercise(views: Triple<EditText, Spinner, EditText>) {
        val (durationEditText, categorySpinner, intensityEditText) = views

        val durationStr = durationEditText.text.toString().trim()
        val intensityStr = intensityEditText.text.toString().trim()

        val isDurationValid = validateDuration(durationStr)
        val isIntensityValid = validateIntensity(intensityStr)

        if (!isDurationValid || !isIntensityValid) return

        val duration = durationStr.toInt()
        val intensity = intensityStr.toInt()
        val category = categorySpinner.selectedItem as ExerciseCategory

        val newExercise =
            Exercise(System.currentTimeMillis(), LocalDateTime.now(), duration, category, intensity)
        viewModel.addNewExercise(newExercise)

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
     * Validates the duration input.
     *
     * @param duration The duration input as a string.
     * @return true if the duration is valid, false otherwise.
     */
    private fun validateDuration(duration: String): Boolean {
        if (duration.isBlank()) {
            Toast.makeText(requireContext(), R.string.fill_all_fields, Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

    /**
     * Validates the intensity input.
     *
     * @param intensity The intensity input as a string.
     * @return true if the intensity is valid, false otherwise.
     */
    private fun validateIntensity(intensity: String): Boolean {
        if (intensity.isBlank()) {
            Toast.makeText(requireContext(), R.string.fill_all_fields, Toast.LENGTH_SHORT).show()
            return false
        }

        return try {
            val intensityValue = intensity.toInt()
            if (intensityValue !in 1..10) {
                Toast.makeText(
                    requireContext(),
                    R.string.intensity_should_be_between_1_and_10,
                    Toast.LENGTH_SHORT
                ).show()
                false
            } else {
                true
            }
        } catch (e: NumberFormatException) {
            Toast.makeText(
                requireContext(),
                R.string.invalid_input_please_enter_valid_numbers,
                Toast.LENGTH_SHORT
            ).show()
            false
        }
    }

    /**
     * Called when the fragment's view is destroyed. Cleans up resources and avoids memory leaks.
     */
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    /**
     * Deletes an exercise from the database.
     *
     * @param exercise The exercise to be deleted.
     */
    override suspend fun deleteExercise(exercise: Exercise?) {
        exercise?.let { viewModel.deleteExercise(it) }

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
}
