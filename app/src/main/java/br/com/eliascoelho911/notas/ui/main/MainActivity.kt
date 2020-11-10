package br.com.eliascoelho911.notas.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil.setContentView
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import br.com.eliascoelho911.notas.R
import br.com.eliascoelho911.notas.databinding.ActivityMainBinding
import br.com.eliascoelho911.notas.ui.extensions.escondeTeclado
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
        configuraBinding()
        configuraViewModel()
        configuraNavView()
        configuraToolbar()
        configuraBottomAppBar()
        bottomAppBarSemNavigationIcon()
    }

    private fun configuraBinding() {
        setContentView<ActivityMainBinding>(this, R.layout.activity_main).apply {
            lifecycleOwner = this@MainActivity
            propriedadesBottomAppBar = viewModel.propriedadesBottomAppBar
            propriedadesFab = viewModel.propriedadesFab
            propriedadesToolbar = viewModel.propriedadesToolbar
        }
    }

    private fun bottomAppBarSemNavigationIcon() {
        navController.addOnDestinationChangedListener { _, _, _ ->
            bottomappbar.navigationIcon = null
            viewModel.propriedadesToolbar.toolbarEditText.limpaTexto()
            escondeTeclado()
        }
    }

    private fun configuraViewModel() {
        appBarConfiguration = AppBarConfiguration(setOf(R.id.nav_notas), drawer_layout)
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