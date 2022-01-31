package com.example.meditationtimer.adapters

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.meditationtimer.models.Meditation

class DisplayMeditationsAdapter(val meditations : List<Meditation>) : RecyclerView.Adapter<DisplayMeditationsAdapter.MeditationViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MeditationViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: MeditationViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }


class MeditationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
{

}
}