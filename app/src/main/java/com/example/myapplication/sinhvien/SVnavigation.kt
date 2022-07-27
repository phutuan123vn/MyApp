package com.example.myapplication.sinhvien

import android.content.Intent
import android.icu.text.TimeZoneFormat
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import kotlinx.android.synthetic.main.svnav.*
import androidx.fragment.app.Fragment
import com.example.myapplication.*
import com.example.myapplication.login.ForgetPass
import com.example.myapplication.login.LoginSV
import com.example.myapplication.login.MainActivity

class SVnavigation : AppCompatActivity() {
    lateinit var toogle : ActionBarDrawerToggle
    private lateinit var drawerLayout: DrawerLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.svnav)

        drawerLayout = findViewById(R.id.drawersvnav)

        toogle = ActionBarDrawerToggle( this, drawerLayout, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toogle)
        toogle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportFragmentManager.beginTransaction().replace(R.id.framelayoutsvnav, Svlichhoc()).commit()
        navsvview.setNavigationItemSelectedListener {

            it.isChecked = true

            when(it.itemId){
                R.id.svlichhoc -> replaceFragment(Svlichhoc(),it.title.toString())
                R.id.svdiemdanh -> replaceFragment(Svdiemdanh(),it.title.toString())
                R.id.svinfo -> replaceFragment(Svinfo(),it.title.toString())
                R.id.svdkmon -> replaceFragment(Svdkmon(),it.title.toString())
                R.id.svpasschange -> replaceFragment(Svpasschange(),it.title.toString())
                R.id.account -> finish()
            }
            true
        }
    }

    private fun replaceFragment(fragment: Fragment, title : String){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.framelayoutsvnav, fragment)
        fragmentTransaction.commit()
        drawerLayout.closeDrawers()
        setTitle(title)
    }

    override fun finish() {
        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        startActivity(intent)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toogle.onOptionsItemSelected(item)){
            return true
        }
            return super.onOptionsItemSelected(item)
    }
    override fun onBackPressed() {}
}
