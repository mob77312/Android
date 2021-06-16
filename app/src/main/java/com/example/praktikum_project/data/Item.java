package com.example.praktikum_project.data;

import com.google.gson.annotations.SerializedName;

// membuat kelas model untuk menampung data data movie
public class Item{
    // SerializedName digunakan untuk menyamakan nama variabel di API dengan yang dibuat
    @SerializedName("idLeague")
    private String idLeague;

    @SerializedName("strSport")
    private String strSport;

    @SerializedName("strLeague")
    private String strLeague;

    @SerializedName("strCurrentSeason")
    private String strCurrentSeason;

    @SerializedName("strWebsite")
    private String strWebsite;

    @SerializedName("strDescriptionEN")
    private String strDescriptionEN;

    @SerializedName("strBadge")
    private String strBadge;

    @SerializedName("strNaming")
    private String strNaming;

    public Item(String idLeague, String strSport, String strLeague, String strCurrentSeason, String strWebsite, String strDescriptionEN, String strBadge,String strNaming) {
        this.idLeague = idLeague;
        this.strSport = strSport;
        this.strLeague = strLeague;
        this.strCurrentSeason = strCurrentSeason;
        this.strWebsite = strWebsite;
        this.strDescriptionEN = strDescriptionEN;
        this.strBadge= strBadge;
        this.strNaming= strNaming;
    }

    public String getIdLeague() {
        return idLeague;
    }

    public String getStrSport() {
        return strSport;
    }

    public String getStrLeague() {
        return strLeague;
    }

    public String getStrCurrentSeason() {
        return strCurrentSeason;
    }

    public String getStrWebsite() {
        return strWebsite;
    }

    public String getStrDescriptionEN() {
        return strDescriptionEN;
    }

    public String getStrBadge() {
        return strBadge;
    }
    public String getStrNaming() {
        return strNaming;
    }
}

