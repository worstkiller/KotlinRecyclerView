package com.androidbuffer.kotlinrecyclerview

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private var listOfItems = ArrayList<DataItems>()
    private var adapter: RecyclerAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.rvMainHolder)
        setUpRecyclerView()
    }

    /*
    set up recyclerview
     */
    private fun setUpRecyclerView() {
        adapter = RecyclerAdapter(getDataItems(),this)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }

    /**
     * get the data items or list
     */
    fun getDataItems(): ArrayList<DataItems> {
        val array = ArrayList<DataItems>()
        for (i in 0..10) {
            array.add(DataItems(null, null))
        }
        return array
    }
}

/**
 * class for data items
 */
class DataItems(var name: String?, var gender: String?)