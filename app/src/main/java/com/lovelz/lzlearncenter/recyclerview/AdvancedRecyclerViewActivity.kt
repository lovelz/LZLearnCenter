package com.lovelz.lzlearncenter.recyclerview

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.lovelz.lzlearncenter.BaseActivity
import com.lovelz.lzlearncenter.R
import kotlinx.android.synthetic.main.activity_advanced_recyclerview.*

/**
 * @author lovelz
 * @date on 2019/3/7.
 */
class AdvancedRecyclerViewActivity : BaseActivity() {

    val mList: MutableList<DiffBean> = mutableListOf()
    val mList1: MutableList<DiffBean> = mutableListOf()

    var diffAdapter: DiffAdapter? = null

    override fun getLayoutId(): Int = R.layout.activity_advanced_recyclerview

    override fun initEvent() {
        super.initEvent()

        recyclerview.layoutManager = LinearLayoutManager(this)
        diffAdapter = DiffAdapter(this)
        recyclerview.adapter = diffAdapter

        mList.apply {
            add(DiffBean("张三", "哈哈哈哈哈"))
            add(DiffBean("李四", "哈哈哈哈哈"))
        }

        diffAdapter?.setData(mList)

    }

    fun modify(view: View) {
        mList1.apply {
            add(DiffBean("王五", "哈哈哈哈哈"))
            add(DiffBean("老六", "哈哈哈哈哈"))
        }
        diffAdapter?.setData(mList1)
    }

}