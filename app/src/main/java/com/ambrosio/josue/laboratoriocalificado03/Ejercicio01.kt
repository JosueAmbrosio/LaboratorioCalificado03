package com.ambrosio.josue.laboratoriocalificado03

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ambrosio.josue.laboratoriocalificado03.databinding.ActivityEjercicio01Binding

class Ejercicio01 : AppCompatActivity() {
    private lateinit var viewModel: TeacherViewModel
    private lateinit var binding: ActivityEjercicio01Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEjercicio01Binding.inflate(layoutInflater)
        setContentView(binding.root)

        initViewModel()
        observeValues()
        viewModel.getTeachers()
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this).get(TeacherViewModel::class.java)
    }

    private fun observeValues() {
        viewModel.teachers.observe(this, Observer { teachers ->
            setupRecyclerView(teachers)
        })
    }

    private fun setupRecyclerView(teachers: List<Teacher>) {
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@Ejercicio01)
            adapter = TeacherAdapter(teachers, ::dialPhoneNumber, ::sendEmail)
        }
    }

    private fun dialPhoneNumber(phoneNumber: String) {
        val intent = Intent(Intent.ACTION_DIAL).apply {
            Toast.makeText(this@Ejercicio01, "Se presiono de forma rapida", Toast.LENGTH_SHORT).show()
            data = Uri.parse("tel:$phoneNumber")
        }
        startActivity(intent)
    }

    private fun sendEmail(email: String) {
        val intent = Intent(Intent.ACTION_SENDTO).apply {
            Toast.makeText(this@Ejercicio01, "Se mantuvo presionado el dedo", Toast.LENGTH_SHORT).show()
            data = Uri.parse("mailto:$email")
        }
        startActivity(intent)
    }
}
