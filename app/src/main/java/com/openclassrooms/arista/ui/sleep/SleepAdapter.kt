package com.openclassrooms.arista.ui.sleep

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.openclassrooms.arista.R
import com.openclassrooms.arista.domain.model.Sleep
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

/**
 * Adapter and view Holder for the recyclerView in SleepFragment.
 */
class SleepAdapter(private var sleeps: List<Sleep>) :
    RecyclerView.Adapter<SleepAdapter.SleepViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SleepViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_sleep, parent, false)
        return SleepViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: SleepViewHolder, position: Int) {
        val sleep = sleeps[position]
        val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")

        // Transforms milliseconds into a date without taking into account time zones or calendars.
        // Using Instant.ofEpochMilli(sleep.startTime), you can convert the timestamp stored in
        // startTime into an Instant object for use in other date and time manipulation operations.
        val startTime = Instant.ofEpochMilli(sleep.startTime)

        val dateTime = LocalDateTime.ofInstant(startTime, ZoneId.systemDefault())
        holder.tvStartTime.text = "Start Time: ${dateTime.format(formatter)}"
        holder.tvDuration.text = "Duration: ${sleep.duration} minutes"
        holder.tvQuality.text = "Quality: ${sleep.quality}"
    }

    override fun getItemCount() = sleeps.size

    inner class SleepViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvStartTime: TextView
        var tvDuration: TextView
        var tvQuality: TextView

        init {
            tvStartTime = itemView.findViewById(R.id.tv_start_time)
            tvDuration = itemView.findViewById(R.id.tv_duration)
            tvQuality = itemView.findViewById(R.id.tv_quality)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(newSleeps: List<Sleep>) {
        this.sleeps = newSleeps
        notifyDataSetChanged()
    }
}
