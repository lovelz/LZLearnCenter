package com.lovelz.lzlearncenter.design.observer

/**
 * @author lovelz
 * @date on 2019/3/11.
 */
interface Subject {

    fun attach(observer: Observer)

    fun detach(observer: Observer)

    fun notify(message: String)

}