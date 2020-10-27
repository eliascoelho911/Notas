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
import br.com.eliascoelho911.notas.ui.CriadorDeAppBarLayout
import br.com.eliascoelho911.notas.ui.CriadorDeToolbarEAppBarConfiguration
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
        configuraViewModel()
        configuraNavViewComNavController()
    }

    private fun configuraViewModel() {
        val criadorDeAppBarLayout = CriadorDeAppBarLayout(
            layoutInflater,
            content_main_linear_layout
        )
        val criadorDeAppBarConfiguration = CriadorDeAppBarConfiguration(drawer_layout)
        viewModel.configura(
            navController = navController,
            criadorDeToolbarEAppBarConfiguration = CriadorDeToolbarEAppBarConfiguration(
                criadorDeAppBarLayout,
                criadorDeAppBarConfiguration),
            aoAlterarAToolbar = { toolbar, appBarConfiguration ->
                configuraAcaoAoAlterarToolbar(toolbar, appBarConfiguration)
            })
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