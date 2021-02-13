package com.example.mysamplemiddledev.model.habr_example

data class Result(val total_count: Int,
                  val incomplete_results: Boolean,
                  val items: List<User>)
