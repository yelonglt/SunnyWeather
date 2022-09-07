package com.sunnyweather.android.extend

import android.text.Editable
import android.text.TextWatcher
import android.widget.TextView
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.debounce

/**
 * author : long.ye
 * e-mail : 354734713@qq.com
 * time   : 2022/09/07 17:42:02
 * desc   : View相关的扩展函数
 */
@OptIn(FlowPreview::class)
fun TextView.textWatcherFlow(): Flow<String> = callbackFlow<String> {
    val textWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

        }

        override fun afterTextChanged(s: Editable?) {
            s?.let { trySend(it.toString()) }
        }
    }
    addTextChangedListener(textWatcher)
    awaitClose {
        removeTextChangedListener(textWatcher)
    }
}.buffer(Channel.CONFLATED).debounce(300L)
