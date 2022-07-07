package com.example.meditationtimer.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.annotation.LayoutRes
import com.example.meditationtimer.R

class BellSoundArrayAdapter(
    context : Context, private val bellNames : List<String>,
    @LayoutRes private val layoutRes: Int,
) : ArrayAdapter<String>(context, layoutRes, bellNames)
{
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return createViewFromResource(position, convertView, parent)
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return createViewFromResource(position, convertView, parent)
    }


    private fun createViewFromResource(position: Int, convertView: View?, parent: ViewGroup?): View{
     //  val view: TextView = convertView as TextView? ?: LayoutInflater.from(context).inflate(layoutRes, parent, false) as TextView
        val view = LayoutInflater.from(context).inflate(layoutRes, parent, false)
        view.findViewById<TextView>(R.id.bell_name).text = bellNames[position]

        return view
    }


}