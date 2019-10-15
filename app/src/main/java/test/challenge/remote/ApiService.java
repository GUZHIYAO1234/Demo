package test.challenge.remote;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import test.challenge.remote.Models.ExampleResponse;

public interface ApiService {
    @GET("api/action/datastore_search")
    Call<ExampleResponse> getMobileDataUsage(@Query("resource_id") String resource_id, @Query("limit") int limit);
}
