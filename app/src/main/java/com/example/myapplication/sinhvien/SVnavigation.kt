package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.myapplication.login.ForgetPass
import com.example.myapplication.login.MainActivity
import kotlinx.android.synthetic.main.svnav.*

class SVnavigation : AppCompatActivity() {
    private lateinit var navController: NavController
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var listener: NavController.OnDestinationChangedListener
//    private lateinit var toogle: ActionBarDrawerToggle


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.svnav)

//        toogle = ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close)
//        drawerLayout.addDrawerListener(toogle)
//        toogle.syncState()
//        supportActionBar?.setDisplayHomeAsUpEnabled(true)

//        navsvview.setNavigationItemSelectedListener {
//            when(it.itemId) {
//                R.id.svlichhoc -> Toast.makeText(applicationContext,
//                    "clicked Lichhoc", Toast.LENGTH_SHORT).show()
//                R.id.svdiemdanh -> Toast.makeText(applicationContext,
//                    "clicked Diemdanh", Toast.LENGTH_SHORT).show()
//                R.id.svinfo -> Toast.makeText(applicationContext,
//                    "clicked Info", Toast.LENGTH_SHORT).show()
//                R.id.svdkmon -> Toast.makeText(applicationContext,
//                    "clicked ĐKMon", Toast.LENGTH_SHORT).show()
//                R.id.svpasschange -> Toast.makeText(applicationContext,
//                    "clicked Passchange", Toast.LENGTH_SHORT).show()
//            }
//            true
//       }

        navController = findNavController(R.id.fragmentsvnav)
        drawerLayout = findViewById(R.id.drawersvnav)
        appBarConfiguration = AppBarConfiguration(navController.graph,drawerLayout)
        navsvview.setupWithNavController(navController)
        setupActionBarWithNavController(navController, appBarConfiguration)

    }
    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.fragmentsvnav)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        if (toogle.onOptionsItemSelected(item)){
//            return true
//        }
//        return super.onOptionsItemSelected(item)
//    }

}

