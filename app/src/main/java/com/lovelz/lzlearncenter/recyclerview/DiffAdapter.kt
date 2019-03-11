package com.lovelz.lzlearncenter.recyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.lovelz.lzlearncenter.R

/**
 * @author lovelz
 * @date on 2019/3/7.
 */
class DiffAdapter(private val context: Context) : RecyclerView.Adapter<DiffAdapter.ViewHolder>() {

    private var mInflater: LayoutInflater = LayoutInflater.from(context)
    private var mList: MutableList<DiffBean> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(mInflater.inflate(R.layout.layout_recycler_item, parent, false))
    }

    override fun getItemCount(): Int = mList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.nameText.text = mList[position].name
        holder.infoText.text = mList[position].info
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameText: TextView = itemView.findViewById(R.id.name)
        val infoText: TextView = itemView.findViewById(R.id.info)
    }

    fun setData(list: List<DiffBean>) {
        val result: DiffUtil.DiffResult = DiffUtil.calculateDiff(DiffCallback(mList, list), true)
        result.dispatchUpdatesTo(this)
        mList.clear()
        mList.addAll(list)
    }

}