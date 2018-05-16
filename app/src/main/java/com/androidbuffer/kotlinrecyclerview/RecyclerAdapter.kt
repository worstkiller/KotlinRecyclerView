package com.androidbuffer.kotlinrecyclerview

import android.content.Context
import android.support.v7.widget.AppCompatEditText
import android.support.v7.widget.AppCompatSpinner
import android.support.v7.widget.RecyclerView
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.util.SparseArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter

/**
 * Created by AndroidBuffer on 16/5/18.
 */
class RecyclerAdapter(dataItems: ArrayList<DataItems>, context: Context) : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    val layoutCode = android.support.v7.appcompat.R.layout.support_simple_spinner_dropdown_item
    val listOfItems = dataItems;
    val adapterGender = ArrayAdapter<String>(context, layoutCode, context.resources.getStringArray(R.array.genderArray))
    val genderCodeList = SparseArray<Int>()

    override fun getItemCount(): Int {
        return listOfItems.size
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        val data = listOfItems[holder!!.adapterPosition]
        holder.etName?.setText(data.name)
        holder.spGender?.adapter = adapterGender
        holder.spGender?.setSelection(adapterGender.getPosition(data.gender))
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent?.context).inflate(R.layout.items_data, parent, false))
    }

    inner class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        val etName: AppCompatEditText? = itemView?.findViewById(R.id.etName)
        val spGender: AppCompatSpinner? = itemView?.findViewById(R.id.spGender)

        init {
            //listener for gender selection
            spGender?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(p0: AdapterView<*>?) {

                }

                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    listOfItems[adapterPosition].gender = p0?.selectedItem.toString()
                    Log.d("TAG", p0?.selectedItem.toString())
                }
            }

            //listener for text watcher
            etName?.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(p0: Editable?) {
                    listOfItems[adapterPosition].name = p0.toString()
                }

                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }
            })
        }
    }
}