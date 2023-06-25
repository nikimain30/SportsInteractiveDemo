package com.sportzinteractive.activity

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.LinearLayoutManager
import com.sportzinteractive.AppApplicationClass
import com.sportzinteractive.AppApplicationClass.Companion.context
import com.sportzinteractive.R
import com.sportzinteractive.adapter.TeamsDetailAdapter
import com.sportzinteractive.baseclasses.BaseActivity
import com.sportzinteractive.customviews.CustomTextView
import com.sportzinteractive.databinding.ActivityTeamsDetailBinding
import com.sportzinteractive.model.MatchDetailModelV2


class TeamsDetailActivity  : BaseActivity(),TeamsDetailAdapter.CategoryClick {

    lateinit var binding: ActivityTeamsDetailBinding
    var matchDetail : MatchDetailModelV2?= null
    lateinit var teamsDetailAdapter: TeamsDetailAdapter
    var teamA : String? = null
    var teamB : String? = null

    override fun getLayoutId(): Int  = R.layout.activity_teams_detail

    @RequiresApi(33)
    override fun init(binding: ViewDataBinding) {
        this.binding = binding as ActivityTeamsDetailBinding
        init()

    }

    private fun init() {
        matchDetail = intent.getSerializableExtra("Details") as? MatchDetailModelV2
        teamA = intent.getStringExtra("teamA")
        teamB = intent.getStringExtra("teamB")
        binding.header.imgToolbarheader.setOnClickListener {
            finish()
        }

        binding.teamOne.setText(teamA)
        binding.teamTwo.setText(teamB)

        var iter = matchDetail?.teams?.entries?.iterator()

        var teamAList = ArrayList<MatchDetailModelV2.Teams.Players>()
        var teamBList =  ArrayList<MatchDetailModelV2.Teams.Players>()
        while (iter!!.hasNext()) {
            val key = iter.next()

            var iter = key.value.players?.entries?.iterator()
            while (iter!!.hasNext()){
                val key1 = iter.next()

                var playersA = MatchDetailModelV2.Teams.Players()
                var playersB = MatchDetailModelV2.Teams.Players()
                if (key.value.NameFull.equals(teamA)) {
                    playersA.NameFull = key1.value.NameFull
                    playersA.Iscaptain = key1.value.Iscaptain
                    playersA.Iskeeper = key1.value.Iskeeper
                    playersA.Position = key1.value.Position
                    playersA.batting = key1.value.batting
                    playersA.bowling = key1.value.bowling
                    teamAList.add(playersA)

                } else if (key.value.NameFull.equals(teamB)) {
                    playersB.NameFull = key1.value.NameFull
                    playersB.Iscaptain = key1.value.Iscaptain
                    playersB.Iskeeper = key1.value.Iskeeper
                    playersB.Position = key1.value.Position
                    playersB.batting = key1.value.batting
                    playersB.bowling = key1.value.bowling
                    teamBList.add(playersB)
                }
            }
        }

        println("teamAList" +teamAList)
        println("teamBList" +teamBList)


        setAdapterTeamA(teamAList)
        setAdapterTeamB(teamBList)

        binding.cbSelectAll.setOnCheckedChangeListener { buttonview, isChecked ->
            if (isChecked) {
                binding.cbTeamB.isChecked = false
                binding.recyclerViewTwo.visibility = View.VISIBLE
                binding.teamTwo.visibility = View.VISIBLE
                binding.cbTeamA.isChecked = false
                binding.recyclerViewOne.visibility = View.VISIBLE
                binding.teamOne.visibility = View.VISIBLE
            }
        }

        binding.cbTeamA.setOnCheckedChangeListener { button, isChecked ->
            if (isChecked) {
                binding.cbSelectAll.isChecked = false
                binding.cbTeamB.isChecked = false
                binding.recyclerViewTwo.visibility = View.GONE
                binding.teamTwo.visibility = View.GONE

            } else {
                binding.recyclerViewTwo.visibility = View.VISIBLE
                binding.teamTwo.visibility = View.VISIBLE
            }
        }

        binding.cbTeamB.setOnCheckedChangeListener { button, isChecked ->
            if (isChecked) {
                binding.cbSelectAll.isChecked = false
                binding.cbTeamA.isChecked = false
                binding.recyclerViewOne.visibility = View.GONE
                binding.teamOne.visibility = View.GONE
            } else {
                binding.recyclerViewOne.visibility = View.VISIBLE
                binding.teamOne.visibility = View.VISIBLE
            }
        }

    }

    private fun setAdapterTeamA(teams: ArrayList<MatchDetailModelV2.Teams.Players>) {
        teamsDetailAdapter = TeamsDetailAdapter(this, teams, this)
        val linearLayoutManager = LinearLayoutManager(AppApplicationClass.context)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        binding.recyclerViewOne.layoutManager = linearLayoutManager
        binding.recyclerViewOne.adapter = teamsDetailAdapter
    }

    private fun setAdapterTeamB(teams: ArrayList<MatchDetailModelV2.Teams.Players>) {
        teamsDetailAdapter = TeamsDetailAdapter(this, teams, this)
        val linearLayoutManager = LinearLayoutManager(AppApplicationClass.context)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        binding.recyclerViewTwo.layoutManager = linearLayoutManager
        binding.recyclerViewTwo.adapter = teamsDetailAdapter
    }

    override fun onClick(batting: String,bowling: String) {
        val dialog = Dialog(this@TeamsDetailActivity, R.style.DialogSlideAnim)
        dialog.getWindow()?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.getWindow()
            ?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        dialog.setContentView(R.layout.dialog_layout)
        dialog.setCanceledOnTouchOutside(true)
        dialog.setCancelable(true)
        dialog.show()

        var battingView = dialog.findViewById<CustomTextView>(R.id.batting)
        var bowlingView = dialog.findViewById<CustomTextView>(R.id.bowling)

        battingView.setText("Batting Style: " +batting)
        bowlingView.setText("Bowling Style: " +bowling)



    }

}