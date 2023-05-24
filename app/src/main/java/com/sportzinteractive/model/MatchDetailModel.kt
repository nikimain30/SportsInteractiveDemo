package com.sportzinteractive.model

import com.google.gson.annotations.SerializedName

data class MatchDetailModel(

    @SerializedName("Matchdetail" ) var matchdetail : Matchdetail?       = Matchdetail()
)
{
    data class Matchdetail (

        @SerializedName("Team_Name"    ) var teamName    : String?    = null,
        @SerializedName("Date_Time"    ) var dateTime    : String?    = null,
        @SerializedName("Venue"        ) var venue       : String?     = null,
        @SerializedName("Players"       ) var players      : Players?    = Players()

    )  {
        data class Players(
            @SerializedName("Player_Name"    ) var playerName    : String?    = null,
            @SerializedName("Style_batting"    ) var styleBatting    : String?    = null,
            @SerializedName("Style_bowling"        ) var styleBowling       : String?     = null
        )
    }

}
