package com.example.retrofitapp

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    private val list = ArrayList<PostResponse>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val rvPost = findViewById<RecyclerView>(R.id.rvPost)
        val tvResponseCode = findViewById<TextView>(R.id.tvResponseCode)


        rvPost.setHasFixedSize(true)
        rvPost.layoutManager = LinearLayoutManager(this)

        RetrofitClient.instance.getPost().enqueue(object: Callback<ArrayList<PostResponse>> {
            override fun onResponse(
                call: Call<ArrayList<PostResponse>>,
                response: Response<ArrayList<PostResponse>>
            ) {
                val responseCode = response.code().toString()
                tvResponseCode.text = responseCode
                response.body()?.let { list.addAll(it)}
                val adapter = PostAdapter(list)
                rvPost.adapter = adapter
            }

            override fun onFailure(call: Call<ArrayList<PostResponse>>, t: Throwable) {

            }

        })
    }
}