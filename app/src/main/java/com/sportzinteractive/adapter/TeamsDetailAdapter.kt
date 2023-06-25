package com.sportzinteractive.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.sportzinteractive.R
import com.sportzinteractive.model.MatchDetailModelV2
import kotlinx.android.synthetic.main.home_header.view.*

class TeamsDetailAdapter (
    private val context: Context,
    private var arrayList: ArrayList<MatchDetailModelV2.Teams.Players>,
    var listerner: TeamsDetailAdapter.CategoryClick
    )  : RecyclerView.Adapter<TeamsDetailAdapter.ViewHolder>()
{

    class ViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        var title: AppCompatTextView = item.findViewById(R.id.customTextView)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TeamsDetailAdapter.ViewHolder {
        return TeamsDetailAdapter.ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.adapter_teams, parent, false)
        )
    }

    override fun onBindViewHolder(holder: TeamsDetailAdapter.ViewHolder, position: Int) {
        var pojo = arrayList[position]

        if (position % 2 == 0) {
            holder.itemView.setBackgroundColor(
                ContextCompat.getColor(
                    holder.itemView.getContext(),
                    R.color.bg_roweven
                )
            );
        } else {
            holder.itemView.setBackgroundColor(ContextCompat.getColor(context, R.color.bg_rowodd));
        }

        if(pojo.Iscaptain.equals("true")) {
            holder.title.setText(pojo.NameFull + " (c)")
        } else if (pojo.Iskeeper.equals("true")) {
            holder.title.setText(pojo.NameFull + " (wk)")
        } else {
            holder.title.setText(pojo.NameFull)
        }

        holder.title.setOnClickListener {
            listerner.onClick(pojo.batting?.Style!!, pojo.bowling?.Style!!)
        }

    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    interface CategoryClick
    {
        fun onClick(batting: String,bowling: String)

    }
}