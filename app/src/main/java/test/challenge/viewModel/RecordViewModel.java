package test.challenge.viewModel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import java.util.List;

import test.challenge.remote.Models.Record;
import test.challenge.remote.Repositories.RecordRepository;
import test.challenge.remote.Resource;

public class RecordViewModel extends ViewModel {
    private static final String TAG = "LoginViewModel";
    private RecordRepository recordRepository;

    public void init(){
        Log.d(TAG, "init: is called");
        recordRepository = RecordRepository.getInstance();
    }

    // LiveData
    public LiveData<Resource<List<Record>>> getRecordData(String resourceId, Integer limit) {
        Log.d(TAG, "getUserDataObservable: is called");
        return recordRepository.getRecordRepositoryInfo(resourceId,limit);
    }
}
