package com.example.myapplication.quanly

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import kotlinx.android.synthetic.main.qlnav.*
import androidx.fragment.app.Fragment
import com.example.myapplication.*
import com.example.myapplication.login.LoginQL
import com.example.myapplication.login.LoginSV
import com.example.myapplication.login.MainActivity

class QLnavigation : AppCompatActivity() {
    lateinit var toogle : ActionBarDrawerToggle
    private lateinit var drawerLayout: DrawerLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.qlnav)

        drawerLayout = findViewById(R.id.drawerqlnav)

        toogle = ActionBarDrawerToggle( this, drawerLayout, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toogle)
        toogle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportFragmentManager.beginTransaction().replace(R.id.framelayoutqlnav, TKB()).commit()
        navqlview.setNavigationItemSelectedListener {

            it.isChecked = true

            when(it.itemId){
                R.id.TKB -> replaceFragment(TKB(),it.title.toString())
                R.id.GVdsgv  -> replaceFragment(GVdsgv(),it.title.toString())
                R.id.GVqldl -> replaceFragment(GVqldl(),it.title.toString())
                R.id.SVdssv -> replaceFragment(SVdssv(),it.title.toString())
                R.id.SVqldl -> replaceFragment(SVqldl(),it.title.toString())
                R.id.SVqltk -> replaceFragment(SVqltk(),it.title.toString())
                R.id.account -> finish()
            }
            true
        }
    }
    private fun replaceFragment(fragment: Fragment, title : String){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.framelayoutqlnav, fragment)
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
