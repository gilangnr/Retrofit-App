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


//        showPost()


        createPost()

    }

    private fun createPost() {
        val rvPost = findViewById<RecyclerView>(R.id.rvPost)
        val tvResponseCode = findViewById<TextView>(R.id.tvResponseCode)
        RetrofitClient.instance.createPost(
            17,
            "retrofitTitle",
            "retrofitBody"
        ).enqueue(object : Callback<CreatePostResponse>{
            override fun onResponse(
                call: Call<CreatePostResponse>,
                response: Response<CreatePostResponse>
            ) {
                val responseText = "Response code: ${response.code()}\n" +
                        "Title: ${response.body()?.title}\n" +
                        "Body: ${response.body()?.body}\n" +
                        "UserId: ${response.body()?.userId}\n" +
                        "id: ${response.body()?.id}\n"

                tvResponseCode.text = responseText
            }

            override fun onFailure(call: Call<CreatePostResponse>, t: Throwable) {
                tvResponseCode.text = t.message
            }

        })
    }

    private fun showPost() {
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