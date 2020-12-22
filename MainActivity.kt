package uz.pdp.uzmobile

import android.Manifest
import android.content.Intent
import android.content.Intent.ACTION_DIAL
import android.content.pm.PackageManager
import android.content.res.Configuration
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.content_main.*
import uz.pdp.uzmobile.utils.MySharedpreference
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        setLocale()

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
        appBarConfiguration = AppBarConfiguration(setOf(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow), drawerLayout)
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)



        icon_balance.setOnClickListener {
            if (ContextCompat.checkSelfPermission(
                    this@MainActivity,
                    Manifest.permission.CALL_PHONE
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    this@MainActivity,
                    arrayOf(Manifest.permission.CALL_PHONE),
                    1
                )
            } else {
                val dial = "tel:$*107" + Uri.encode("#")
                startActivity(Intent(Intent.ACTION_CALL, Uri.parse(dial)))
            }
        }

        icon_operator.setOnClickListener {
            startActivity(Intent(ACTION_DIAL ,Uri.parse("tel: "+ Uri.encode("1099"))))
        }

        icon_home.setOnClickListener {
            navController.navigate(R.id.nav_home)
        }

        icon_news.setOnClickListener {

        }

        icon_setings.setOnClickListener {
            val intent = Intent(this,LanguageActivity::class.java)
            startActivity(intent)
            finish()
        }


    }

    private fun setLocale() {
        val locale = Locale(MySharedpreference.getLanguage())
        Locale.setDefault(locale)

        val config: Configuration = resources.configuration
        config.locale = locale
        resources.updateConfiguration(config, resources.displayMetrics)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}