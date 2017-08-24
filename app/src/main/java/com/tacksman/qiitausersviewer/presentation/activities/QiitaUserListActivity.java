package com.tacksman.qiitausersviewer.presentation.activities;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.facebook.stetho.common.LogUtil;
import com.tacksman.qiitausersviewer.R;
import com.tacksman.qiitausersviewer.entity.User;
import com.tacksman.qiitausersviewer.presentation.viewmodels.QiitaUserListViewModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class QiitaUserListActivity extends AppCompatActivity implements QiitaUserListViewModel.UserFetchSucceededListener, QiitaUserListViewModel.UserFetchFailedListener {

    @BindView(R.id.rv_user_list)
    RecyclerView rvUserList;

    QiitaUserListViewModel viewModel;
    private UserListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qiita_user_list);

        if (savedInstanceState == null) {
            this.viewModel = ViewModelProviders.of(this).get(QiitaUserListViewModel.class);
            this.viewModel.init(this, this);
        }

        ButterKnife.bind(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.viewModel.fetchUsers();

        /**
         * Super :shit:
         */
//        for (int i = 0; i < 100; i++) this.viewModel.fetchUsers();

        // initialize RecyclerView with adapter
        adapter = new UserListAdapter(this);
        rvUserList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rvUserList.setAdapter(adapter);
        setListDivider();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void fetchSucceeded(List<User> addedUsers) {
        adapter.update();
    }

    @Override
    public void fetchFailed(Throwable throwable) {
        Toast.makeText(this, "ユーザー一覧の取得に失敗しました: " + throwable.getMessage(), Toast.LENGTH_LONG).show();
    }

    private void setListDivider() {
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(rvUserList.getContext(),
                new LinearLayoutManager(this).getOrientation());
        rvUserList.addItemDecoration(dividerItemDecoration);
    }

    public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.UserViewHolder> {

        LayoutInflater inflater;

        public UserListAdapter(Context context) {
            this.inflater = LayoutInflater.from(context);
        }

        public void update() {
            notifyDataSetChanged();
        }

        @Override
        public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = inflater.inflate(R.layout.user_item_view, parent, false);
            return new UserViewHolder(view);
        }

        @Override
        public void onBindViewHolder(UserViewHolder holder, int position) {
            User user = viewModel.getUsers().get(position);
            if (!TextUtils.isEmpty(user.getProfileImageUrl())) Log.d("CHECK", String.valueOf(user));
            Log.d("HOGE", String.valueOf(user));
            holder.setUserData(user);
        }

        @Override
        public int getItemCount() {
            return viewModel.getUsers().size();
        }

        public class UserViewHolder extends RecyclerView.ViewHolder {

            @BindView(R.id.iv_thumbnail)
            ImageView ivThumbnail;

            @BindView(R.id.tv_user_name)
            TextView tvUserName;

            public UserViewHolder(View itemView) {
                super(itemView);
                ButterKnife.bind(this, itemView);
            }

            public void setUserData(User user) {
                RequestOptions requestOptions = new RequestOptions();
                requestOptions.placeholder(R.drawable.ic_assignment_ind_grey_400_24dp);
                requestOptions.placeholder(R.drawable.ic_assignment_ind_grey_400_24dp);

                Glide.with(itemView)
                        .load(user.getProfileImageUrl())
                        .apply(requestOptions)
                        .into(ivThumbnail);

                tvUserName.setText(user.getId());
            }
        }
    }
}
