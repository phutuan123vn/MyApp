package com.example.myapplication.sinhvien

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.example.myapplication.*
import com.example.myapplication.login.MainActivity
import kotlinx.android.synthetic.main.svdkmon.*
import kotlinx.android.synthetic.main.svinfochange.*
import kotlinx.android.synthetic.main.svnav.*

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
        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toogle.onOptionsItemSelected(item)){
            hidekeyboard()
            return true
        }
            return super.onOptionsItemSelected(item)
    }
    override fun onBackPressed() {}
    fun hidekeyboard() {
        val xem = this.currentFocus
        if (xem != null) {
            val hide = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            hide.hideSoftInputFromWindow(xem.windowToken, 0)
        }
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
        currentFocus?.clearFocus()
    }
    fun closekeyboard(view: View) {
        hidekeyboard()
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
    }
}


