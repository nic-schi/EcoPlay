package com.jadehs.ma.ecoplay.utils;

import android.animation.ValueAnimator;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;

public class Animator {

    public static void animateCollapse(View parent, View view, ImageView icon) {
        Animator.animateCollapse(parent, view, icon, view.getVisibility() == View.VISIBLE);
    }

    public static void animateCollapse(View parent, View view, ImageView icon, boolean isCollapsed) {
        Animator.animateCollapse(parent, view, icon, isCollapsed, 300);
    }

    public static void animateCollapse(View parent, View view, ImageView icon, boolean isCollapsed, int duration) {
        view.setVisibility(View.VISIBLE);

        int currentHeight = view.getHeight();
        int newHeight = 0;

        if (!isCollapsed) {
            int widthMeasureSpec = View.MeasureSpec.makeMeasureSpec(parent.getWidth(), View.MeasureSpec.AT_MOST);
            int heightMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
            view.measure(widthMeasureSpec, heightMeasureSpec);
            newHeight = view.getMeasuredHeight();
        }

        ValueAnimator animator = ValueAnimator.ofInt(currentHeight, newHeight).setDuration(duration);
        animator.addUpdateListener(animation -> {
            view.getLayoutParams().height = (Integer) animation.getAnimatedValue();
            view.requestLayout();
        });
        animator.addListener(new android.animation.Animator.AnimatorListener() {
            public void onAnimationStart(@NonNull android.animation.Animator animation) {
            }

            public void onAnimationEnd(@NonNull android.animation.Animator animation) {
                if (isCollapsed) {
                    view.setVisibility(View.GONE);
                }
            }

            public void onAnimationCancel(@NonNull android.animation.Animator animation) {
            }

            public void onAnimationRepeat(@NonNull android.animation.Animator animation) {
            }
        });

        animator.start();

        // Animate icon
        if (icon != null) {
            int newRotation = isCollapsed ? 0 : 180;
            ValueAnimator iconAnimator = ValueAnimator.ofFloat(icon.getRotation(), newRotation);
            iconAnimator.addUpdateListener(animation -> {
                icon.setRotation((Float) animation.getAnimatedValue());
            });
            iconAnimator.start();
        }
    }

}
