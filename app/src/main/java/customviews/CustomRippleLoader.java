package customviews;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.RelativeLayout;

import com.example.customviews.R;

import java.util.ArrayList;

public class CustomRippleLoader extends RelativeLayout {

    public CustomRippleLoader(Context context) {
        super(context);
    }


    private static final int RIPPLE_COUNT = 4;
    private static final int DURATION_TIME = 1000;
    private static final float SCALE = 6.0f;

    private float mRippleStrokeWidth;
    private boolean mIsAnimationRunning = false;

    private Paint mPaint;
    private AnimatorSet mAnimatorSet;
    private ArrayList<RippleView> mRippleViewList = new ArrayList<>();

    public CustomRippleLoader(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomRippleLoader(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        int rippleColor = getResources().getColor(R.color.colorAccent);
        mRippleStrokeWidth = getResources().getDimension(R.dimen.rippleStrokeWidth);
        float rippleRadius = getResources().getDimension(R.dimen.rippleRadius);
        int rippleDelay = DURATION_TIME / RIPPLE_COUNT;
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mRippleStrokeWidth = 0;
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(rippleColor);

        LayoutParams rippleParams = new LayoutParams((int) (2 * (rippleRadius + mRippleStrokeWidth)), (int) (2 * (rippleRadius + mRippleStrokeWidth)));
        rippleParams.addRule(CENTER_IN_PARENT, TRUE);
        mAnimatorSet = new AnimatorSet();
        mAnimatorSet.setInterpolator(new AccelerateDecelerateInterpolator());

        ArrayList<Animator> animatorList = new ArrayList<>();
        for (int i = 0; i < RIPPLE_COUNT; i++) {
            RippleView rippleView = new RippleView(getContext());
            addView(rippleView, rippleParams);
            mRippleViewList.add(rippleView);
            final ObjectAnimator scaleXAnimator = ObjectAnimator.ofFloat(rippleView, "ScaleX", 1.0f, SCALE);
            scaleXAnimator.setRepeatCount(ObjectAnimator.INFINITE);
            scaleXAnimator.setRepeatMode(ObjectAnimator.RESTART);
            scaleXAnimator.setStartDelay(i * rippleDelay);
            scaleXAnimator.setDuration(DURATION_TIME);
            animatorList.add(scaleXAnimator);
            final ObjectAnimator scaleYAnimator = ObjectAnimator.ofFloat(rippleView, "ScaleY", 1.0f, SCALE);
            scaleYAnimator.setRepeatCount(ObjectAnimator.INFINITE);
            scaleYAnimator.setRepeatMode(ObjectAnimator.RESTART);
            scaleYAnimator.setStartDelay(i * rippleDelay);
            scaleYAnimator.setDuration(DURATION_TIME);
            animatorList.add(scaleYAnimator);
            final ObjectAnimator alphaAnimator = ObjectAnimator.ofFloat(rippleView, "Alpha", 1.0f, 0f);
            alphaAnimator.setRepeatCount(ObjectAnimator.INFINITE);
            alphaAnimator.setRepeatMode(ObjectAnimator.RESTART);
            alphaAnimator.setStartDelay(i * rippleDelay);
            alphaAnimator.setDuration(DURATION_TIME);
            animatorList.add(alphaAnimator);
        }
        mAnimatorSet.playTogether(animatorList);
    }

    public void startRippleAnimation() {
        if (!isRippleAnimationRunning()) {
            for (RippleView rippleView : mRippleViewList) {
                rippleView.setVisibility(VISIBLE);
            }
            mAnimatorSet.start();
            mIsAnimationRunning = true;
        }
    }

    public boolean isRippleAnimationRunning() {
        return mIsAnimationRunning;
    }


    /**
     * A view class to draw the ripple.
     */
    private class RippleView extends View {
        public RippleView(Context context) {
            super(context);
            this.setVisibility(View.INVISIBLE);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            int radius = (Math.min(getWidth(), getHeight())) / 2;
            canvas.drawCircle(radius, radius, radius - mRippleStrokeWidth, mPaint);
        }
    }
}
