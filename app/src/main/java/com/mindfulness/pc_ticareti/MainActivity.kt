package com.mindfulness.pc_ticareti

import android.annotation.SuppressLint
import android.content.Intent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var adapter: MainRecyclerAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rv_project: RecyclerView = findViewById(R.id.rv_project)

        getData(rv_project)

        adapter?.clickStatus?.observe(this, Observer {
            Log.e("Activity", "Clicked on itemssss ")

        })

        rv_project?.adapter = MainRecyclerAdapter(this,getCompanies()) {

            Intent(this, RepairComputerActivity::class.java).also {
                it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(it)
            }

        }

       /* adapter?.clickStatus?.observe(this, Observer {

            Toast.makeText(this,"aaaaa",Toast.LENGTH_SHORT).show()

            /*Intent(this, MainActivity::class.java).also {
                it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(it)
            }*/
        })*/

        //rv_project.adapter.apply { Toast.makeText(this,"adadad",Toast.LENGTH_SHORT).  }
    }

    //@SuppressLint("WrongConstant")
    @SuppressLint("WrongConstant")
    private fun getData(rv_project: RecyclerView) {

        rv_project.adapter = adapter
        rv_project.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
        //rv_project.adapter = MainRecyclerAdapter(getCompanies())

        /*adapter!!.onItemClick = { project ->
            Toast.makeText(this,"aaaaa",Toast.LENGTH_SHORT).show()
            // do something with your item
            Log.d("TAG", project.projectId)
        }*/
    }

    override fun onStart() {
        super.onStart()
        stars.onStart()
    }

    override fun onStop() {
        stars.onStop()
        super.onStop()
    }

}

    private fun getCompanies(): MutableList<Project> {
    val companies = mutableListOf(

        Project("1", "Bilgisayar Tamir Et", "", "", "", "", "", 1)
    )

    return companies
}

