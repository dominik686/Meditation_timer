package com.example.meditationtimer.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.meditationtimer.R
import com.example.meditationtimer.models.Meditation
import com.example.meditationtimer.models.MoodEmoji

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
        setMoodEmoji(meditation.emoji)
        itemView.setOnClickListener()
        {
            val animation = AnimationUtils.loadAnimation(itemView.context, R.anim.shake)
            it.startAnimation(animation)
        }
    }

   private fun setDescription(description : String)
    {

        if(description.length <= 50)
            itemView.findViewById<TextView>(R.id.description_textview).text = description
        else
            itemView.findViewById<TextView>(R.id.description_textview).text =
                description.subSequence(0, 50) .toString()+ "...   tap to see more"

    }
    private fun setDuration(duration : Int)
    {
        itemView.findViewById<TextView>(R.id.duration_textview).text = duration.toString() + " minutes"
    }
    private fun setDate(date : String)
    {
        itemView.findViewById<TextView>(R.id.date_textview).text = date
    }

    private fun setMoodEmoji(moodEmoji: MoodEmoji)
    {
        itemView.findViewById<ImageView>(R.id.emoji).setImageResource(getDrawableId(moodEmoji))
    }

    fun getDrawableId(moodEmoji: MoodEmoji) : Int
    {
        when(moodEmoji.name)
        {
            MoodEmoji.VERY_BAD.name -> return R.drawable.sad
            MoodEmoji.BAD.name -> return R.drawable.confused
            MoodEmoji.NEUTRAL.name -> return R.drawable.neutral
            MoodEmoji.GOOD.name -> return R.drawable.smile
            MoodEmoji.GREAT.name -> return R.drawable.smiling
        }

        return R.drawable.neutral
    }

}
}