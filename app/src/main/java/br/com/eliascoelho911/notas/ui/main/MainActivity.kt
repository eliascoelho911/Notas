package br.com.eliascoelho911.notas.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import br.com.eliascoelho911.notas.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by viewModel()
    private lateinit var appBarConfiguration: AppBarConfiguration
    private val navController: NavController by lazy {
        findNavController(R.id.nav_host)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        configuraViewModel()
        configuraNavView()
        configuraToolbar()
        configuraBottomAppBar()
        bottomAppBarSemNavigationIcon()
    }

    private fun bottomAppBarSemNavigationIcon() {
        navController.addOnDestinationChangedListener { _, _, _ ->
            bottomappbar.navigationIcon = null
        }
    }

    private fun configuraViewModel() {
        appBarConfiguration = AppBarConfiguration(setOf(R.id.nav_notas), drawer_layout)
        viewModel.modificadorDeFab = ModificadorDeFab(fab)
        viewModel.modificadorDeBottomAppBar = ModificadorDeBottomAppBar(bottomappbar)
        viewModel.modificadorDeToolbar = ModificadorDeToolbar(toolbar)
    }

    private fun configuraToolbar() {
        setSupportActionBar(toolbar)
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    private fun configuraNavView() {
        nav_view.setupWithNavController(navController)
    }

    private fun configuraBottomAppBar() {
        bottomappbar.setupWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}