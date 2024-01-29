package mo.inc.eh.fakeposts.presentation.details.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import mo.inc.eh.fakeposts.R
import mo.inc.eh.fakeposts.databinding.FragmentDetailsBinding
import mo.inc.eh.fakeposts.databinding.FragmentPostsBinding
import mo.inc.eh.fakeposts.domain.entity.Post
import mo.inc.eh.fakeposts.presentation.details.viewmodel.DetailsViewModel
import mo.inc.eh.fakeposts.presentation.posts.viewmodel.PostsViewModel
import mo.inc.eh.fakeposts.utils.UiState

@AndroidEntryPoint
class DetailsFragment : Fragment() {
    private val viewModel: DetailsViewModel by viewModels()

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val postId = DetailsFragmentArgs.fromBundle(requireArguments()).postId
        viewModel.getPostInDetails(postId)
        lifecycleScope.launch {
            viewModel.detailedPost.collectLatest {
                when(it){
                    is UiState.OnFailure<*> -> {
                        hideLoading()
                        showFailure(it.exception)

                    }
                    UiState.OnLoading -> {
                        showLoading()
                    }
                    is UiState.OnSuccess -> {
                        hideLoading()
                        showData(it.data)
                    }
                }
            }
        }
    }

    private fun showData(data: Post) {
        binding.card.visibility = View.VISIBLE
        binding.title.text = data.title
        binding.body.text = data.body
        binding.user.text = "User ${data.userId}"
    }

    private fun hideLoading() {
        binding.progressBar.visibility =View.GONE
    }

    private fun showLoading() {
        binding.progressBar.visibility =View.VISIBLE
    }

    private fun showFailure(exception: String) {
        binding.failure.visibility = View.VISIBLE
        Snackbar.make(requireView(), exception , Snackbar.LENGTH_SHORT).show()
    }
}