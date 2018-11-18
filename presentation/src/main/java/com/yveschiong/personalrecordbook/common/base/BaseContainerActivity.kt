package com.yveschiong.personalrecordbook.common.base

import com.yveschiong.personalrecordbook.common.utils.view.Refreshable

abstract class BaseContainerActivity : BaseActivity() {

    abstract fun getContainerId(): Int

    override fun onResume() {
        super.onResume()

        val frag = supportFragmentManager.findFragmentById(getContainerId()) as? Refreshable ?: return
        frag.refresh()
    }
}
