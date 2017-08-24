package com.tacksman.qiitausersviewer.presentation.viewmodels;

import android.arch.lifecycle.ViewModel;

import com.tacksman.qiitausersviewer.entity.User;
import com.tacksman.qiitausersviewer.infrastracture.repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;

public class QiitaUserListViewModel extends ViewModel {

    int lastFetchPageIndex = 1;
    List<User> users = new ArrayList<>();
    private UserRepository userRepository;

    UserFetchSucceededListener fetchSucceededListener;
    UserFetchFailedListener fetchFailedListener;

    public QiitaUserListViewModel() {
    }

    public void init(UserFetchSucceededListener fetchSucceededListener, UserFetchFailedListener fetchFailedListener) {
        this.userRepository = new UserRepository();
        this.fetchSucceededListener = fetchSucceededListener;
        this.fetchFailedListener = fetchFailedListener;
    }

    public void fetchUsers() {
        if (lastFetchPageIndex == 100) return;

        this.lastFetchPageIndex++;
        this.userRepository.getQiitaUsers(this.lastFetchPageIndex)
                .subscribe(users -> {
                            this.users.addAll(users);
                            this.fetchSucceededListener.fetchSucceeded(users);
                        },
                        throwable -> {
                            this.lastFetchPageIndex--;
                            fetchFailedListener.fetchFailed(throwable);
                        });
    }

    public int getLastFetchPageIndex() {
        return this.lastFetchPageIndex;
    }

    public int getNextPage() {
        return this.lastFetchPageIndex + 1;
    }

    public List<User> getUsers() {
        return users;
    }

    public interface UserFetchSucceededListener {
        void fetchSucceeded(List<User> addedUsers);
    }

    public interface UserFetchFailedListener {
        void fetchFailed(Throwable throwable);
    }
}
