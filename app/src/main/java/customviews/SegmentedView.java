package customviews;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import com.example.customviews.R;

import java.util.HashMap;
import java.util.Map;

public class SegmentedView extends RelativeLayout {
    private final Paint mProgressColorPaint = new Paint();
    private CompletionCount mCompletionCount = new CompletionCount();

    public SegmentedView(Context context) {
        super(context);
        setWillNotDraw(false);
    }

    public SegmentedView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setWillNotDraw(false);
    }

    @Override
    protected synchronized void onDraw(Canvas canvas) {
        if (mCompletionCount.getTotalCount()>0) {
            drawSegments(canvas);
        }
    }

    private void drawSegments(Canvas canvas) {
        float size = Math.min(getWidth(), getHeight());
        int completedSegmentCount, totalSegmentCount;
        completedSegmentCount = mCompletionCount.getCompletedCount();
        totalSegmentCount = mCompletionCount.getTotalCount();

        final RectF oval = new RectF(0, 0, getWidth(), getHeight());
        oval.inset(size / 13, size / 13);
        int getSplit = 360 / totalSegmentCount;
        int optimizeGetSplit = getSplit - 10;
        int initialStart = 275;
        int getChoppedAngle;
        int getColorCodeValue = 0;
        boolean setAngleBoolean = false;
        Map<String, Path> multiPathMap = new HashMap<>();


        if (getResources().getDisplayMetrics().density >= 4.0) {
            mProgressColorPaint.setStrokeWidth(10);
        } else if (getResources().getDisplayMetrics().density >= 2.0) {
            mProgressColorPaint.setStrokeWidth(8);
        } else if (getResources().getDisplayMetrics().density >= 1.3) {
            mProgressColorPaint.setStrokeWidth(5);
        } else if (getResources().getDisplayMetrics().density < 1.3) {
            mProgressColorPaint.setStrokeWidth(4);
        } else {
            mProgressColorPaint.setStrokeWidth(8);
        }

        mProgressColorPaint.setAntiAlias(true);
        mProgressColorPaint.setStrokeCap(Paint.Cap.ROUND);
        mProgressColorPaint.setStyle(Paint.Style.STROKE);



        for (int i = 0; i < totalSegmentCount; i++) {
            Path path = new Path();
            multiPathMap.put("Path" + i, path);
        }
        for (Map.Entry<String, Path> entry : multiPathMap.entrySet()) {
            if (setAngleBoolean) {
                initialStart = initialStart + getSplit;
                getChoppedAngle = initialStart;
            } else {
                getChoppedAngle = initialStart;
                setAngleBoolean = true;
            }
            if (getColorCodeValue != completedSegmentCount) {
                mProgressColorPaint.setColor(getResources().getColor(R.color.colorAccent/*Color.parseColor(getContext().getString(R.string.segmented_green_color))*/));
                getColorCodeValue++;
            }else {
                mProgressColorPaint.setColor(getResources().getColor(R.color.colorPrimary/*Color.parseColor(getContext().getString(R.string.segment_orange_color))*/));
            }
            entry.getValue().arcTo(oval, getChoppedAngle, optimizeGetSplit, true);
            canvas.drawPath(entry.getValue(), mProgressColorPaint);
        }
    }


    public synchronized void setSegmentColor(int color) {
        mProgressColorPaint.setColor(color);
        invalidate();
    }


    public synchronized void setSegmentCount(CompletionCount completionCount) {
        mCompletionCount = completionCount;
        invalidate();
    }

    /**
     * Class to set completion and total segment count.
     */
    public static class CompletionCount {

        private int completedCount;
        private int totalCount;


        public void setCompletedCount(int completedCount) {
            this.completedCount = completedCount;
        }

        public void setTotalCount(int totalCount) {
            this.totalCount = totalCount;
        }


        public int getTotalCount() {
            return totalCount;
        }

        public int getCompletedCount() {
            return completedCount;
        }
    }
}