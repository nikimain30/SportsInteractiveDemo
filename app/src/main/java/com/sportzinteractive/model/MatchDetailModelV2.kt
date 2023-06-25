package com.sportzinteractive.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.io.Serializable

data class MatchDetailModelV2(
    @SerializedName("Matchdetail" ) var matchdetail : Matchdetail?       = Matchdetail(),
    @SerializedName("Nuggets"     ) var Nuggets     : ArrayList<String>  = arrayListOf(),
    @SerializedName("Innings"     ) var innings     : ArrayList<Innings> = arrayListOf(),
    @SerializedName("Teams"       ) var teams       : Map<String, Teams>?             = HashMap(),
    @SerializedName("Notes"       ) var notes       : Map<String, ArrayList<String>>?             = HashMap()
)  : Serializable
{
    data class Matchdetail (

        @SerializedName("Team_Home"    ) var TeamHome    : String?    = null,
        @SerializedName("Team_Away"    ) var TeamAway    : String?    = null,
        @SerializedName("Match"        ) var match       : Match?     = Match(),
        @SerializedName("Series"       ) var series      : Series?    = Series(),
        @SerializedName("Venue"        ) var venue       : Venue?     = Venue(),
        @SerializedName("Officials"    ) var officials   : Officials? = Officials(),
        @SerializedName("Weather"      ) var Weather     : String?    = null,
        @SerializedName("Tosswonby"    ) var Tosswonby   : String?    = null,
        @SerializedName("Status"       ) var Status      : String?    = null,
        @SerializedName("Status_Id"    ) var StatusId    : String?    = null,
        @SerializedName("Player_Match" ) var PlayerMatch : String?    = null,
        @SerializedName("Result"       ) var Result      : String?    = null,
        @SerializedName("Winningteam"  ) var Winningteam : String?    = null,
        @SerializedName("Winmargin"    ) var Winmargin   : String?    = null,
        @SerializedName("Equation"     ) var Equation    : String?    = null

    ) : Serializable

    {
        data class Match (

            @SerializedName("Livecoverage" ) var Livecoverage : String? = null,
            @SerializedName("Id"           ) var Id           : String? = null,
            @SerializedName("Code"         ) var Code         : String? = null,
            @SerializedName("League"       ) var League       : String? = null,
            @SerializedName("Number"       ) var Number       : String? = null,
            @SerializedName("Type"         ) var Type         : String? = null,
            @SerializedName("Date"         ) var Date         : String? = null,
            @SerializedName("Time"         ) var Time         : String? = null,
            @SerializedName("Offset"       ) var Offset       : String? = null,
            @SerializedName("Daynight"     ) var Daynight     : String? = null

        ) : Serializable
        data class Series (
            @SerializedName("Id"        ) var Id       : String? = null,
            @SerializedName("Name"      ) var Name     : String? = null,
            @SerializedName("Status"    ) var Status   : String? = null,
            @SerializedName("Tour"      ) var Tour     : String? = null,
            @SerializedName("Tour_Name" ) var TourName : String? = null
        ): Serializable

        data class Venue (

            @SerializedName("Id"   ) var Id   : String? = null,
            @SerializedName("Name" ) var Name : String? = null

        ): Serializable

        data class Officials (

            @SerializedName("Umpires" ) var Umpires : String? = null,
            @SerializedName("Referee" ) var Referee : String? = null

        ) : Serializable
    }

    data class Innings (

        @SerializedName("Number"              ) var Number             : String?                  = null,
        @SerializedName("Battingteam"         ) var Battingteam        : String?                  = null,
        @SerializedName("Total"               ) var Total              : String?                  = null,
        @SerializedName("Wickets"             ) var Wickets            : String?                  = null,
        @SerializedName("Overs"               ) var Overs              : String?                  = null,
        @SerializedName("Runrate"             ) var Runrate            : String?                  = null,
        @SerializedName("Byes"                ) var Byes               : String?                  = null,
        @SerializedName("Legbyes"             ) var Legbyes            : String?                  = null,
        @SerializedName("Wides"               ) var Wides              : String?                  = null,
        @SerializedName("Noballs"             ) var Noballs            : String?                  = null,
        @SerializedName("Penalty"             ) var Penalty            : String?                  = null,
        @SerializedName("AllottedOvers"       ) var AllottedOvers      : String?                  = null,
        @SerializedName("Batsmen"             ) var batsmen            : ArrayList<Batsmen>       = arrayListOf(),
        @SerializedName("Partnership_Current" ) var partnershipCurrent : PartnershipCurrent?      = PartnershipCurrent(),
        @SerializedName("Bowlers"             ) var bowlers            : ArrayList<Bowlers>       = arrayListOf(),
        @SerializedName("FallofWickets"       ) var fallofWickets      : ArrayList<FallofWickets> = arrayListOf(),
        @SerializedName("PowerPlay"           ) var powerPlay          : PowerPlay?               = PowerPlay()

    ): Serializable
    {
        data class Batsmen (

            @SerializedName("Batsman"    ) var Batsman    : String? = null,
            @SerializedName("Runs"       ) var Runs       : String? = null,
            @SerializedName("Balls"      ) var Balls      : String? = null,
            @SerializedName("Fours"      ) var Fours      : String? = null,
            @SerializedName("Sixes"      ) var Sixes      : String? = null,
            @SerializedName("Dots"       ) var Dots       : String? = null,
            @SerializedName("Strikerate" ) var Strikerate : String? = null,
            @SerializedName("Dismissal"  ) var Dismissal  : String? = null,
            @SerializedName("Howout"     ) var Howout     : String? = null,
            @SerializedName("Bowler"     ) var Bowler     : String? = null,
            @SerializedName("Fielder"    ) var Fielder    : String? = null

        ): Serializable

        data class PartnershipCurrent (

            @SerializedName("Runs"    ) var Runs    : String?            = null,
            @SerializedName("Balls"   ) var Balls   : String?            = null,
            @SerializedName("Batsmen" ) var Batsmen : ArrayList<Batsmen> = arrayListOf()

        ): Serializable
        data class Bowlers (

            @SerializedName("Bowler"      ) var Bowler      : String? = null,
            @SerializedName("Overs"       ) var Overs       : String? = null,
            @SerializedName("Maidens"     ) var Maidens     : String? = null,
            @SerializedName("Runs"        ) var Runs        : String? = null,
            @SerializedName("Wickets"     ) var Wickets     : String? = null,
            @SerializedName("Economyrate" ) var Economyrate : String? = null,
            @SerializedName("Noballs"     ) var Noballs     : String? = null,
            @SerializedName("Wides"       ) var Wides       : String? = null,
            @SerializedName("Dots"        ) var Dots        : String? = null

        ): Serializable

        data class FallofWickets (

            @SerializedName("Batsman" ) var Batsman : String? = null,
            @SerializedName("Score"   ) var Score   : String? = null,
            @SerializedName("Overs"   ) var Overs   : String? = null

        ): Serializable
        data class PowerPlay (

            @SerializedName("PP1" ) var PP1 : String? = null,
            @SerializedName("PP2" ) var PP2 : String? = null

        ): Serializable
    }
    data class Teams(
        @SerializedName("Name_Full"  ) var NameFull  : String?  = null,
        @SerializedName("Name_Short" ) var NameShort : String?  = null,
        @SerializedName("Players"    ) var players   : Map<String, Players>? = HashMap()
    ) : Serializable
    {
        data class Players (

            @SerializedName("Position"  ) var Position : String?  = null,
            @SerializedName("Name_Full" ) var NameFull : String?  = null,
            @SerializedName("Iscaptain" ) var Iscaptain : String?  = null,
            @SerializedName("Iskeeper" ) var Iskeeper : String?  = null,
            @SerializedName("Batting"   ) var batting  : Batting? = Batting(),
            @SerializedName("Bowling"   ) var bowling  : Bowling? = Bowling()
        ): Serializable {
            data class Batting (
                @SerializedName("Style"      ) var Style      : String? = null,
                @SerializedName("Average"    ) var Average    : String? = null,
                @SerializedName("Strikerate" ) var Strikerate : String? = null,
                @SerializedName("Runs"       ) var Runs       : String? = null
                    ): Serializable
            data class Bowling(
                @SerializedName("Style"       ) var Style       : String? = null,
                @SerializedName("Average"     ) var Average     : String? = null,
                @SerializedName("Economyrate" ) var Economyrate : String? = null,
                @SerializedName("Wickets"     ) var Wickets     : String? = null

            ): Serializable

        }
    }
}
