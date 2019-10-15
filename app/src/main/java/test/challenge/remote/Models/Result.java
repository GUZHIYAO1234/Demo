package test.challenge.remote.Models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Result {
    @SerializedName("records")
    private List<Record> records;

    public List<Record> getRecords() {
        return records;
    }

    public void setRecords(List<Record> records) {
        this.records = records;
    }
}
