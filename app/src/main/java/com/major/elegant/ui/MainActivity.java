package com.major.elegant.ui;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.major.elegant.R;
import com.major.elegant.base.BaseActivity;
import com.major.elegant.bean.Gank;
import com.major.elegant.biz.main.MainContract;
import com.major.elegant.biz.main.MainPresenterImpl;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity<MainPresenterImpl> implements MainContract.MainView {

    private ListView       mListView;
    private ProgressDialog mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton)findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null)
//                        .show();
            }
        });

        mListView = (ListView)findViewById(R.id.lv_main);

        mDialog = new ProgressDialog(this);
        mDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        mDialog.setCancelable(false);
        mDialog.setMessage("加载中...");

    }

    @Override
    protected MainPresenterImpl onCreatePresenter() {
        return new MainPresenterImpl(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showDialog() {
        if (mDialog != null) {
            mDialog.show();
        }
    }

    @Override
    public void hideDialog() {
        if (mDialog != null) {
            mDialog.dismiss();
        }
    }

    @Override
    public void onSuccess(Gank data) {
        Toast.makeText(MainActivity.this, "请求成功", Toast.LENGTH_SHORT).show();
        List<Gank.Result> results = data.getResults();
        List<String> list = new ArrayList<>();
        for (Gank.Result result : results) {
            list.add(result.toString());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, (String[])list.toArray());
        mListView.setAdapter(adapter);
    }

    @Override
    public void onFailure(String err) {
        Toast.makeText(MainActivity.this, "请求失败", Toast.LENGTH_SHORT).show();
    }
}
