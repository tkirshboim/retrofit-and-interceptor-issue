package com.kirshboim.depepencyissue;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.Path;

public final class SimpleService {

    public static final String API_URL = "https://api.github.com";

    public interface GitHub {
        @GET("users/{user}/repos")
        Call<Void> listRepos(@Path("user") String user);
    }

    public static GitHub createClient() {
        OkHttpClient.Builder client = new OkHttpClient().newBuilder();
        client.addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC));

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                .client(client.build())
                .build();

        return retrofit.create(GitHub.class);
    }
}