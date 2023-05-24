package com.sportzinteractive.activity

import androidx.databinding.ViewDataBinding
import com.sportzinteractive.R
import com.sportzinteractive.baseclasses.BaseActivity

import com.sportzinteractive.databinding.ActivityTeamsDetailBinding

class TeamsDetailActivity  : BaseActivity() {

    lateinit var binding: ActivityTeamsDetailBinding

    override fun getLayoutId(): Int  = R.layout.activity_teams_detail

    override fun init(binding: ViewDataBinding) {
        this.binding = binding as ActivityTeamsDetailBinding

    }
}