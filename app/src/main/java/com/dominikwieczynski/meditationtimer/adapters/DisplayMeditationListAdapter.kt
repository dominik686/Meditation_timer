package com.dominikwieczynski.meditationtimer.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dominikwieczynski.meditationtimer.common.MarginItemDecorator
import com.dominikwieczynski.meditationtimer.R
import com.dominikwieczynski.meditationtimer.models.MeditationList


class DisplayMeditationListAdapter(val context: Context, val meditations: List<MeditationList>) :
    RecyclerView.Adapter<DisplayMeditationListAdapter.MeditationListViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MeditationListViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.display_meditations_list_item, parent, false)

        return MeditationListViewHolder(view)
    }

    override fun onBindViewHolder(holder: MeditationListViewHolder, position: Int) {
        holder.bind(context, meditations[position])
    }

    override fun getItemCount(): Int {
        return meditations.size
    }
    // https://github.com/airbnb/epoxy/
    // https://github.com/lisawray/groupie

    class MeditationListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(context: Context, meditation: MeditationList) {
            val list = itemView.findViewById<RecyclerView>(R.id.list_meditations)
            val textview = itemView.findViewById<TextView>(R.id.date)

            textview.text = meditation.date
            list.adapter = DisplayMeditationsAdapter(meditation.meditationList)
            list.layoutManager = LinearLayoutManager(context)
            list.addItemDecoration(MarginItemDecorator(16))
        }

    }


}
