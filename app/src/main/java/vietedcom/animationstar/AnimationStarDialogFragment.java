package vietedcom.animationstar;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Smile on 6/29/2016.
 */
public class AnimationStarDialogFragment extends DialogFragment {
    private Star mStarLeft, mStarCenter, mStartRight;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_fragment_animation_star, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mStarLeft = (Star) view.findViewById(R.id.star_left);
        mStarCenter = (Star) view.findViewById(R.id.star_center);
        mStartRight = (Star) view.findViewById(R.id.star_right);
        mStarLeft.setupAnimation(-90, -20, 0.9f, 1);
        mStarCenter.setupAnimation(20, 0, 0.5f, 1);
        mStartRight.setupAnimation(90, 20, 0.9f, 1);
        mStarLeft.setOnEndAnimationListener(mOnEndAnimationListener);
        mStarCenter.setOnEndAnimationListener(mOnEndAnimationListener);
        mStartRight.setOnEndAnimationListener(mOnEndAnimationListener);
    }

    private Star.OnEndAnimationListener mOnEndAnimationListener = new Star.OnEndAnimationListener() {
        @Override
        public void onEnd(View view) {
            switch (view.getId()) {
                case R.id.star_left:
                    mStarCenter.start();
                    break;
                case R.id.star_center:
                    mStartRight.start();
                    break;
                case R.id.star_right:
                    break;

            }
        }
    };

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mStarLeft.start();
    }
}
