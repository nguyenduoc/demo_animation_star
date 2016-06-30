package vietedcom.animationstar;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.drawable.Animatable;
import android.os.Build;
import android.support.v4.view.animation.FastOutLinearInInterpolator;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnticipateOvershootInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.CycleInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;

/**
 * Created by Smile on 6/29/2016.
 */
public class Star extends ImageView implements Animatable {
    ;

    private AnimatorSet mObjectAnimation;
    private boolean isAnimation = false;
    private OnEndAnimationListener mOnEndAnimationListener;

    public Star(Context context) {
        super(context);
        init(context);
    }

    public Star(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public Star(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);

    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public Star(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(Context context) {
//        inflate(context, R.layout.include_star_layout, this);
//        mStarBackground = (ImageView) findViewById(R.id.star_background);
//        mStarAnimation = (ImageView) findViewById(R.id.star_animation);

    }

    public void setupAnimation(int fromRotation, int toRotation,
                               float fromAlpha, float toAlpha) {
        mObjectAnimation = new AnimatorSet();
        mObjectAnimation.setInterpolator(new FastOutSlowInInterpolator());
        mObjectAnimation.playTogether(
                ObjectAnimator.ofFloat(this, SCALE_X, 5f, 1f),
                ObjectAnimator.ofFloat(this, SCALE_Y, 5f, 1f),
                ObjectAnimator.ofFloat(this, ROTATION, fromRotation, toRotation),
                ObjectAnimator.ofFloat(this, ALPHA, fromAlpha, toAlpha));
        mObjectAnimation.setDuration(1000);
        mObjectAnimation.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                if (mOnEndAnimationListener != null) {
                    mOnEndAnimationListener.onEnd(Star.this);
                }
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
    }

    @Override
    public void start() {
        if (isAnimation)
            return;
        setVisibility(VISIBLE);
        bringToFront();
        mObjectAnimation.start();
    }

    @Override
    public void stop() {
        if (!isAnimation)
            return;
        mObjectAnimation.cancel();
    }

    @Override
    public boolean isRunning() {
        return isAnimation;
    }

    public interface OnEndAnimationListener {
        void onEnd(View view);
    }

    public void setOnEndAnimationListener(OnEndAnimationListener listener) {
        mOnEndAnimationListener = listener;
    }
}
