package com.example.foursquare.Home

import android.content.Intent
import android.location.Location
import android.location.LocationListener
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.FragmentPagerAdapter
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager
import com.example.foursquare.*
import com.example.foursquare.Home.Adapter.HomeAdapter
import com.example.foursquare.search.Search_homeActivity

import com.example.foursquare.viewmodel.PlaceViewModel
import com.google.android.material.navigation.NavigationView
import com.google.android.material.tabs.TabItem
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_details_screen.*
import kotlinx.android.synthetic.main.content_main.*

class HomeActivity : AppCompatActivity() {
    private lateinit var placeViewModel : PlaceViewModel

    lateinit var tabLayout: TabLayout
    lateinit var viewPager: ViewPager

    lateinit var drawerLayout: DrawerLayout
    lateinit var toggle: ActionBarDrawerToggle
    lateinit var navigationView: NavigationView

    lateinit var nearyou: TabItem
    lateinit var toppick: TabItem
    lateinit var popular: TabItem
    lateinit var lunch: TabItem
    lateinit var coffee: TabItem

    lateinit var adapter: HomeAdapter

    private lateinit var listener : LocationListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        placeViewModel = ViewModelProvider(this).get(PlaceViewModel::class.java)
        //getUserLocation()
        //supportActionBar?.hide()

        tabLayout=findViewById(R.id.tabLayout)
        viewPager=findViewById(R.id.view_pager)
//        nearyou=findViewById(R.id.nearyou)
//        toppick=findViewById(R.id.toppick)
//        popular=findViewById(R.id.popular)
//        lunch=findViewById(R.id.lunch)
//        coffee=findViewById(R.id.coffee)

        drawerLayout = findViewById(R.id.drawer)
        navigationView = findViewById(R.id.nav_view)

        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        toggle = ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close)

        drawerLayout.addDrawerListener(toggle)
        toggle.setDrawerIndicatorEnabled(true)
        toggle.syncState()

//        tabLayout.addTab(tabLayout.newTab().setText("Near you"))
//        tabLayout.addTab(tabLayout.newTab().setText("Toppick"))
//        tabLayout.addTab(tabLayout.newTab().setText("Popular"))
//        tabLayout.addTab(tabLayout.newTab().setText("Lunch"))
//        tabLayout.addTab(tabLayout.newTab().setText("Coffee"))
//        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL)

//        val adapter= HomeAdapter(this,supportFragmentManager,tabLayout.tabCount)
//        viewPager.adapter=adapter

        viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPager.currentItem = tab.position
            }
            override fun onTabUnselected(tab: TabLayout.Tab) {

            }
            override fun onTabReselected(tab: TabLayout.Tab) {

            }
        })

        adapter = HomeAdapter(this,getSupportFragmentManager(),FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT,tabLayout.tabCount)
        viewPager.adapter = adapter

        navigationView.setNavigationItemSelectedListener { menuItem ->
            val id = menuItem.itemId
            if (id == R.id.nav_fav) {
                startActivity(Intent(this, FavouritesActivity::class.java))
            } else if (id == R.id.nav_feedback) {
                startActivity(Intent(this, FeedbackActivity::class.java))
            }else if (id == R.id.nav_aboutus) {
                startActivity(Intent(this, AboutusActivity::class.java))
            }else if (id == R.id.nav_logout){
                Toast.makeText(this,"logout",Toast.LENGTH_SHORT).show()
            }
            drawerLayout.closeDrawer(GravityCompat.START)
            true
        }

        filter_icon.setOnClickListener {
            val intent = Intent(this,FilterActivity::class.java)
            startActivity(intent)
        }
        search_icon.setOnClickListener {
            val intent = Intent(this,Search_homeActivity::class.java)
            startActivity(intent)
        }

    }
    
}