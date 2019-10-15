package test.challenge;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

import test.challenge.databinding.ActivityMainBinding;
import test.challenge.remote.Models.Record;
import test.challenge.remote.Resource;
import test.challenge.remote.Status;
import test.challenge.viewModel.RecordViewModel;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private RecordViewModel recordViewModel;
    private ActivityMainBinding mainBinding;
    private final String resource_id = "a807b7ab-6cad-4aa6-87d0-e283a7353a0";
    private final int limit = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        recordViewModel = ViewModelProviders.of(this).get(RecordViewModel.class);
        mainBinding.setRecordViewModel(recordViewModel);
        mainBinding.setLifecycleOwner(this);
        recordViewModel.init();
        recordViewModel.getRecordData(resource_id,limit).observe(this, new Observer<Resource<List<Record>>>() {
            @Override
            public void onChanged(@Nullable Resource<List<Record>> listResource) {
                Log.d(TAG, "onChanged: ");
                if(listResource.status== Status.SUCCESS) {
                    mainBinding.textRecord.setText(listResource.data.get(0).getId());
                }else {
                    mainBinding.textRecord.setText(listResource.getMessage());
                }
            }
        });
    }
}
