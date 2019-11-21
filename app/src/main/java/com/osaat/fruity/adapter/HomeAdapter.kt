package com.osaat.fruity.adapter

import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewParent
import com.osaat.fruity.R
import com.osaat.fruity.viewmodels.FruitItemViewModel
import kotlinx.android.synthetic.main.item_home_list.view.*

class HomeAdapter(private val listener: OnItemClickListener<FruitItemViewModel>) :
    androidx.recyclerview.widget.RecyclerView.Adapter<HomeAdapter.FruitItemViewHolder>() {

    private val differ: AsyncListDiffer<FruitItemViewModel> =
        AsyncListDiffer(this, object : DiffUtil.ItemCallback<FruitItemViewModel>(){
        override fun areItemsTheSame(old: FruitItemViewModel, new: FruitItemViewModel): Boolean =
            old == new

        override fun areContentsTheSame(old: FruitItemViewModel, new: FruitItemViewModel): Boolean =
            old.fruitType == new.fruitType

    })

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FruitItemViewHolder =
        FruitItemViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_home_list, parent, false),
            listener
        )

    override fun onBindViewHolder(holder: FruitItemViewHolder, position: Int) =
            holder.bind(differ.currentList[position])

    override fun getItemCount() = differ.currentList.size

    fun setData(data: List<FruitItemViewModel>) = differ.submitList(data)


    class FruitItemViewHolder(itemView: View, private val listener: OnItemClickListener<FruitItemViewModel>) :
            androidx.recyclerview.widget.RecyclerView.ViewHolder(itemView) {
        fun bind(item: FruitItemViewModel) = with(itemView) {
            home_item_title_view.text = item.fruitType
            setOnClickListener{ listener.onItemClick(it, item) }
        }
    }

}
