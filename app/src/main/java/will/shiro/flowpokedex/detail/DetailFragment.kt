package will.shiro.flowpokedex.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import will.shiro.flowpokedex.databinding.DetailFragmentBinding

@AndroidEntryPoint
class DetailFragment : Fragment() {

    private val viewModel by viewModels<DetailViewModel>()
    private var _binding: DetailFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DetailFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.setUpData()
        setUpObservers()
    }

    private fun setUpObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            launch { viewModel.uiState.collect(::handleUiState) }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            launch { viewModel.uiEvent.collect(::handleUiEvent) }
        }
    }

    private fun handleUiState(uiState: DetailUiState) {
        binding.loadingView.isVisible = uiState.showLoading
        uiState.pokemonDetail?.let { pokemonDetail ->
            binding.pokemonNameText.text = pokemonDetail.name
            Glide.with(this).load(pokemonDetail.sprites.back).into(binding.backImage)
            Glide.with(this).load(pokemonDetail.sprites.backShiny).into(binding.backShinyImage)
            Glide.with(this).load(pokemonDetail.sprites.front).into(binding.frontImage)
            Glide.with(this).load(pokemonDetail.sprites.frontShiny).into(binding.frontShinyImage)
        }
    }

    private fun handleUiEvent(uiEvent: DetailUiEvent) {

    }
}