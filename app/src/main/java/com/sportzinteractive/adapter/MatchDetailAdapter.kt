package com.sportzinteractive.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.sportzinteractive.R
import com.sportzinteractive.model.MatchDetailModelV2
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class MatchDetailAdapter (
    private val context: Context,
    private var arrayList: MatchDetailModelV2,
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
        var pojo = arrayList
    /* holder.title.text = pojo.matchdetail?.teamName
        holder.dateTime.text = pojo.matchdetail?.dateTime
        holder.venue.text = pojo.matchdetail?.venue*/


        var matchDate = pojo.matchdetail?.match?.Date
        var matchtime = pojo.matchdetail?.match?.Time
        var offset = pojo.matchdetail?.match?.Offset
        var venue = pojo.matchdetail?.venue?.Name

        val offsetval: String? = offset
        val str_date: String = matchtime + " " + matchDate
        val formatter: DateFormat = SimpleDateFormat("HH:mm M/dd/yyyy", Locale.getDefault())
        val date: Date = formatter.parse(str_date)
        val utcTime: Long = date.getTime()
        val sdf = SimpleDateFormat("dd/MM/yyyy HH:mm a", Locale.getDefault())
        sdf.timeZone = TimeZone.getTimeZone(offsetval)
        val dateStr: String = sdf.format(utcTime)
        println("data"+ dateStr)

        holder.dateTime.text = dateStr
        holder.venue.text = venue

        /* val iter: Iterator<Map.Entry<String, MatchDetailModelV2.Teams>> = response.teams!!.iterator()
         while (iter.hasNext()) {
             val key = iter.next()
             println("Key " + key.value)

         }*/

        var iter = pojo.teams?.entries?.iterator()
        var list = ArrayList<String>()
        while ((iter!!.hasNext())) {
            val key = iter.next()
            list.add(key.value.NameFull!!)
        }

        holder.title.text = list.get(0) + " vs "+ list.get(1)
        holder.btn_navigate.setOnClickListener{
            listerner.onClick(arrayList,list.get(0), list.get(1),position)
        }

    }

    override fun getItemCount(): Int {
        return 1
    }


    interface CategoryClick
    {
        fun onClick(model: MatchDetailModelV2,teamA : String,teamB : String, position: Int)

    }
}