package mo.inc.eh.fakeposts.presentation.posts.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import mo.inc.eh.fakeposts.databinding.PostItemLayoutBinding
import mo.inc.eh.fakeposts.domain.entity.Post
import mo.inc.eh.fakeposts.presentation.posts.interfaces.OnItemClickListener


class PostsAdapter(
    private val onItemClickListener: OnItemClickListener
    ) : ListAdapter<Post, PostsAdapter.ViewHolder>(
    PostsDiffUtil()
) {

    private lateinit var postItemLayoutBinding: PostItemLayoutBinding


    class ViewHolder(var binding: PostItemLayoutBinding) : RecyclerView.ViewHolder(binding.root) {


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostsAdapter.ViewHolder {
        val inflater: LayoutInflater =
            parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        postItemLayoutBinding = PostItemLayoutBinding.inflate(inflater, parent, false)

        return ViewHolder(postItemLayoutBinding)

    }


    override fun onBindViewHolder(holder: PostsAdapter.ViewHolder, position: Int) {
        val post = getItem(position)
        holder.binding.title.text = post.title
        holder.binding.body.text = post.body
        holder.binding.user.text = "User ${post.userId}"
        holder.binding.card.setOnClickListener {
            onItemClickListener.onPostPressed(post.id)
        }

    }






    class PostsDiffUtil
        : DiffUtil.ItemCallback<Post>() {
        override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(
            oldItem: Post,
            newItem: Post,
        ): Boolean {
            return oldItem == newItem
        }

    }


}