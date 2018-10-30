package com.yveschiong.personalrecordbook.common.extensions

import android.content.Intent
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.yveschiong.personalrecordbook.common.BaseActivity

fun AppCompatActivity.replaceFragment(containerId: Int, fragment: Fragment) {
    supportFragmentManager.inTransaction { replace(containerId, fragment) }
}

fun AppCompatActivity.launchActivityForResult(clazz: Class<out BaseActivity>, requestCode: Int) {
    startActivityForResult(Intent(this, clazz), requestCode)
}