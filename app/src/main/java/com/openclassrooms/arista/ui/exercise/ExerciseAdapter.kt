package com.openclassrooms.arista.ui.exercise

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.openclassrooms.arista.R
import com.openclassrooms.arista.domain.model.Exercise
import com.openclassrooms.arista.ui.exercise.ExerciseAdapter.ExerciseViewHolder
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.time.format.DateTimeFormatter

/**
 * Adapter class for displaying a list of exercises in a RecyclerView.
 *
 * @param context The context implementing the DeleteExerciseInterface.
 */
class ExerciseAdapter(private val context: DeleteExerciseInterface) :
    ListAdapter<Exercise, ExerciseViewHolder>(
        DIFF_CALLBACK
    ) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseViewHolder {
        val itemView: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_exercise, parent, false)
        return ExerciseViewHolder(itemView)
    }

    @OptIn(DelicateCoroutinesApi::class)
    override fun onBindViewHolder(holder: ExerciseViewHolder, position: Int) {
        val exercise = getItem(position)
        val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")
        holder.tvStartTime.text =
            String.format("Start Time: %s", exercise!!.startTime.format(formatter))
        holder.tvDuration.text = String.format("Duration: %d minutes", exercise.duration)
        holder.tvCategory.text = String.format("Category: %s", exercise.category.toString())
        holder.tvIntensity.text = String.format("Intensity: %d", exercise.intensity)
        holder.ivDelete.setOnClickListener { _: View? ->
            GlobalScope.launch {
                context.deleteExercise(exercise)
            }
        }
    }

    /**
     * ViewHolder class for an exercise item in the RecyclerView.
     *
     * @param itemView The view for the exercise item.
     */
    inner class ExerciseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvStartTime: TextView
        var tvDuration: TextView
        var tvCategory: TextView
        var tvIntensity: TextView
        var ivDelete: ImageView

        init {
            tvStartTime = itemView.findViewById(R.id.tv_start_time)
            tvDuration = itemView.findViewById(R.id.tv_duration)
            tvCategory = itemView.findViewById(R.id.tv_category)
            tvIntensity = itemView.findViewById(R.id.tv_intensity)
            ivDelete = itemView.findViewById(R.id.delete)
        }
    }

    companion object {
        private val DIFF_CALLBACK: DiffUtil.ItemCallback<Exercise> =
            object : DiffUtil.ItemCallback<Exercise>() {
                override fun areItemsTheSame(oldItem: Exercise, newItem: Exercise): Boolean {
                    return oldItem === newItem
                }

                override fun areContentsTheSame(oldItem: Exercise, newItem: Exercise): Boolean {
                    return oldItem == newItem
                }
            }
    }
}