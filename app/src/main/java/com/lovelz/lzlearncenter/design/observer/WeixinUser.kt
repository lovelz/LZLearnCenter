package com.lovelz.lzlearncenter.design.observer

import android.util.Log

/**
 * @author lovelz
 * @date on 2019/3/11.
 */
class WeixinUser constructor(private val name: String) : Observer {

    override fun update(message: String) {
        Log.d("Observer", "$name - $message")
    }
}