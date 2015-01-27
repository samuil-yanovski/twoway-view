package org.lucasr.twowayview.util;

import android.animation.ObjectAnimator;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;

/**
 * Created by samuil.yanovski on 15.1.2015 г..
 */
public class AnimationsUtil {

    public static final int FADE_ANIMATIONS_DURATION = 500;


    public static void fadeIn(View target) {
        float alpha = target.getAlpha();
        ObjectAnimator anim = ObjectAnimator.ofFloat(target, "alpha", alpha, 2 * alpha);
        anim.setDuration(FADE_ANIMATIONS_DURATION);
        anim.setInterpolator(new AccelerateDecelerateInterpolator());
        anim.start();
    }

    public static void fadeOut(View target) {
        float alpha = target.getAlpha();
        ObjectAnimator anim = ObjectAnimator.ofFloat(target, "alpha", alpha, alpha / 2);
        anim.setDuration(FADE_ANIMATIONS_DURATION);
        anim.setInterpolator(new AccelerateInterpolator());
        anim.start();
    }

}
