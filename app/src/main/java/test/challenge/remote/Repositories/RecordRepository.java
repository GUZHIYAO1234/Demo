package test.challenge.remote.Repositories;

import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.google.gson.stream.MalformedJsonException;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.HttpException;
import retrofit2.Response;
import test.challenge.remote.ApiService;
import test.challenge.remote.Models.ExampleResponse;
import test.challenge.remote.Models.Record;
import test.challenge.remote.Models.Result;
import test.challenge.remote.Resource;
import test.challenge.remote.RetrofitClient;

public class RecordRepository {
    private MutableLiveData<Resource<List<Record>>> result = new MutableLiveData<>();
    private static RecordRepository instance;
    private static final String TAG = "Record_Repository";

    public static RecordRepository getInstance() {
        if (instance == null) {
            instance = new RecordRepository();
        }
        return instance;
    }

    public MutableLiveData<Resource<List<Record>>> getRecordRepositoryInfo(String resourceId, int limit) {
        ApiService apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);
        Call<ExampleResponse> mobileDataUsage = apiService.getMobileDataUsage(resourceId,limit);
        mobileDataUsage.enqueue(new Callback<ExampleResponse>() {
            @Override
            public void onResponse(Call<ExampleResponse> call, Response<ExampleResponse> response) {
                //progressBar.set(View.GONE);
                if(!response.isSuccessful()){
                    result.setValue(Resource.error(response.code()+" oops, sth wrong with the remote!",new Result().getRecords()));
                    return;
                }
                else {
                    List<Record> records = response.body().getResult().getRecords();
                    for(Record record:records){
                        String content = "";
                        content+= "Id: "+ record.getId()+"\n";
                        content+= "Quarter: "+ record.getQuarter()+"\n";
                        content+= "Id: "+ record.getD_volume()+"\n\n";
                        Log.d(TAG, "onResponse: "+content);
                    }
                    Log.d(TAG, "onSuccess: ");
                    result.setValue(Resource.success(records));
                }
            }

            @Override
            public void onFailure(Call<ExampleResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
                //LoginResponseModel loginResponseModel = new LoginResponseModel(null, null);
                //loginViewModelMutableLiveData.setValue(loginResponseModel);
                result.setValue(Resource.error(getCustomErrorMessage(t),new Result().getRecords()));
            }
        });
        return result;
    }

    private String getCustomErrorMessage(Throwable error){

        if (error instanceof SocketTimeoutException) {
            return "Request Time Out";
        } else if (error instanceof MalformedJsonException) {
            return "Json Error";
        } else if (error instanceof IOException) {
            return  "IO Failure";
        } else if (error instanceof HttpException) {
            return (((HttpException) error).response().message());
        } else {
            return "Unknown Error"+error.getLocalizedMessage();
        }
    }

}
