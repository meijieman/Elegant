package com.major.elegant.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * @desc: TODO
 * @author: Major
 * @since: 2017/2/22 0:14
 */
public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity {

    protected P mPresenter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = onCreatePresenter();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.unSubscribe();
        }
    }

    protected abstract P onCreatePresenter();
}
