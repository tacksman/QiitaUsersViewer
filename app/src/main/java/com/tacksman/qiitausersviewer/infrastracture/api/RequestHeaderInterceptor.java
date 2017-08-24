package com.tacksman.qiitausersviewer.infrastracture.api;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

class RequestHeaderInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        final Request.Builder builder = chain.request().newBuilder();
        builder.addHeader("X-Client-Type", "Android");
        return chain.proceed(builder.build());
    }

}
