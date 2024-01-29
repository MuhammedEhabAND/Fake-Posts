package mo.inc.eh.fakeposts.presentation.posts.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import mo.inc.eh.fakeposts.data.model.PostResponse
import mo.inc.eh.fakeposts.databinding.FragmentPostsBinding
import mo.inc.eh.fakeposts.domain.entity.Post
import mo.inc.eh.fakeposts.presentation.posts.adapters.OnItemClickListener
import mo.inc.eh.fakeposts.presentation.posts.adapters.PostsAdapter
import mo.inc.eh.fakeposts.presentation.posts.viewmodel.PostsViewModel
import mo.inc.eh.fakeposts.utils.UiState

@AndroidEntryPoint
class PostsFragment : Fragment(), OnItemClickListener {

    private val viewModel: PostsViewModel by viewModels()
    private lateinit var postsAdapter: PostsAdapter
    private var _binding: FragmentPostsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentPostsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        postsAdapter = PostsAdapter(this)
        // Observe the posts state flow
        lifecycleScope.launch {
            viewModel.posts.collect { uiState ->
                when (uiState) {
                    is UiState.OnFailure<*> -> {
                        hideProgress()
                        showError(uiState.exception)
                    }
                    UiState.OnLoading ->{
                        showLoading()
                    }
                    is UiState.OnSuccess ->{
                        hideProgress()
                        showPosts(uiState.data)
                    }
                }
            }
        }
    }

    private fun showLoading() {
        showProgress()

    }

    private fun hideProgress() {

        binding.progressBar.visibility = View.GONE
    }

    private fun showProgress() {

        binding.postsRv.visibility = View.GONE
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun showPosts(posts: List<Post>) {
        binding.postsRv.visibility = View.VISIBLE
        postsAdapter.submitList(posts)
        binding.postsRv.adapter = postsAdapter
    }



    private fun showError(errorMessage: String) {
        binding.postsRv.visibility = View.GONE
        binding.failure.visibility = View.VISIBLE
        Snackbar.make(requireView(), "$errorMessage", Snackbar.LENGTH_SHORT).show()
    }




    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onPostPressed(postId: Int) {
        val action = PostsFragmentDirections.actionPostsFragmentToDetailsFragment(postId)
        findNavController().navigate(action)

    }
}