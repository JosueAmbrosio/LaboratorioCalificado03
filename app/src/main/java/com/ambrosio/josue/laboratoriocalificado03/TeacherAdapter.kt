package com.ambrosio.josue.laboratoriocalificado03

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ambrosio.josue.laboratoriocalificado03.databinding.ItemTeacherBinding
import com.bumptech.glide.Glide

class TeacherAdapter(
    var list: List<Teacher>,
    private val dialPhoneNumber: (String) -> Unit,
    private val sendEmail: (String) -> Unit
) : RecyclerView.Adapter<TeacherAdapter.TeacherViewHolder>() {

    inner class TeacherViewHolder(private val binding: ItemTeacherBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(teacher: Teacher, clickListener: (Teacher) -> Unit, longClickListener: (Teacher) -> Unit) {
            binding.tvName.text = teacher.name
            binding.tvLastName.text = teacher.last_name
            Glide.with(binding.imgTeacher.context).load(teacher.imageUrl).into(binding.imgTeacher)
            itemView.setOnClickListener { clickListener(teacher) }
            itemView.setOnLongClickListener {
                longClickListener(teacher)
                true
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeacherViewHolder {
        val binding = ItemTeacherBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TeacherViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TeacherViewHolder, position: Int) {
        val teacher = list[position]
        holder.bind(teacher, clickListener = { teacher ->
            dialPhoneNumber(teacher.phone)
        }, longClickListener = { teacher ->
            sendEmail(teacher.email)
        })
    }

    override fun getItemCount(): Int {
        return list.size
    }
}
