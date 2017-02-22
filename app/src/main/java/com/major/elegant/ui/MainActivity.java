package com.major.elegant.ui;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.major.elegant.R;
import com.major.elegant.base.BaseActivity;
import com.major.elegant.bean.Gank;
import com.major.elegant.biz.main.MainContract;
import com.major.elegant.biz.main.MainPresenter;
import com.major.elegant.util.SnackbarUtil;

import java.util.List;

public class MainActivity extends BaseActivity<MainPresenter> implements MainContract.View {

    private ListView mListView;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected MainPresenter onCreatePresenter() {
        return new MainPresenter(this);
    }

    @Override
    protected void init() {
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton)findViewById(R.id.fab);
        fab.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {

                mListView.setAdapter(null);
                mPresenter.getGank(1);
            }
        });

        mListView = (ListView)findViewById(R.id.lv_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSuccess(Gank data) {
        SnackbarUtil.show(mListView, "请求成功");
        List<Gank.Result> results = data.getResults();
        String[] strs = new String[results.size()];
        for (int i = 0; i < results.size(); i++) {
            Gank.Result result = results.get(i);
            strs[i] = result.toString();
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, strs);
        mListView.setAdapter(adapter);
    }

    @Override
    public void onFailure(String err) {
        SnackbarUtil.show(mListView, "请求失败", "重试", new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                mListView.setAdapter(null);
                mPresenter.getGank(1);
            }
        });
    }
}
