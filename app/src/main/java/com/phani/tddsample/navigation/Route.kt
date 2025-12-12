package com.phani.tddsample.navigation

sealed class Route(val route: String) {
    data object UserList : Route("user_list")
    data object UserDetail : Route("user_detail/{userId}") {
        fun createRoute(userId: Int) = "user_detail/$userId"
    }
}
