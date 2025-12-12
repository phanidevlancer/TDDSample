package com.phani.tddsample.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.phani.tddsample.presentation.userdetail.UserDetailScreen
import com.phani.tddsample.presentation.userlist.UserListScreen

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Route.UserList.route
    ) {
        composable(Route.UserList.route) {
            UserListScreen(
                onUserClick = { userId ->
                    navController.navigate(Route.UserDetail.createRoute(userId))
                }
            )
        }

        composable(
            route = Route.UserDetail.route,
            arguments = listOf(
                navArgument("userId") {
                    type = NavType.StringType
                }
            )
        ) {
            UserDetailScreen(
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
    }
}
