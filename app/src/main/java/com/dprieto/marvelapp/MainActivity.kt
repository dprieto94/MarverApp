package com.dprieto.marvelapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.dprieto.marvelapp.domain.MarvelCharacter
import com.dprieto.marvelapp.ui.components.Screens
import com.dprieto.marvelapp.ui.herodetail.HeroDetailScreen
import com.dprieto.marvelapp.ui.herolist.HeroListScreen
import com.dprieto.marvelapp.ui.splash.SplashScreen
import com.dprieto.marvelapp.ui.theme.MarvelAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MarvelAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {

                    val navController = rememberNavController()

                    NavHost(
                        navController = navController,
                        startDestination = Screens.Splash.route
                    ) {
                        composable(Screens.Splash.route) {
                            SplashScreen {
                                navController.navigate(Screens.HeroList.route)
                            }
                        }
                        composable(Screens.HeroList.route) {
                            HeroListScreen { character ->
                                navController.currentBackStackEntry?.arguments?.putParcelable(Screens.HeroDetail.ARG_CHARACTER, character)
                                navController.navigate(Screens.HeroDetail.route)
                            }
                        }
                        composable(
                            Screens.HeroDetail.route) { backStackEntry ->

                            //val character = backStackEntry.arguments?.getParcelable(Screens.HeroDetail.ARG_CHARACTER) ?: MarvelCharacter("1", "", "")
                            val character = navController.previousBackStackEntry?.arguments?.getParcelable<MarvelCharacter>(Screens.HeroDetail.ARG_CHARACTER) ?: MarvelCharacter("1", "", "")

                            HeroDetailScreen(character){
                                navController.navigateUp()
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MarvelAppTheme {
        Greeting("Android")
    }
}