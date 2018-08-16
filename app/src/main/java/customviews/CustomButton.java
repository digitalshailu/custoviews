package customviews;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;

import com.example.customviews.R;

/**
 * Created by shailendra on 15/08/2018.
 */

public class CustomButton extends AppCompatButton {

    public CustomButton(Context context) {
        super(context);
        setCustomFont(context, null);
    }

    public CustomButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        setCustomFont(context, attrs);
    }

    public CustomButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setCustomFont(context, attrs);
    }

    /**
     * Sets custom font to textview
     *
     * @param context
     */
    @SuppressLint("CustomViewStyleable")
    private void setCustomFont(Context context, AttributeSet attrs) {
        String fontType = FontProvider.ROBOTO_BLACK;
        if (null != attrs) {
            TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.CustomFont);
            int fontTypeInt = array.getInt(R.styleable.CustomFont_customFontStyle, CustomFontStyle.ROBOTO_BLACK.getIndex());
            array.recycle();
            fontType = CustomFontStyle.getCustomFontStyle(fontTypeInt).getFontName();
        }
        setTypeface(FontProvider.getTypeface(context, fontType));
    }
}

