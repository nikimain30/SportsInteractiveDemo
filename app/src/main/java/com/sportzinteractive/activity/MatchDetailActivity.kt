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
import com.sportzinteractive.networking.ApiStatus
import com.sportzinteractive.networking.AppAPI
import com.sportzinteractive.utils.showToastMessage
import com.sportzinteractive.viewmodel.MatchDetailViewModel
import org.json.JSONObject
import java.lang.String.join

class MatchDetailActivity : BaseActivity(), MatchDetailAdapter.CategoryClick {

    lateinit var binding: ActivityMatchDetailsBinding
    lateinit var viewModel: MatchDetailViewModel
    lateinit var matchDetailAdapter: MatchDetailAdapter
    var arraylist = ArrayList<MatchDetailModel>()

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
                    var response = it.data!!.string()
                    var matchDetailOne = JSONObject(response)

                    setData1(matchDetailOne)

                }
                ApiStatus.ERROR -> {
                    hideLoading()
                    it?.message?.let { it1 -> showToastMessage(it1) }
                }
            }
        })
    }

    private fun setData1(matchDetailOne: JSONObject) {
        viewModel.getMatchDetail(
            AppAPI.API_BASE_URL + "sapk01222019186652.json"
        ).observe(this, {
            when (it.status) {
                ApiStatus.SUCCESS -> {
                    hideLoading()

                    var response = it.data!!.string()
                    var jsonObject = JSONObject(response)

                    var matchDetailTwo = jsonObject

                    if (matchDetailOne.has("Matchdetail")) {
                        var date = matchDetailOne.getJSONObject("Matchdetail").getJSONObject("Match").getString("Date")
                        var time = matchDetailOne.getJSONObject("Matchdetail").getJSONObject("Match").getString("Time")
                        var offset = matchDetailOne.getJSONObject("Matchdetail").getJSONObject("Match").getString("Offset")
                        var venue = matchDetailOne.getJSONObject("Matchdetail").getJSONObject("Venue").getString("Name")


                       /* val formatter: DateFormat = SimpleDateFormat("M/dd/yyyy HH:mm:ssZ")
                        formatter.setTimeZone(TimeZone.getTimeZone("Asia/Kolkata")) // Or whatever IST is supposed to be

                        var datetime =  formatter.format(formatter.parse(date+ " " + time +offset))

                        println("dateTime " +datetime)*/

                        var set: HashSet<String> = HashSet()
                        var list = MatchDetailModel()
                        if (matchDetailOne.has("Teams")) {
                            var teamDetail = matchDetailOne.getJSONObject("Teams")

                            val list = teamDetail.keys()
                            while (list.hasNext()) {
                                var key = list.next().toString()
                                var teams_name = teamDetail.getJSONObject(key).getString("Name_Full")
                                var player = teamDetail.getJSONObject(key).getJSONObject("Players")
                                set.add(teams_name)


                                println("Out " + teams_name + player)
                            }

                        }
                        var strNames: String = join(",", set)
                        list.matchdetail?.dateTime = date + " " +time + "pm"
                        list.matchdetail?.venue = venue
                        list.matchdetail?.teamName = strNames.replace(",", " vs ")

                        arraylist.add(list)

                    }


                    if (matchDetailTwo.has("Matchdetail")) {
                        var date = matchDetailTwo.getJSONObject("Matchdetail").getJSONObject("Match").getString("Date")
                        var time = matchDetailTwo.getJSONObject("Matchdetail").getJSONObject("Match").getString("Time")
                        var offset = matchDetailTwo.getJSONObject("Matchdetail").getJSONObject("Match").getString("Offset")
                        var venue = matchDetailTwo.getJSONObject("Matchdetail").getJSONObject("Venue").getString("Name")


                        var set: HashSet<String> = HashSet()
                        var list = MatchDetailModel()
                        if (matchDetailTwo.has("Teams")) {
                            var teamDetail = matchDetailTwo.getJSONObject("Teams")

                            val list = teamDetail.keys()
                            while (list.hasNext()) {
                                var key = list.next().toString()
                                var teams_name = teamDetail.getJSONObject(key).getString("Name_Full")
                                var player = teamDetail.getJSONObject(key).getJSONObject("Players")
                                set.add(teams_name)

                            }

                        }
                        var strNames: String = join(",", set)
                        list.matchdetail?.dateTime = date + " " +time + "pm"
                        list.matchdetail?.venue = venue
                        list.matchdetail?.teamName = strNames.replace(",", " vs ")

                        arraylist.add(list)
                    }

                    setAdapter(arraylist)


                }
                ApiStatus.ERROR -> {
                    hideLoading()
                    it?.message?.let { it1 -> showToastMessage(it1) }
                }
            }
        })
    }

    private fun setAdapter(list : ArrayList<MatchDetailModel>) {
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

    override fun onClick(model: MatchDetailModel, position: Int) {
        val intent = Intent(this, TeamsDetailActivity::class.java)
        intent.putExtra("Details", model.toString())
        startActivity(intent)
    }
}