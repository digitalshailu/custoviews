package customviews;

import android.content.Context;
import android.graphics.Typeface;

public class FontProvider {
    private static final String PATH = "fonts/";
    public static final String ROBOTO_BLACK = PATH + "Roboto-Black.ttf";
    public static final String ROBOTO_BLACK_ITALIC = PATH + "Roboto-BlackItalic.ttf";
    public static final String ROBOTO_BOLD = PATH + "Roboto-Bold.ttf";
    public static final String ROBOTO_BOLD_ITALIC = PATH + "Roboto-BoldItalic.ttf";
    public static final String ROBOTO_ITALIC = PATH + "Roboto-Italic.ttf";
    public static final String ROBOTO_LIGHT= PATH + "Roboto-Light.ttf";
    public static final String ROBOTO_LIGHT_ITALIC= PATH + "Roboto-LightItalic.ttf";
    public static final String ROBOTO_MEDIUM= PATH + "Roboto-Medium.ttf";
    public static final String ROBOTO_MEDIUM_ITALIC= PATH + "Roboto-MediumItalic.ttf";
    public static final String ROBOTO_REGULAR= PATH + "Roboto-Regular.ttf";
    public static final String ROBOTO_THIN= PATH + "Roboto-Thin.ttf";
    public static final String ROBOTO_THIN_ITALIC= PATH + "Roboto-ThinItalic.ttf";

    public static Typeface getTypeface(Context context, String font) {
        return Typeface.createFromAsset(context.getAssets(), font);
    }
}