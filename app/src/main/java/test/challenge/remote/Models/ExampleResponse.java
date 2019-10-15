package test.challenge.remote.Models;

import com.google.gson.annotations.SerializedName;

public class ExampleResponse {
    @SerializedName("result")
    private Result result;

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }
}
