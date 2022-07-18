package com.example.rxjavanyc.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.rxjavanyc.R
import com.example.rxjavanyc.data.model.NYCSchoolResponse

class SchoolAdapter(
    dataSet: List<NYCSchoolResponse>?,
    clickEvent: ListenerInterface.ListClickEvent
) : RecyclerView.Adapter<SchoolAdapter.SchoolViewHolder>() {
    private val dataSet: List<NYCSchoolResponse>?
    private val clickEvent: ListenerInterface.ListClickEvent
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SchoolViewHolder {
        return SchoolViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(
                    R.layout.school_layout_item,
                    parent,
                    false
                )
        )
    }

    override fun onBindViewHolder(holder: SchoolViewHolder, position: Int) {
        holder.bind(
            dataSet!![position],
            clickEvent
        )
    }

    override fun getItemCount(): Int {
        return dataSet?.size ?: 0
    }

    inner class SchoolViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val schoolName: TextView
        fun bind(dataItem: NYCSchoolResponse, clickEvent: ListenerInterface.ListClickEvent) {
            schoolName.setText(
                dataItem.school_name
            )
            itemView.setOnClickListener { view: View? ->
                clickEvent.clickDetails(
                    dataItem.dbn,
                    dataItem.school_name,
                    dataItem.location,
                    dataItem.school_email,
                    dataItem.phone_number
                )
            }
        }

        init {
            schoolName = itemView.findViewById(R.id.school_item_school_name)
        }
    }

    init {
        this.dataSet = dataSet
        this.clickEvent = clickEvent
    }
}