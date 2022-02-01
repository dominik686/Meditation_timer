package com.example.meditationtimer.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.meditationtimer.R
import com.example.meditationtimer.models.Meditation

class DisplayMeditationsAdapter(val meditations : List<Meditation>) : RecyclerView.Adapter<DisplayMeditationsAdapter.MeditationViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MeditationViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.display_meditations_item, parent, false)

        return MeditationViewHolder(view)
    }

    override fun onBindViewHolder(holder: MeditationViewHolder, position: Int) {
        holder.bind(meditations[position])
    }

    override fun getItemCount(): Int {
        return meditations.size
    }


class MeditationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
{

    fun bind(meditation : Meditation)
    {
        setDescription(meditation.description)
        setDate(meditation.date)
        setDuration(meditation.duration)

    }

   private fun setDescription(description : String)
    {
        itemView.findViewById<TextView>(R.id.description_textview).text = description

    }
    private fun setDuration(duration : Int)
    {
        itemView.findViewById<TextView>(R.id.duration_textview).text = duration.toString() + " minutes"
    }
    private fun setDate(date : String)
    {
        itemView.findViewById<TextView>(R.id.date_textview).text = date
    }

}
}