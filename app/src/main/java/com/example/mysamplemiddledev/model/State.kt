package com.example.mysamplemiddledev.model

enum class State(var stateDescription:String) {
    NO_LOAD(""),
    LOADED("count loaded files"),
    LOADING("files download"),
    ERROR("error"),
    SAVED("count saved files")
}