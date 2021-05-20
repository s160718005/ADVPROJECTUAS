package com.jitusolution.projectnativefoodjournal.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.jitusolution.projectnativefoodjournal.R
import com.jitusolution.projectnativefoodjournal.databinding.FoodlogItemLayoutBinding
import com.jitusolution.projectnativefoodjournal.model.Day

class FoodLogListAdapter(val foodList:ArrayList<Day>):RecyclerView.Adapter<FoodLogListAdapter.FoodLogListViewHolder>() {
    class FoodLogListViewHolder(var view: FoodlogItemLayoutBinding) : RecyclerView.ViewHolder(view.root)
    fun updateFoodLogList(newFoodLogList: List<Day>) {
        foodList.clear()
        foodList.addAll(newFoodLogList)
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodLogListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        //val view = inflater.inflate(R.layout.todo_item_layout,parent,false)
        val view = DataBindingUtil.inflate<FoodlogItemLayoutBinding>(
                inflater,
                R.layout.foodlog_item_layout,
                parent,
                false
        )
        return FoodLogListViewHolder(view)
    }

    override fun onBindViewHolder(holder: FoodLogListViewHolder, position: Int) {
        holder.view.day = foodList[position]
    }

    override fun getItemCount(): Int {
        return foodList.size
    }
}