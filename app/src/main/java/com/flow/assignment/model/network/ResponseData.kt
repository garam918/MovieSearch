package com.flow.assignment.model.network

data class ResponseData(
    val lastBuildDate : String,
    val total : Int,
    val start : Int,
    val display : Int,
    val items : List<Item>
)

data class Item(
    val title : String,
    val link : String,
    val image : String,
    val subtitle : String,
    val pubDate : String,
    val director : String,
    val actor : String,
    val userRating : String
)
