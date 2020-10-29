package br.com.eliascoelho911.notas.ui.main

import android.media.Image
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import br.com.eliascoelho911.notas.R
import br.com.eliascoelho911.notas.ui.CriadorDeAppBarConfiguration
import br.com.eliascoelho911.notas.ui.CriadorDeAppBarLayout
import br.com.eliascoelho911.notas.ui.CriadorDeToolbar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModel()
    private val navController: NavController by lazy {
        findNavController(R.id.nav_host)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        configuraViewModel()
        configuraNavViewComNavController()
    }

    private fun configuraViewModel() {
        val criadorDeAppBarLayout = CriadorDeAppBarLayout(
            layoutInflater,
            content_main_linear_layout
        )
        viewModel.configura(
            navController = navController,
            criadorDeToolbar = CriadorDeToolbar(criadorDeAppBarLayout),
            criadorDeAppBarConfiguration = CriadorDeAppBarConfiguration(drawer_layout),
            aoAlterarAToolbar = { toolbar, appBarConfiguration ->
                configuraAcaoAoAlterarToolbar(toolbar, appBarConfiguration)
            })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home)
            onBackPressed()
        return super.onOptionsItemSelected(item)
    }

    private fun configuraAcaoAoAlterarToolbar(
        toolbar: Toolbar,
        appBarConfiguration: AppBarConfiguration
    ) {
        setSupportActionBar(toolbar)
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    private fun configuraNavViewComNavController() {
        nav_view.setupWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(viewModel.appBarConfiguration) || super.onSupportNavigateUp()
    }
}