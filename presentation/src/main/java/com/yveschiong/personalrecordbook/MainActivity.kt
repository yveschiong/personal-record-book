package com.yveschiong.personalrecordbook

import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.NavigationView
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.view.Menu
import android.view.MenuItem
import com.yveschiong.personalrecordbook.common.BaseActivity
import com.yveschiong.personalrecordbook.common.extensions.replaceFragment
import com.yveschiong.personalrecordbook.ui.people.PeopleFragment
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import javax.inject.Inject

class MainActivity : BaseActivity(), HasSupportFragmentInjector {

    @Inject
    lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        setupFab(fab)

        // Set to the people navigation initially
        setNavigation(R.id.nav_people)

        setupNavDrawerToggle(
            drawer_layout, ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
            )
        )

        setupNavDrawerItemSelectedListener(nav_view)
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }

    private fun setupFab(fab: FloatingActionButton) {
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }

    private fun setupNavDrawerToggle(drawer: DrawerLayout, toggle: ActionBarDrawerToggle) {
        drawer.addDrawerListener(toggle)
        toggle.syncState()
    }

    private fun setupNavDrawerItemSelectedListener(nav: NavigationView) {
        nav.setNavigationItemSelectedListener {
            setNavigation(it.itemId)

            drawer_layout.closeDrawer(GravityCompat.START)
            true
        }
    }

    private fun setNavigation(id: Int) {
        when (id) {
            R.id.nav_people -> {
                replaceFragment(R.id.fragment, PeopleFragment.newInstance())
            }
            else -> return
        }

        nav_view.setCheckedItem(id)
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment>? {
        return fragmentDispatchingAndroidInjector
    }
}
