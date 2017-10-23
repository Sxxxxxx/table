package com.example.sx.myapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.transition.ChangeTransform;
import android.transition.Explode;
import android.view.Window;

/**
 * Created by sx on 2017/8/25.
 */

public class SecondActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);

// 允许使用transitions
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
// 指定进入、退出、返回、重新进入时的transitions
        getWindow().setEnterTransition(new Explode());
        getWindow().setExitTransition(new Explode());
        getWindow().setEnterTransition(new Explode());
        getWindow().setExitTransition(new Explode());
// 指定进入、退出、返回、重新进入时的共享transitions
        getWindow().setSharedElementEnterTransition(new ChangeTransform());
        getWindow().setSharedElementExitTransition(new ChangeTransform());
        getWindow().setSharedElementReturnTransition(new ChangeTransform());
        getWindow().setSharedElementReenterTransition(new ChangeTransform());

    }
}
