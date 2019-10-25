package com.hughod.app.ui.list

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.hughod.app.R
import com.hughod.app.ui.ext.setTextOrHide
import com.hughod.app.ui.ext.showError
import com.hughod.app.ui.ext.showImage
import com.hughod.app.ui.util.ViewHolder
import org.koin.androidx.scope.currentScope

class ListFragment : Fragment(R.layout.fragment_list) {

    private val viewModelFactory: ListViewModelFactory by currentScope.inject()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel = ListViewModelProvider.get(this, viewModelFactory)
        val adapter = ListAdapter { movieBundle ->
            view.findNavController().navigate(R.id.action_list_to_detail, movieBundle)
        }
        view.findViewById<RecyclerView>(R.id.recycler).adapter = adapter

        viewModel.movies.observe(this, Observer {
            activity?.showError(false)
            adapter.set(it)
        })
        viewModel.error.observe(this, Observer { activity?.showError(it) })
    }

    private class MovieViewHolder(parent: ViewGroup) : ViewHolder<MovieModel>(parent, R.layout.item_movie) {
        override fun bind(data: MovieModel, itemSelected: ((MovieModel) -> Unit)?) {
            itemView.showImage(R.id.poster_iv, data.posterPath)
            itemView.setTextOrHide(R.id.title_tv, data.title)
            itemView.setTextOrHide(R.id.overview_tv, data.body)
            itemView.setTextOrHide(R.id.release_date_tv, data.releaseDate)
            itemView.setTextOrHide(R.id.rating_tv, data.rating.toString())

            itemView.setOnClickListener { itemSelected?.invoke(data) }
        }
    }

    private class ListAdapter(
        private val movieBundleFunction: (Bundle) -> Unit
    ) : RecyclerView.Adapter<ViewHolder<MovieModel>>() {

        private var data: List<MovieModel> = emptyList()

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder<MovieModel> =
            MovieViewHolder(parent)

        override fun getItemCount(): Int = data.size

        override fun onBindViewHolder(holder: ViewHolder<MovieModel>, position: Int) =
            holder.bind(data[position]) { movieBundleFunction(it.toBundle()) }

        fun set(data: List<MovieModel>) {
            this.data = data
            this.notifyDataSetChanged()
        }
    }
}
