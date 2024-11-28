package com.pettsme.showcase.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.pettsme.showcase.characterdetails.presentation.CharacterDetailsScreen
import com.pettsme.showcase.characters.presentation.CharacterListScreen
import com.pettsme.showcase.navigation.NavigationConstants.NAV_PARAM_ID
import com.pettsme.showcase.navigation.NavigationConstants.ROUTE_CHARACTERS

@Composable
fun MainNavHost(
    navController: NavHostController,
    modifier: Modifier,
) {
    NavHost(
        navController = navController,
        modifier = modifier,
        startDestination = ROUTE_CHARACTERS,
    ) {
        composable(
            route = ROUTE_CHARACTERS,
        ) {
            CharacterListScreen(
                navigateToDetails = { navController.navigate("$ROUTE_CHARACTERS/$it") },
            )
        }
        composable(
            route = "$ROUTE_CHARACTERS/{${NAV_PARAM_ID}}",
            arguments = listOf(navArgument(NAV_PARAM_ID) { type = NavType.IntType }),
        ) {
            CharacterDetailsScreen()
        }
    }
}
