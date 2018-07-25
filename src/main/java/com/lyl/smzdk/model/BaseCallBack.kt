package com.lyl.smzdk.model


data class BaseCallBack<T>(
        var code: Int = 0,
        var msg: String = "",
        var data: T
)