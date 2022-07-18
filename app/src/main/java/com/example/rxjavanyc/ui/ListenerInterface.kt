package com.example.rxjavanyc.ui

interface ListenerInterface {
    fun openDetails(dbn: String?, name: String?, loc: String?, email: String?, phone: String?)

    interface ListClickEvent {
        fun clickDetails(dbn: String?, name: String?, loc: String?, email: String?, phone: String?)
    }
}