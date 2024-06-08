package com.ambrosio.josue.laboratoriocalificado03
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TeacherViewModel : ViewModel() {
    private val _teachers = MutableLiveData<List<Teacher>>()
    val teachers: LiveData<List<Teacher>> get() = _teachers

    private val apiService = RetrofitClient.apiService

    fun getTeachers() {
        apiService.getTeachers().enqueue(object : Callback<TeacherResponse> {
            override fun onResponse(call: Call<TeacherResponse>, response: Response<TeacherResponse>) {
                if (response.isSuccessful) {
                    _teachers.value = response.body()?.teachers
                } else {
                }
            }

            override fun onFailure(call: Call<TeacherResponse>, t: Throwable) {
                // Errores de conexion
            }
        })
    }
}
