package dev.gilmario.task.view

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.SystemClock
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.view.MenuItem
import android.support.v4.widget.DrawerLayout
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.util.Log
import dev.gilmario.task.R
import dev.gilmario.task.business.PriorityBusiness
import dev.gilmario.task.business.TaskBusiness
import dev.gilmario.task.business.UserBusiness
import dev.gilmario.task.constants.TaskConstants
import dev.gilmario.task.model.Priority
import dev.gilmario.task.model.PriorityCacheConstants
import dev.gilmario.task.model.User
import dev.gilmario.task.receivers.AlarmReceiver
import dev.gilmario.task.util.SecurityPreferences

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var mSecurityPreferences : SecurityPreferences
    private lateinit var mPriorityBusiness: PriorityBusiness
    private lateinit var userBusiness : UserBusiness
    private lateinit var usuarios : List<User>
    private lateinit var prioridades : List<Priority>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        mSecurityPreferences = SecurityPreferences(this)
        mPriorityBusiness = PriorityBusiness(this)
        userBusiness = UserBusiness(this)





      //  userBusiness.deleteAll();

        usuarios = userBusiness.getListUsers()

        Log.d("myTag", "usuario"+usuarios.get(0));

        prioridades  = mPriorityBusiness.getList()
        if(prioridades.size == 0) {
            mPriorityBusiness.insert(1, "Baixa")
            mPriorityBusiness.insert(2, "Media")
            mPriorityBusiness.insert(3, "Alta")
        }

        loadPriorityCache();
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        startFragmentDefault();
        navView.setNavigationItemSelectedListener(this)

        scheduleNotificationService();
    }


    fun scheduleNotificationService(){
        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intentAlarm = Intent(this, AlarmReceiver::class.java)
        val alarmIntent = PendingIntent.getBroadcast(this, 0, intentAlarm, 0)
        alarmManager.setRepeating(AlarmManager.ELAPSED_REALTIME, SystemClock.elapsedRealtime(), 1000 * 10, alarmIntent)
    }

    private fun startFragmentDefault() {
        val mfragment :Fragment = TaskListFragment.newInstance(TaskConstants.TASKFILTER.COMPLETE);
        supportFragmentManager.beginTransaction().replace(R.id.frameContent, mfragment).commit()
    }

    override fun onBackPressed() {
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.

         var mfragment :Fragment = TaskListFragment.newInstance(TaskConstants.TASKFILTER.COMPLETE)

        when (item.itemId) {
            R.id.nav_done -> {
                mfragment = TaskListFragment.newInstance(TaskConstants.TASKFILTER.COMPLETE)
            }
            R.id.nav_todo -> {
                mfragment = TaskListFragment.newInstance(TaskConstants.TASKFILTER.TOO)
            }
            R.id.nav_view -> {
                mfragment = TaskListFragment.newInstance(TaskConstants.TASKFILTER.NEW)
            }
            R.id.nav_logout -> {
                logout()
            }

        }
        val  framentManager = supportFragmentManager
        framentManager.beginTransaction().replace(R.id.frameContent, mfragment).commit()

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    private fun loadPriorityCache () {
        PriorityCacheConstants.setCache(mPriorityBusiness.getList())
    }

    fun logout() {
        mSecurityPreferences.removeStoredString(TaskConstants.KEY.USER_ID)
        mSecurityPreferences.removeStoredString(TaskConstants.KEY.USER_NAME)
        mSecurityPreferences.removeStoredString(TaskConstants.KEY.USER_EMAIL)
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }

}
