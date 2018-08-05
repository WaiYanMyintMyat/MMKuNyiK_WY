package com.example.waiyan.mmkunyik.activities

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.view.GravityCompat
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.example.waiyan.mmkunyik.R
import com.example.waiyan.mmkunyik.adapters.JobsListAdapter
import com.example.waiyan.mmkunyik.components.SmartScrollListener
import com.example.waiyan.mmkunyik.delegates.BeforeLoginDelegate
import com.example.waiyan.mmkunyik.delegates.JobsDelegate
import com.example.waiyan.mmkunyik.events.ApiErrorEvent
import com.example.waiyan.mmkunyik.events.ForceGetJobEvent
import com.example.waiyan.mmkunyik.events.SuccessGetJobEvent
import com.example.waiyan.mmkunyik.utils.AppConstants
import com.example.waiyan.mmkunyik.views.pods.BeforeLoginViewPod
import com.padc.mmkunyi.data.models.JobModel
import com.padc.mmkunyi.data.vos.JobVO

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class MainActivity : BaseActivity(), BeforeLoginDelegate , JobsDelegate{
    override fun onTapJob(jobVO: JobVO) {
        val intent = Intent(applicationContext, JobDetailActivity::class.java)
        intent.putExtra(AppConstants.JOB_ID, jobVO.jobPostId)
        startActivity(intent)
    }

    override fun onTapApply(jobVO: JobVO) {

    }

    override fun onTapLogin() {
        val intent = Intent(applicationContext, AccountControlActivity::class.java)
        intent.putExtra(AccountControlActivity.ACTION_TYPE, AccountControlActivity.ACTION_TYPE_LOGIN)
        startActivity(intent)
    }

    override fun onTapRegister() {
        val intent = Intent(applicationContext, AccountControlActivity::class.java)
        intent.putExtra(AccountControlActivity.ACTION_TYPE, AccountControlActivity.ACTION_TYPE_REGISTER)
        startActivity(intent)
    }

    private lateinit var mJobsListAdapter: JobsListAdapter
    private var snackBar: Snackbar? = null
    private var mSmartScrollListener: SmartScrollListener? = null

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
                            , "Tapped Job Lists", Snackbar.LENGTH_LONG).show()
                }
                R.id.menu_news_just_for_now -> {
                    Snackbar.make(navigationView
                            , "Tapped Job For You", Snackbar.LENGTH_LONG).show()
                }
                R.id.menu_favourite_news -> {
                    Snackbar.make(navigationView
                            , "Tapped Populated Jobs", Snackbar.LENGTH_LONG).show()
                }
            }

            for (menuItemIndex in 0 until navigationView.menu.size()) {
                navigationView.menu.getItem(menuItemIndex).isChecked = false
            }
            it.isChecked = true
            drawerLayout.closeDrawer(GravityCompat.START)
            return@setNavigationItemSelectedListener true
        }

        swipeRefreshLayout.isRefreshing = true
        JobModel.getObjectInstance()!!.loadJobList()

        val vpBeforeLogin = navigationView.getHeaderView(0) as BeforeLoginViewPod
        vpBeforeLogin.setDelegate(this)

        rvJobsList.layoutManager = LinearLayoutManager(applicationContext)
        mJobsListAdapter = JobsListAdapter(this)
        rvJobsList.adapter = mJobsListAdapter

        swipeRefreshLayout.setOnRefreshListener {
            JobModel.getObjectInstance()!!.loadJobListForceRefresh()
        }

        mSmartScrollListener = SmartScrollListener(object : SmartScrollListener.OnSmartScrollListener {
            override fun onListEndReach() {
                Snackbar.make(rvJobsList, "Loading more data.", Snackbar.LENGTH_LONG).show()
                //swipeRefresh.isRefreshing = true
                //MMHealthModel.getInstanceObject()!!.loadHealthCareInfo()
            }
        })
        rvJobsList.addOnScrollListener(mSmartScrollListener)
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

    override fun onStart() {
        super.onStart()
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this)
        }
    }

    override fun onStop() {
        super.onStop()
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this)
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onSuccessGetJobEvent(successEvent: SuccessGetJobEvent) {
        swipeRefreshLayout.isRefreshing = false
        mJobsListAdapter.appendJob(successEvent.jobList)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onSuccessGetJobEventForceRefresh(successForceGetJobEvent: ForceGetJobEvent){
        swipeRefreshLayout.isRefreshing = false
        vpEmptyNews.visibility = View.GONE
        mJobsListAdapter.setJob(successForceGetJobEvent.jobList)
        if(snackBar!=null){
            snackBar!!.dismiss()
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onFailureApiEvent(errorEvent: ApiErrorEvent) {
        swipeRefreshLayout.isRefreshing = false

        if (mJobsListAdapter.itemCount <= 0) {
            vpEmptyNews.visibility = View.VISIBLE
            snackBar = Snackbar.make(rvJobsList,errorEvent.errorMessage,Snackbar.LENGTH_INDEFINITE)
            snackBar!!.show()
        }else{
            Toast.makeText(this,"No Internet Connection",Toast.LENGTH_SHORT).show()
        }
    }

}
