package will.shiro.flowpokedex.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import will.shiro.flowpokedex.databinding.ListFragmentBinding

@AndroidEntryPoint
class ListFragment : Fragment() {

    private val viewModel by viewModels<ListViewModel>()
    private var _binding: ListFragmentBinding? = null
    private val binding get() = _binding!!
    private val pokemonAdapter by lazy {
        PokemonAdapter {
            viewModel.triggerUiIntent(ListUiIntent.NavigateToDetail(it))
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ListFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpObservers()
        setUpUi()
        viewModel.setUpData()
    }

    private fun setUpUi() {
        binding.pokemonRecycler.adapter = pokemonAdapter
    }

    private fun setUpObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            launch {
                repeatOnLifecycle(Lifecycle.State.RESUMED) {
                    viewModel.uiEvent.collect(::handleUiEventChange)
                }
            }
            launch {
                repeatOnLifecycle(Lifecycle.State.RESUMED) {
                    viewModel.uiState.collect(::handleUiStateChange)
                }
            }
        }
    }

    private fun handleUiStateChange(state: ListUiState) {
        binding.loadingView.isVisible = state.showLoading
        viewLifecycleOwner.lifecycleScope.launch {
            state.list?.let { pokemonAdapter.submitData(it) }
        }
    }

    private fun handleUiEventChange(event: ListUiEvent) {
        when (event) {
            is ListUiEvent.ShowError -> Snackbar.make(
                binding.root,
                event.error,
                Snackbar.LENGTH_SHORT
            ).show()
            is ListUiEvent.Navigate -> findNavController().navigate(event.direction, event.bundle)
        }
    }
}