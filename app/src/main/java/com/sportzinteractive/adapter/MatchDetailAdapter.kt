package com.sportzinteractive.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sportzinteractive.R
import com.sportzinteractive.model.MatchDetailModel
import java.util.*

class MatchDetailAdapter (
    private val context: Context,
    private var arrayList: ArrayList<MatchDetailModel>,
    var listerner: CategoryClick

) : RecyclerView.Adapter<MatchDetailAdapter.ViewHolder>(){

    class ViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        var title: AppCompatTextView = item.findViewById(R.id.item_title)
        var dateTime: AppCompatTextView = item.findViewById(R.id.item_datetime)
        var venue: AppCompatTextView = item.findViewById(R.id.item_venue)
        var btn_navigate: AppCompatTextView = item.findViewById(R.id.btn_navigate)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.adapter_all_details, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var pojo = arrayList.get(position)
     holder.title.text = pojo.matchdetail?.teamName
        holder.dateTime.text = pojo.matchdetail?.dateTime
        holder.venue.text = pojo.matchdetail?.venue

        holder.btn_navigate.setOnClickListener{
            listerner.onClick(arrayList[position],position)
        }

    }

    override fun getItemCount(): Int {
        return arrayList.size
    }


    interface CategoryClick
    {
        fun onClick(model: MatchDetailModel,position: Int)

    }
}