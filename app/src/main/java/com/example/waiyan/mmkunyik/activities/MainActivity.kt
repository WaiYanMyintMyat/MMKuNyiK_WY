package com.example.waiyan.mmkunyik.activities

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.view.GravityCompat
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import com.example.waiyan.mmkunyik.R
import com.example.waiyan.mmkunyik.adapters.JobsListAdapter
import com.example.waiyan.mmkunyik.delegates.BeforeLoginDelegate
import com.example.waiyan.mmkunyik.views.pods.BeforeLoginViewPod

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : BaseActivity() , BeforeLoginDelegate {
    override fun onTapLogin() {
        val intent= Intent(applicationContext,AccountControlActivity::class.java)
        intent.putExtra(AccountControlActivity.ACTION_TYPE,AccountControlActivity.ACTION_TYPE_LOGIN)
        startActivity(intent)
    }

    override fun onTapRegister() {
        val intent=Intent(applicationContext,AccountControlActivity::class.java)
        intent.putExtra(AccountControlActivity.ACTION_TYPE,AccountControlActivity.ACTION_TYPE_REGISTER)
        startActivity(intent)
    }

    private lateinit var mJobsListAdapter: JobsListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayShowTitleEnabled(false)//null phyit yin exception throw phyit mal//!! non null operator

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp)

        toolbar.title = resources.getString(R.string.jobposts)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

        navigationView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.menu_lastest_news -> {
                    Snackbar.make(navigationView
                            , "Tapped Lastest News", Snackbar.LENGTH_LONG).show()
                }
                R.id.menu_news_just_for_now -> {
                    Snackbar.make(navigationView
                            , "Tapped Just For You", Snackbar.LENGTH_LONG).show()
                }
                R.id.menu_favourite_news -> {
                    Snackbar.make(navigationView
                            , "Tapped Favourite", Snackbar.LENGTH_LONG).show()
                }
            }

            for (menuItemIndex in 0 until navigationView.menu.size()) {
                navigationView.menu.getItem(menuItemIndex).isChecked = false
            }
            it.isChecked = true
            drawerLayout.closeDrawer(GravityCompat.START)
            return@setNavigationItemSelectedListener true
        }

        val vpBeforeLogin = navigationView.getHeaderView(0) as BeforeLoginViewPod
        vpBeforeLogin.setDelegate(this)

        rvJobsList.layoutManager = LinearLayoutManager(applicationContext)
        mJobsListAdapter = JobsListAdapter()
        rvJobsList.adapter = mJobsListAdapter
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        if (item.itemId == android.R.id.home) {
            drawerLayout.openDrawer(GravityCompat.START)
            return true
        }

        return super.onOptionsItemSelected(item)
    }
}
