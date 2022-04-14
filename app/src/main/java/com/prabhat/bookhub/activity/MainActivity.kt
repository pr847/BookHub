package com.prabhat.bookhub.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcel
import android.view.MenuItem
import android.widget.FrameLayout
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import com.prabhat.bookhub.*
import com.prabhat.bookhub.fragment.AboutAppFragment
import com.prabhat.bookhub.fragment.DashboardFragment
import com.prabhat.bookhub.fragment.FavouritesFragment
import com.prabhat.bookhub.fragment.ProfileFragment


class MainActivity() : AppCompatActivity() {
    lateinit var drawerLayout: DrawerLayout
    lateinit var coordinatorLayout: CoordinatorLayout
    lateinit var toolbar: Toolbar
    lateinit var frameLayout: FrameLayout
    lateinit var navigationView: NavigationView
    var previousMenuItem:MenuItem?=null

    constructor(parcel: Parcel) : this() {

    }

    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        drawerLayout=findViewById(R.id.drawableLayout)
        coordinatorLayout=findViewById(R.id.coordinatorLayout)
        toolbar=findViewById(R.id.toolbar)
        frameLayout=findViewById(R.id.frameLayout)
        navigationView=findViewById(R.id.navigationView)
        setUpToolbar()
        openDashboard()




        val actionBarDrawerToggle=ActionBarDrawerToggle(this@MainActivity,drawerLayout, R.string.open_drawer
        , R.string.close_drawer)
        drawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()

        navigationView.setNavigationItemSelectedListener {
            if(previousMenuItem!=null)
            {
                previousMenuItem?.isChecked=false
            }
            //these are checked for currnt menu item
            it.isCheckable=true
            it.isChecked=true
            previousMenuItem=it
            when (it.itemId)
            {
                R.id.dashboard ->{

                    openDashboard()
                    Toast.makeText(this@MainActivity,"Clicked on dashboard", Toast.LENGTH_SHORT).show()
                    supportFragmentManager.beginTransaction()
                       .replace(R.id.frameLayout,DashboardFragment())
                       .addToBackStack("Dashboard")
                       .commit()
                    supportActionBar?.title="Dashboard"
                    drawerLayout.closeDrawers()
                }
                R.id.favorites ->{
                    //Toast.makeText(this@MainActivity,"Clicked on favorites",Toast.LENGTH_SHORT).show()
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frameLayout, FavouritesFragment())
                       // .addToBackStack("Favourites")
                        .commit()

                    supportActionBar?.title="Favourites"
                    drawerLayout.closeDrawers()
                }
                R.id.profile ->{
                   // Toast.makeText(this@MainActivity,"Clicked on profile",Toast.LENGTH_SHORT).show()
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frameLayout, ProfileFragment())
                        //.addToBackStack("Profile")
                        .commit()
                    supportActionBar?.title="Profile"
                    drawerLayout.closeDrawers()
                }
                R.id.aboutApp ->{
                    //Toast.makeText(this@MainActivity,"Clicked on aboutapp",Toast.LENGTH_SHORT).show()
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frameLayout, AboutAppFragment())
                       // .addToBackStack("AboutApp")
                        .commit()
                    supportActionBar?.title="AboutApp"
                    drawerLayout.closeDrawers()
                }

            }

            return@setNavigationItemSelectedListener true }


    }
    fun setUpToolbar(){
        setSupportActionBar(toolbar)
        supportActionBar?.title="Toolbar Title"
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        val id=item.itemId
        if(id==android.R.id.home)
        {
            drawerLayout.openDrawer(GravityCompat.START)
        }
        return super.onOptionsItemSelected(item)
    }
    private fun openDashboard(){
        val fragment= DashboardFragment()
        val transaction=supportFragmentManager.beginTransaction()
                transaction.replace(R.id.frameLayout,fragment)
           transaction.commit()
        supportActionBar?.title="Dashboard"
        navigationView.setCheckedItem(R.id.dashboard)

    }

    override fun onBackPressed() {
        when(supportFragmentManager.findFragmentById(R.id.frameLayout))
        {
            !is DashboardFragment ->openDashboard()
            else->super.onBackPressed()
        }


    }


}