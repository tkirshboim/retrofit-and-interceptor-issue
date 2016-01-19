package com.kirshboim.depepencyissue;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Call<Void> call = SimpleService.createClient().listRepos("square");
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Response<Void> response) {
                Log.i(MainActivity.class.getSimpleName(), "Response Code: " + response.code());
            }

            @Override
            public void onFailure(Throwable t) {
                Log.e(MainActivity.class.getSimpleName(), "onFailure: " + t.getCause());
            }
        });
    }
}
