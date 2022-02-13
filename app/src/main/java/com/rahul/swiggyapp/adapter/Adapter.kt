package com.rahul.swiggyapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rahul.swiggyapp.R
import com.rahul.swiggyapp.databinding.ItemLayoutBinding
import com.rahul.swiggyapp.modelClass.SearchClass
import com.rahul.swiggyapp.onClickInterface.MovieClickListener

class Adapter(private val list: ArrayList<SearchClass>, private val onClick: MovieClickListener) :
    RecyclerView.Adapter<ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_layout,
                parent,
                false
            ), onClick
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val searchClass = list[position]
        holder.setData(searchClass)
    }

    override fun getItemCount(): Int {
        return list.size
    }

}


class ViewHolder(private val view: ItemLayoutBinding, private val onClick: MovieClickListener) :
    RecyclerView.ViewHolder(view.root) {

    public fun setData(searchClass: SearchClass) {
        view.apply {
            Glide.with(imgMovieImage).load(searchClass.poster).into(imgMovieImage)
            tvMovieName.text = searchClass.title
        }

        view.root.setOnClickListener {
            onClick.onClick(bindingAdapterPosition)
        }
    }
}