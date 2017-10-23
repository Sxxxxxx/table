package com.example.sx.myapplication;

import android.animation.Animator;
import android.content.Context;
import android.transition.TransitionValues;
import android.transition.Visibility;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by sx on 2017/8/25.
 */

public class Anim extends Visibility {
    private View tv1;
    private View tv2;
    Context context;

    public Anim(Context context, View tv1, View tv2) {
        this.tv1 = tv1;
        this.tv2 = tv2;
        this.context = context;
    }

    @Override
    public void captureStartValues(TransitionValues transitionValues) {
        super.captureStartValues(transitionValues);
    }

    @Override
    public void captureEndValues(TransitionValues transitionValues) {
        super.captureEndValues(transitionValues);
    }

    @Override
    public Animator onAppear(ViewGroup sceneRoot, View view, TransitionValues startValues, TransitionValues endValues) {
        return super.onAppear(sceneRoot, view, startValues, endValues);
    }

    @Override
    public Animator onDisappear(ViewGroup sceneRoot, View view, TransitionValues startValues, TransitionValues endValues) {
        return super.onDisappear(sceneRoot, view, startValues, endValues);
    }
}
