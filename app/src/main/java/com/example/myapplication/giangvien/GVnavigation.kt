package com.example.myapplication.giangvien

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.example.myapplication.*
import com.example.myapplication.login.LoginGV
import kotlinx.android.synthetic.main.gvnav.*
import com.example.myapplication.login.MainActivity

class GVnavigation : AppCompatActivity() {
    lateinit var toogle : ActionBarDrawerToggle
    private lateinit var drawerLayout: DrawerLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.gvnav)

        drawerLayout = findViewById(R.id.drawergvnav)

        toogle = ActionBarDrawerToggle( this, drawerLayout, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toogle)
        toogle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportFragmentManager.beginTransaction().replace(R.id.framelayoutgvnav, Gvthongtinday()).commit()
        navgvview.setNavigationItemSelectedListener {

            it.isChecked = true

            when(it.itemId){
                R.id.gvthongtinday -> replaceFragment(Gvthongtinday(),it.title.toString())
                R.id.gvdslop -> replaceFragment(Gvdslop(),it.title.toString())
                R.id.gvinfo -> replaceFragment(Gvinfo(),it.title.toString())
                R.id.gvpasschange -> replaceFragment(Gvpasschange(),it.title.toString())
                R.id.account -> this.finish()
            }
            true
        }
    }
    private fun replaceFragment(fragment: Fragment, title : String){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.framelayoutgvnav, fragment)
        fragmentTransaction.commit()
        drawerLayout.closeDrawers()
        setTitle(title)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toogle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}
