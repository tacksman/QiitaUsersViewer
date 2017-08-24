package com.tacksman.qiitausersviewer.presentation.activities;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.tacksman.qiitausersviewer.R;
import com.tacksman.qiitausersviewer.entity.User;
import com.tacksman.qiitausersviewer.presentation.viewmodels.QiitaUserListViewModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class QiitaUserListActivity extends AppCompatActivity implements QiitaUserListViewModel.UserFetchSucceededListener,
                                                                        QiitaUserListViewModel.UserFetchFailedListener
//        , QiitaUserListActivity.OnMoreScrolledListener
{

    @BindView(R.id.rv_user_list)
    RecyclerView rvUserList;

    @BindView(R.id.pb_load)
    ProgressBar pbLoad;

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
        pbLoad.setVisibility(View.VISIBLE);
        this.viewModel.fetchUsers();

        /**
         * Super :shit:
         */
//        for (int i = 0; i < 100; i++) this.viewModel.fetchUsers();

        // initialize RecyclerView with adapter
        adapter = new UserListAdapter(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvUserList.setLayoutManager(layoutManager);
        rvUserList.setAdapter(adapter);
        rvUserList.addOnScrollListener(new EndlessScrollListener(layoutManager) {
            @Override
            public void onLoadMore() {
                pbLoad.setVisibility(View.VISIBLE);
                viewModel.fetchUsers();
            }

            @Override
            public void onLoadFinish() {
                pbLoad.setVisibility(View.GONE);
            }
        });
        setListDivider();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void fetchSucceeded(List<User> addedUsers) {
        adapter.update(addedUsers);
        pbLoad.setVisibility(View.GONE);
    }

    @Override
    public void fetchFailed(Throwable throwable) {
        Toast.makeText(this, "ユーザー一覧の取得に失敗しました: " + throwable.getMessage(), Toast.LENGTH_LONG).show();
        pbLoad.setVisibility(View.GONE);
    }

//    @Override
//    public void onLoadMore() {
//        viewModel.fetchUsers();
//    }

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

        public void update(List<User> addedUsers) {
            if (!viewModel.getUsers().isEmpty()) {
                notifyItemMoved(viewModel.getUsers().size() - addedUsers.size(), viewModel.getUsers().size() - 1);
            }
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

    public abstract class EndlessScrollListener extends RecyclerView.OnScrollListener {

        int visibleThreshold = 6;
        int firstVisibleItem, visibleItemCount, totalItemCount;
        private int previousTotal = 0;
        private boolean loading = true;
        private int current_page = 1;

        private LinearLayoutManager mLinearLayoutManager;

        public EndlessScrollListener(LinearLayoutManager linearLayoutManager) {
            this.mLinearLayoutManager = linearLayoutManager;
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);

            visibleItemCount = recyclerView.getChildCount();
            totalItemCount = mLinearLayoutManager.getItemCount();
            firstVisibleItem = mLinearLayoutManager.findFirstVisibleItemPosition();

            if (loading) {
                if (totalItemCount > previousTotal) {
                    loading = false;
                    onLoadFinish();
                    previousTotal = totalItemCount;
                }
            }

            if (!loading && (totalItemCount - visibleItemCount) <= (firstVisibleItem + visibleThreshold)) {

                loading = true;
                onLoadMore();
            }
        }

        public abstract void onLoadMore();

        public abstract void onLoadFinish();
    }
}
