package com.example.myapplication.quanly

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import kotlinx.android.synthetic.main.qlnav.*
import androidx.fragment.app.Fragment
import com.example.myapplication.*
import com.example.myapplication.login.MainActivity
import com.example.myapplication.quanly.quanlygv.GVdsgv
import com.example.myapplication.quanly.quanlygv.GVqldl
import com.example.myapplication.quanly.quanlygv.GVqltk
import com.example.myapplication.quanly.quanlysv.SVdssv
import com.example.myapplication.quanly.quanlysv.SVqldl
import com.example.myapplication.quanly.quanlysv.SVqltk

class QLnavigation : AppCompatActivity() {
    lateinit var toogle : ActionBarDrawerToggle
    private lateinit var drawerLayout: DrawerLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.qlnav)
        hidekeyboard()
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
                R.id.GVqltk -> replaceFragment(GVqltk(),it.title.toString())
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
    }
}
