package com.tacksman.qiitausersviewer.infrastracture.api;

import com.tacksman.qiitausersviewer.entity.User;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface QiitaUserApi {

    @GET("/api/v2/users")
    Observable<List<User>> getUsers(@Query("page") int page, @Query("per_page") int perPage);

}
