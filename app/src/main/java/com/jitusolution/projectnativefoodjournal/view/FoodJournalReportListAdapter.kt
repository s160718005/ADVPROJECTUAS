package com.jitusolution.projectnativefoodjournal.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.jitusolution.projectnativefoodjournal.R
import com.jitusolution.projectnativefoodjournal.databinding.FoodlogItemLayoutBinding
import com.jitusolution.projectnativefoodjournal.databinding.ReportItemLayoutBinding
import com.jitusolution.projectnativefoodjournal.model.Day

class FoodJournalReportListAdapter(val reportList:ArrayList<Day>): RecyclerView.Adapter<FoodJournalReportListAdapter.FoodJournalReportListViewHolder>() {
    class FoodJournalReportListViewHolder(var view: ReportItemLayoutBinding) : RecyclerView.ViewHolder(view.root)
    fun updateReportList(newReportList: List<Day>) {
        reportList.clear()
        reportList.addAll(newReportList)
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodJournalReportListAdapter.FoodJournalReportListViewHolder{
        val inflater = LayoutInflater.from(parent.context)
        //val view = inflater.inflate(R.layout.todo_item_layout,parent,false)
        val view = DataBindingUtil.inflate<ReportItemLayoutBinding>(
            inflater,
            R.layout.report_item_layout,
            parent,
            false
        )
        return FoodJournalReportListAdapter.FoodJournalReportListViewHolder(view)
    }

    override fun onBindViewHolder(holder: FoodJournalReportListViewHolder, position: Int) {
        holder.view.day = reportList[position]
    }

    override fun getItemCount(): Int {
        return reportList.size
    }

}