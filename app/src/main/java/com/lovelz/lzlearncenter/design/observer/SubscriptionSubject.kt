package com.lovelz.lzlearncenter.design.observer

/**
 * @author lovelz
 * @date on 2019/3/11.
 */
class SubscriptionSubject : Subject {

    private val weixinUserList = arrayListOf<Observer>()

    override fun attach(observer: Observer) {
        weixinUserList.add(observer)
    }

    override fun detach(observer: Observer) {
        weixinUserList.remove(observer)
    }

    override fun notify(message: String) {
        for (observer in weixinUserList) {
            observer.update(message)
        }
    }
}