package com.pettsme.showcase.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.pettsme.showcase.characterdetails.presentation.CharacterDetailsScreen
import com.pettsme.showcase.characterlist.presentation.CharacterListScreen

@Composable
fun MainNavHost(
    navController: NavHostController,
    modifier: Modifier,
) {
    NavHost(
        navController = navController,
        modifier = modifier,
        startDestination = "characters",
    ) {
        composable(
            route = "characters",
        ) {
            CharacterListScreen(
                navigateToDetails = { navController.navigate("characters/$it") },
            )
        }
        composable(
            route = "characters/{id}",
            arguments = listOf(navArgument("id") { type = NavType.IntType }),
        ) { navBackStackEntry ->
            CharacterDetailsScreen(navBackStackEntry.arguments?.getInt("id"))
        }
    }
}
