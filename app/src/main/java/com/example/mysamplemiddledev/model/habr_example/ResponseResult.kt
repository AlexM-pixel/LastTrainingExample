package com.example.mysamplemiddledev.model.habr_example

data class ResponseResult(val total_count: Int,
                          val incomplete_results: Boolean,
                          val items: List<User>)
