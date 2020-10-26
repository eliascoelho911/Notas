package br.com.eliascoelho911.notas.ui.main

import android.os.Bundle
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
import br.com.eliascoelho911.notas.ui.CriadorDeToolbar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private var appBarConfiguration: AppBarConfiguration? = null
    private val viewModel: MainViewModel by viewModel()
    private val navController: NavController by lazy {
        findNavController(R.id.nav_host)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel.configura(
            navController = navController,
            criadorDeToolbar = CriadorDeToolbar(layoutInflater, content_main_linear_layout),
            criadorDeAppBarConfiguration = CriadorDeAppBarConfiguration(drawer_layout),
            aoAlterarAToolbar = { toolbar, appBarConfiguration ->
                configuraAcaoAoAlterarToolbar(toolbar, appBarConfiguration)
            })
        configuraNavViewComNavController()
    }

    private fun configuraAcaoAoAlterarToolbar(
        toolbar: Toolbar,
        appBarConfiguration: AppBarConfiguration?
    ) {
        setSupportActionBar(toolbar)
        this.appBarConfiguration = appBarConfiguration
        appBarConfiguration?.run { setupActionBarWithNavController(navController, this) }
    }

    private fun configuraNavViewComNavController() {
        nav_view.setupWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return appBarConfiguration?.run { navController.navigateUp(this) || super.onSupportNavigateUp() }
            ?: super.onSupportNavigateUp()
    }
}