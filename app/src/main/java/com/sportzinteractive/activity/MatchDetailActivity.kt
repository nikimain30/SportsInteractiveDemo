package com.sportzinteractive.activity

import android.content.Intent
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.sportzinteractive.AppApplicationClass
import com.sportzinteractive.R
import com.sportzinteractive.adapter.MatchDetailAdapter
import com.sportzinteractive.baseclasses.BaseActivity
import com.sportzinteractive.databinding.ActivityMatchDetailsBinding
import com.sportzinteractive.model.MatchDetailModel
import com.sportzinteractive.model.MatchDetailModelV2
import com.sportzinteractive.networking.ApiStatus
import com.sportzinteractive.networking.AppAPI
import com.sportzinteractive.utils.showToastMessage
import com.sportzinteractive.viewmodel.MatchDetailViewModel
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class MatchDetailActivity : BaseActivity(), MatchDetailAdapter.CategoryClick {

    lateinit var binding: ActivityMatchDetailsBinding
    lateinit var viewModel: MatchDetailViewModel
    lateinit var matchDetailAdapter: MatchDetailAdapter
    var arraylist = ArrayList<MatchDetailModelV2>()

    override fun getLayoutId(): Int = R.layout.activity_match_details

    override fun init(binding: ViewDataBinding) {
        this.binding = binding as ActivityMatchDetailsBinding

        setUpViewModel()
        setData()
    }

    private fun setData() {
        viewModel.getMatchDetail(
            AppAPI.API_BASE_URL + "nzin01312019187360.json"
        ).observe(this, {
            when (it.status) {
                ApiStatus.SUCCESS -> {
                    hideLoading()
                    var response = it.data!!
                    setAdapter(response)
                }
                ApiStatus.ERROR -> {
                    hideLoading()
                    it?.message?.let { it1 -> showToastMessage(it1) }
                }
            }
        })
    }

    private fun setAdapter(list : MatchDetailModelV2) {
        matchDetailAdapter = MatchDetailAdapter(this@MatchDetailActivity, list, this)
        val linearLayoutManager = LinearLayoutManager(AppApplicationClass.context)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        binding.rvMatchDetail.layoutManager = linearLayoutManager
        binding.rvMatchDetail.adapter = matchDetailAdapter
    }

    private fun setUpViewModel() {
        viewModel =
            ViewModelProvider(this).get(MatchDetailViewModel::class.java)
        binding.lifecycleOwner = this
    }

    override fun onClick(model: MatchDetailModelV2, teamA : String,teamB : String, position: Int) {
        val intent = Intent(this, TeamsDetailActivity::class.java)
        intent.putExtra("Details", model)
        intent.putExtra("teamA", teamA)
        intent.putExtra("teamB", teamB)
        startActivity(intent)
    }
}