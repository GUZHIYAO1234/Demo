package test.challenge.remote.Models;

import com.google.gson.annotations.SerializedName;

public class Record {
    @SerializedName("volume_of_mobile_data")
    private String d_volume;
    private String quarter;
    private @SerializedName("_id")
    String id;

    public String getD_volume() {
        return d_volume;
    }

    public void setD_volume(String d_volume) {
        this.d_volume = d_volume;
    }

    public String getQuarter() {
        return quarter;
    }

    public void setQuarter(String quater) {
        this.quarter = quater;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
