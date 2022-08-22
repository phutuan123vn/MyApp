package com.example.myapplication.giangvien

import android.content.Context
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
import androidx.fragment.app.FragmentTransaction
import com.example.myapplication.*
import com.example.myapplication.login.MainActivity
import com.example.myapplication.model.TEMP
import kotlinx.android.synthetic.main.gvdslop.*
import kotlinx.android.synthetic.main.gvnav.*
import kotlinx.android.synthetic.main.login1.*

class GVnavigation: AppCompatActivity() {
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
                R.id.account -> finish()
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
            val hide = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            hide.hideSoftInputFromWindow(xem.windowToken, 0)
        }
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
        currentFocus?.clearFocus()
    }
    fun closekeyboard(view: View) {
        hidekeyboard()
        currentFocus?.clearFocus()
        val hide = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        hide.hideSoftInputFromWindow(view.windowToken, 0)
    }


}


