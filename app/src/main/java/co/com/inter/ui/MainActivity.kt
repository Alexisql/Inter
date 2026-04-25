package co.com.inter.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import co.com.inter.ui.component.ErrorHandlerContext
import co.com.inter.ui.navigation.Navigation
import co.com.inter.ui.theme.InterTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navHostController = rememberNavController()
            InterTheme {
                ErrorHandlerContext {
                    Navigation(
                        navController = navHostController
                    )
                }
            }
        }
    }
}