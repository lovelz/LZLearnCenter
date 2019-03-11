package com.lovelz.lzlearncenter.recyclerview

/**
 * @author lovelz
 * @date on 2019/3/7.
 */
data class DiffBean(var name: String, var info: String) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || javaClass != other.javaClass) return false
        val diff = other as DiffBean?
        return diff!!.name == name
    }

    override fun hashCode(): Int {
        return name.hashCode()
    }

}