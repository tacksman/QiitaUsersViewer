package com.tacksman.qiitausersviewer.infrastracture.api;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.tacksman.qiitausersviewer.entity.User;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class QiitaApi implements QiitaUserApi {

    private Retrofit retrofit;

    public QiitaApi() {

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpclient = new OkHttpClient.Builder()
                .addInterceptor(new RequestHeaderInterceptor())
                .addInterceptor(loggingInterceptor)
                .addNetworkInterceptor(new StethoInterceptor())
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl("http://qiita.com")
                .client(okHttpclient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    @Override
    public Observable<List<User>> getUsers(int page, int perPage) {
        return retrofit.create(QiitaUserApi.class)
                .getUsers(page, perPage)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
