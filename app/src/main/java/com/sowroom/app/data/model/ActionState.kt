package com.sowroom.app.data.model

data class ActionState<T>(
    val data : T? = null,
    val massage: String? = null,
    val isSuccess: Boolean = true,
    var isConsumed: Boolean = false
)
