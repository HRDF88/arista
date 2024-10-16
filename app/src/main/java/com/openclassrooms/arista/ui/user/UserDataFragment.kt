package com.openclassrooms.arista.ui.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.openclassrooms.arista.databinding.FragmentUserDataBinding
import com.openclassrooms.arista.domain.model.User
import dagger.hilt.android.AndroidEntryPoint
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch

/**
 * A fragment that displays user data.
 */
@AndroidEntryPoint
class UserDataFragment : Fragment() {
    private lateinit var binding: FragmentUserDataBinding
    private val viewModel: UserDataViewModel by viewModels()

    /**
     * Create the main view of the fragment
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUserDataBinding.inflate(inflater, container, false)
        return binding.root
    }

    /**
     * Perform additional actions on the fragment view once it is created.
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.userFlow.collect { user: User? ->
                user?.let {
                    binding.etName.setText(it.name)
                    binding.etEmail.setText(it.email)
                }
            }
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
}
