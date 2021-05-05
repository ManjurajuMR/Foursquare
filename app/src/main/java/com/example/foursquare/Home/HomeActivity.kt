package com.example.foursquare.Home

import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.example.foursquare.Home.Adapter.HomeAdapter
import com.example.foursquare.R
import com.google.android.material.navigation.NavigationView
import com.google.android.material.tabs.TabItem
import com.google.android.material.tabs.TabLayout

class HomeActivity : AppCompatActivity() {
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

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

    }
}