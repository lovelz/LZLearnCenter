package com.lovelz.lzlearncenter.recyclerview

import android.text.TextUtils
import androidx.recyclerview.widget.DiffUtil

/**
 * @author lovelz
 * @date on 2019/3/7.
 */
class DiffCallback(private val oldList: List<DiffBean>, private val newList: List<DiffBean>) : DiffUtil.Callback() {

    /**
     * 被DiffUtil调用，用来判断 两个对象是否是相同的Item。
     * 例如，如果你的Item有唯一的id字段，这个方法就 判断id是否相等，或者重写equals方法
     */
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].name == newList[newItemPosition].name
    }

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    /**
     * 被DiffUtil调用，用来检查 两个item是否含有相同的数据
     * DiffUtil用返回的信息（true false）来检测当前item的内容是否发生了变化
     */
    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldBean = oldList[oldItemPosition]
        val newBean = newList[newItemPosition]

        if (TextUtils.equals(oldBean.info, newBean.info)) {
            return false
        }

        return true
    }
}