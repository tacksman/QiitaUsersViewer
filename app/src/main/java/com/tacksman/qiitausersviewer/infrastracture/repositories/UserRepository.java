package com.tacksman.qiitausersviewer.infrastracture.repositories;

import com.tacksman.qiitausersviewer.entity.User;
import com.tacksman.qiitausersviewer.infrastracture.api.QiitaApi;

import java.util.List;

import io.reactivex.Observable;

public class UserRepository {

    public UserRepository() {
    }

    public Observable<List<User>> getQiitaUsers(int page) {
        /**
         * perPageパラメータで1ページにつき100件のデータを請求
         */
        return new QiitaApi().getUsers(page, 100);
    }
}
