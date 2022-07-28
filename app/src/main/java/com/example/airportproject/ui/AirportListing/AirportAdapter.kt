package com.example.airportproject.ui.AirportListing

import com.example.airportproject.data.model.ApiResponse
import com.example.airportproject.databinding.LiAirportBinding
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class AirportAdapter(val context: Context, val recyclerViewHome: RecyclerViewHomeClickListener) : RecyclerView.Adapter<ViewHolder>(){
    private lateinit var recyclerView: RecyclerView

    var items: List<ApiResponse> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LiAirportBinding.inflate(
                LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items.get(position)
        item.let {
            holder.apply {
                bind(item, isLinearLayoutManager())
                itemView.tag = item
            }
        }

        holder.itemView.setOnClickListener {
            recyclerViewHome.clickOnItem(
                item,
                holder.itemView
            )
        }
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        this.recyclerView = recyclerView
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun submitList(itemList: List<ApiResponse>){
        //items.addAll(itemList)
        items = itemList
        notifyDataSetChanged()
    }

    private fun isLinearLayoutManager() = recyclerView.layoutManager is LinearLayoutManager
}

class ViewHolder(private val binding: LiAirportBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(data: ApiResponse, isLinearLayoutManager: Boolean) {
        binding.apply {
            airport = data
            executePendingBindings()
        }
    }
}
interface RecyclerViewHomeClickListener {
    fun clickOnItem(data: ApiResponse, card: View)
}


